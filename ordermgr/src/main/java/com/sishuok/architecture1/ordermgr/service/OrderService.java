package com.sishuok.architecture1.ordermgr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sishuok.architecture1.common.service.BaseService;
import com.sishuok.architecture1.ordermgr.dao.OrderDAO;
import com.sishuok.architecture1.ordermgr.vo.OrderModel;
import com.sishuok.architecture1.ordermgr.vo.OrderQueryModel;
@Service
public class OrderService extends BaseService<OrderModel, OrderQueryModel>
	implements IOrderService{
	private OrderDAO dao;

	@Resource
	public void setDao(OrderDAO dao) {
		this.dao = dao;
		super.setDao(dao);
	}
	
}
