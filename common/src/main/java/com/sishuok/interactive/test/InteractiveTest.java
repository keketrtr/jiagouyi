package com.sishuok.interactive.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sishuok.interactive.InteractiveCallHelper;

@Service
public class InteractiveTest {
	@Resource
	private InteractiveCallHelper callHelper;
	
	public void testCall(){
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("abc", "aa11");
		mapParam.put("paramName", "testName");
		MyRetModel rm = callHelper.call("GoodsModule", "1", mapParam, MyRetModel.class);
		System.out.println("rm======="+rm);
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		InteractiveTest test = ctx.getBean(InteractiveTest.class);
		test.testCall();
	}
}
