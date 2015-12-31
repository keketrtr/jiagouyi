package com.sishuok.transaction;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class TransactionTest1 {
	@Resource
	@Qualifier("jtaDataSource1")
	private DataSource ds1;
	@Resource
	@Qualifier("jtaDataSource2")
	private DataSource ds2;
	
	public void testAdd(){
		JdbcTemplate jt1 = new JdbcTemplate(ds1);
		JdbcTemplate jt2 = new JdbcTemplate(ds2);
		
		List list = jt1.queryForList("select * from tb1_store");
		System.out.println("list==="+list);
		
		jt1.execute("insert into tb1_store(goodsUuid,storeNum) values(8,8)");
		
		jt2.execute("insert into tb1_store(goodsUuid,storeNum) values(8,8)");
		
		int i = 5/0;//测试回滚
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-tx.xml");
		TransactionTest1 test = ctx.getBean(TransactionTest1.class);
		test.testAdd();
	}
}
