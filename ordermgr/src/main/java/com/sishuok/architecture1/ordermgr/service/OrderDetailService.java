package com.sishuok.architecture1.ordermgr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sishuok.architecture1.common.service.BaseService;
import com.sishuok.architecture1.ordermgr.dao.OrderDetailDAO;
import com.sishuok.architecture1.ordermgr.vo.OrderDetailModel;
import com.sishuok.architecture1.ordermgr.vo.OrderDetailQueryModel;
@Service
public class OrderDetailService extends BaseService<OrderDetailModel, OrderDetailQueryModel>
	implements IOrderDetailService{
	private OrderDetailDAO dao;

	@Resource
	public void setDao(OrderDetailDAO dao) {
		this.dao = dao;
		super.setDao(dao);
	}
	
}
