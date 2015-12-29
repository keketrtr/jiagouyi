package com.sishuok.architecture1;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sishuok.architecture1.cartmgr.service.ICartService;
import com.sishuok.architecture1.cartmgr.vo.CartModel;
import com.sishuok.architecture1.cartmgr.vo.CartQueryModel;
import com.sishuok.architecture1.customermgr.vo.CustomerModel;
import com.sishuok.architecture1.goodsmgr.service.IGoodsService;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.architecture1.ordermgr.service.IOrderDetailService;
import com.sishuok.architecture1.ordermgr.service.IOrderService;
import com.sishuok.architecture1.storemgr.service.IStoreService;
import com.sishuok.pageutil.Page;

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
	@Resource
	private SecurityManager securityManager;
	
	@RequestMapping(value="/toIndex", method=RequestMethod.GET)
	public String toIndex(Model model){
		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser = SecurityUtils.getSubject();
		CustomerModel customerModel = (CustomerModel) currentUser.getSession().getAttribute("Login_Customer");
		System.out.println("now customerModel==="+customerModel);
		
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
		
		orderService.order(customerUuid);
		
		return "success";
	}
	
	
}
