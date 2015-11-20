package com.sishuok.architecture1.ordermgr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sishuok.architecture1.cartmgr.service.ICartService;
import com.sishuok.architecture1.cartmgr.vo.CartModel;
import com.sishuok.architecture1.cartmgr.vo.CartQueryModel;
import com.sishuok.architecture1.common.service.BaseService;
import com.sishuok.architecture1.ordermgr.dao.OrderDAO;
import com.sishuok.architecture1.ordermgr.queue.QueueSender;
import com.sishuok.architecture1.ordermgr.vo.OrderDetailModel;
import com.sishuok.architecture1.ordermgr.vo.OrderModel;
import com.sishuok.architecture1.ordermgr.vo.OrderQueryModel;
import com.sishuok.architecture1.storemgr.service.IStoreService;
import com.sishuok.architecture1.storemgr.vo.StoreModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.format.DateFormatHelper;
@Service
public class OrderService extends BaseService<OrderModel, OrderQueryModel>
	implements IOrderService{
	@Resource
	private ICartService cartService;
	@Resource
	private IOrderDetailService orderDetailService;
	@Resource
	private IStoreService storeService;
	
	private OrderDAO dao;

	@Resource
	public void setDao(OrderDAO dao) {
		this.dao = dao;
		super.setDao(dao);
	}

	@Override
	public void order(int customerUuid) {
		
		QueueSender.sendMsg(customerUuid);
		
		
		
	}
	
}
