package com.sishuok.architecture1.customermgr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sishuok.architecture1.common.service.BaseService;
import com.sishuok.architecture1.customermgr.dao.CustomerDAO;
import com.sishuok.architecture1.customermgr.vo.CustomerModel;
import com.sishuok.architecture1.customermgr.vo.CustomerQueryModel;
@Service
public class CustomerService extends BaseService<CustomerModel, CustomerQueryModel>
	implements ICustomerService{
	private CustomerDAO dao;

	@Resource
	public void setDao(CustomerDAO dao) {
		this.dao = dao;
		super.setDao(dao);
	}

	@Override
	public CustomerModel getByCustomerId(String customerId) {
		return dao.getByCustomerId(customerId);
	}
	
}
