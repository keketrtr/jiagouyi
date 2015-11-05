package com.sishuok.architecture1.goodsmgr.client;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sishuok.architecture1.goodsmgr.dao.GoodsDAO;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
@Service
public class ClientTest3 {
	@Resource
	private GoodsDAO dao;
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ClientTest3 test3 = context.getBean(ClientTest3.class);
		
//		GoodsModel m = test3.dao.getByUuid(1);
//		System.out.println("m==="+m);
		
		GoodsQueryModel qm = new GoodsQueryModel();
		qm.getPage().setNumPerPage(100);
		List<GoodsModel> list = test3.dao.getByConditionPage(qm);
		System.out.println("list===="+list);
		
		context.close();
	}
}
