package com.sishuok.consistance;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sishuok.interactive.InteractiveCallHelper;

@Service
public class MyTest {
	@Resource
	private InteractiveCallHelper callHelper;
	
	public void testCall(){
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("ip", "localhost");
		mapParam.put("port", "9080");
		mapParam.put("url", "/storeDispatchFI/call");
		callHelper.call("StoreModule", "1", mapParam);
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		MyTest test = ctx.getBean(MyTest.class);
		test.testCall();
	}
}
