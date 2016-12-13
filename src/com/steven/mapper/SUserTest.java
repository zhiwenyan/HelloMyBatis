package com.steven.mapper;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SUserTest {
	@Test
	public void getSUer() throws Exception {
		String pathXml="beans.xml";
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext(pathXml);
		UserDao userDao=(UserDao)applicationContext.getBean("userDao");
		System.out.println(userDao.findUserById(1).toString());
	}
}
