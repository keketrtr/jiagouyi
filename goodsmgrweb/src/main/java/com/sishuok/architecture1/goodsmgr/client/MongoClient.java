package com.sishuok.architecture1.goodsmgr.client;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sishuok.architecture1.goodsmgr.service.GoodsService;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.pageutil.Page;

@Service
public class MongoClient {
	@Resource
	private GoodsService goodsService;
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		MongoClient client = ctx.getBean(MongoClient.class);
		
//		GoodsModel gm1 = new GoodsModel();
//		gm1.setUuid(100);
//		gm1.setName("name100");
//		gm1.setDescription("dd100");
//		
//		GoodsModel gm2 = new GoodsModel();
//		gm2.setUuid(200);
//		gm2.setName("name200");
//		gm2.setDescription("dd200");
//		
//		GoodsModel gm3 = new GoodsModel();
//		gm3.setUuid(300);
//		gm3.setName("name300");
//		gm3.setDescription("dd300");
//		
//		client.goodsService.create(gm1);
//		client.goodsService.create(gm2);
//		client.goodsService.create(gm3);
		
		GoodsQueryModel qm = new GoodsQueryModel();
		qm.getPage().setCurrentPage(2);
		Page<GoodsModel> page = client.goodsService.getByConditionPage(qm);
		System.out.println(page);
	}
}
