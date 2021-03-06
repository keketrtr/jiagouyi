package com.sishuok.architecture1.goodsmgr.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;
@Service
public class ClientTest2 {
	@Resource
	private MemCachedClient memCachedClient;
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ClientTest2 test2 = context.getBean(ClientTest2.class);
		
		List<UserModel> list = new ArrayList<UserModel>();
		list.add(new UserModel("33", "11name", 11));
		list.add(new UserModel("44", "22name", 22));
		test2.memCachedClient.set("list", list);
		Object userList = test2.memCachedClient.get("list");
		System.out.println("userList===="+userList);
		
		context.close();
	}
}
