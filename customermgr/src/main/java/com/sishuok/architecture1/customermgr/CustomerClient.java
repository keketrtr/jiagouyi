package com.sishuok.architecture1.customermgr;

import javax.annotation.Resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sishuok.architecture1.customermgr.service.ICustomerService;
import com.sishuok.architecture1.customermgr.vo.CustomerModel;
import com.sishuok.architecture1.customermgr.vo.CustomerQueryModel;
import com.sishuok.pageutil.Page;

@Service
@Transactional
public class CustomerClient {
	@Resource
	private ICustomerService customerService;
	
	public ICustomerService getCustomerService() {
		return customerService;
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerClient customerClient = (CustomerClient)ctx.getBean("customerClient");
		ctx.close();
//		CustomerModel cm = new CustomerModel();
//		cm.setCustomerId("c2");
//		cm.setPwd("afdas");
//		cm.setShowName("c2");
//		cm.setTrueName("张asd");
//		cm.setRegisterTime("111");
//		
//		customerClient.customerDAO.create(cm);
		CustomerQueryModel cqm = new CustomerQueryModel();
		cqm.getPage().setCurrentPage(1);
		
		Page<CustomerModel> page = customerClient.getCustomerService().getByConditionPage(cqm);
		System.out.println("list=="+page);
		
		Page<CustomerModel> page2 = customerClient.getCustomerService().getByConditionPage(cqm);
		System.out.println("list2222=="+page2);
	}
}
