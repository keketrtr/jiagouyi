package com.sishuok.architecture1.goodsmgr.client;

import java.util.ArrayList;
import java.util.List;

import com.danga.MemCached.MemCachedClient;
import com.sishuok.memcached.MemcachedUtil;

public class ClientTest {
	public static void main(String[] args) {
		MemCachedClient client = MemcachedUtil.getMemCachedClient();
		
//		client.set("Data1", "12345");
//		Object obj = client.get("Data1");
//		System.out.println("obj===="+obj);
		
//		UserModel userModel = new UserModel("11", "11name", 11);
//		client.set("user1", userModel);
//		Object user1 = client.get("user1");
//		System.out.println("user1===="+user1);
		
		List<UserModel> list = new ArrayList<UserModel>();
		list.add(new UserModel("11", "11name", 11));
		list.add(new UserModel("22", "22name", 22));
		client.set("list", list);
		Object userList = client.get("list");
		System.out.println("userList===="+userList);
		
	}
}
