package com.sishuok.architecture1;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sishuok.architecture1.cartmgr.service.ICartService;
import com.sishuok.architecture1.cartmgr.vo.CartModel;
import com.sishuok.architecture1.cartmgr.vo.CartQueryModel;
import com.sishuok.architecture1.goodsmgr.service.IGoodsService;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.architecture1.ordermgr.service.IOrderDetailService;
import com.sishuok.architecture1.ordermgr.service.IOrderService;
import com.sishuok.architecture1.ordermgr.vo.OrderDetailModel;
import com.sishuok.architecture1.ordermgr.vo.OrderModel;
import com.sishuok.architecture1.ordermgr.vo.OrderQueryModel;
import com.sishuok.architecture1.storemgr.service.IStoreService;
import com.sishuok.architecture1.storemgr.vo.StoreModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.format.DateFormatHelper;

@Controller
@RequestMapping("/")
public class IndexController {
	@Resource
	private IGoodsService goodsService;
	@Resource
	private ICartService cartService;
	@Resource
	private IOrderService orderService;
	@Resource
	private IOrderDetailService orderDetailService;
	@Resource
	private IStoreService storeService;
	
	@RequestMapping(value="/toIndex", method=RequestMethod.GET)
	public String toIndex(Model model){
		GoodsQueryModel qm = new GoodsQueryModel();
		qm.getPage().setNumPerPage(100);
		Page<GoodsModel> page = goodsService.getByConditionPage(qm);
		model.addAttribute("page", page);
		return "index";
	}
	
	@RequestMapping(value="/toGoodsDesc/{goodsUuid}", method=RequestMethod.GET)
	public String toGoodsDesc(Model model, @PathVariable("goodsUuid") int goodsUuid){
		GoodsModel goodsModel = goodsService.getByUuid(goodsUuid);
		model.addAttribute("m", goodsModel);
		return "goods/desc";
	}
	
	@RequestMapping(value="/addToCart/{goodsUuid}", method=RequestMethod.GET)
	public String addToCart(Model model, @PathVariable("goodsUuid") int goodsUuid, @CookieValue("MyLogin") String myLogin){
		int customerUuid = Integer.parseInt(myLogin.split(",")[0]);
		CartModel cartModel = new CartModel();
		cartModel.setBuyNum(1);
		cartModel.setCustomerUuid(customerUuid);
		cartModel.setGoodUuid(goodsUuid);
		
		cartService.create(cartModel);
		
		CartQueryModel qm = new CartQueryModel();
		qm.getPage().setNumPerPage(1000);
		qm.setCustomerUuid(customerUuid);
		Page<CartModel> page = cartService.getByConditionPage(qm);
		model.addAttribute("page", page);
		
		return "cart/myCart";
	}
	
	@RequestMapping(value="/toCart", method=RequestMethod.GET)
	public String toCart(Model model, @CookieValue("MyLogin") String myLogin){
		int customerUuid = Integer.parseInt(myLogin.split(",")[0]);
		
		CartQueryModel qm = new CartQueryModel();
		qm.getPage().setNumPerPage(1000);
		qm.setCustomerUuid(customerUuid);
		Page<CartModel> page = cartService.getByConditionPage(qm);
		model.addAttribute("page", page);
		
		return "cart/myCart";
	}
	
	@RequestMapping(value="/order", method=RequestMethod.GET)
	public String order(@CookieValue("MyLogin") String myLogin){
		//1、查出用户购物车所有的信息
		int customerUuid = Integer.parseInt(myLogin.split(",")[0]);
		CartQueryModel cartQueryModel = new CartQueryModel();
		cartQueryModel.getPage().setNumPerPage(1000);
		cartQueryModel.setCustomerUuid(customerUuid);
		Page<CartModel> page = cartService.getByConditionPage(cartQueryModel);
		//2、
		float totalMoney = 0.0f;
		for(CartModel cartModel : page.getResult()){
			totalMoney += 10;//10元一件商品
		}
		
		OrderModel orderModel = new OrderModel();
		orderModel.setCustomerUuid(customerUuid);
		orderModel.setOrderTime(DateFormatHelper.date2str(System.currentTimeMillis()));
		orderModel.setSaveMoney(0.0F);
		orderModel.setTotalMoney(totalMoney);
		orderModel.setState(1);
		
		orderService.create(orderModel);
		
		OrderQueryModel orderQueryModel = new OrderQueryModel();
		orderQueryModel.setOrderTime(orderModel.getOrderTime());
		Page<OrderModel> orderPage = orderService.getByConditionPage(orderQueryModel);
		orderModel = orderPage.getResult().get(0);
		
		//3、
		for(CartModel cartModel : page.getResult()){
			OrderDetailModel orderDetailModel = new OrderDetailModel();
			orderDetailModel.setGoodsUuid(cartModel.getGoodUuid());
			orderDetailModel.setOrderNum(cartModel.getBuyNum());
			orderDetailModel.setPrice(10.0f);
			orderDetailModel.setMoney(orderDetailModel.getPrice()*orderDetailModel.getOrderNum());
			orderDetailModel.setSaveMoney(0.0f);
			orderDetailModel.setOrderUuid(orderModel.getUuid());
			
			orderDetailService.create(orderDetailModel);
			//4、
			StoreModel storeModel = storeService.getByGoodsUuid(cartModel.getGoodUuid());
			storeModel.setStoreNum(storeModel.getStoreNum()-orderDetailModel.getOrderNum());
			storeService.update(storeModel);
			//5、
			cartService.delete(cartModel.getUuid());
		}
		
		
		return "success";
	}
	
	
}
