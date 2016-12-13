package com.steven.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.catalina.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.steven.bean.Classes;
import com.steven.bean.ConditionUser;
import com.steven.bean.Duser;
import com.steven.bean.Users;
import com.sun.nio.sctp.SctpStandardSocketOptions.InitMaxStreams;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Mybatis5 {
	//因为接下来的测试代码中，获取sqlSession这部分都相同，所以抽取成一个方法
	public SqlSession getSession() throws IOException {
		String resource = "SqlMapConfig.xml"; //mybatis配置文件
		//得到配置文件的流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂SqlSessionFactory,要传入mybaits的配置文件的流
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(); //默认是手动提交
		return sqlSession;
	}

	@Test
	public void getClasses() throws IOException {
		SqlSession sqlSession = getSession(); 
		String name="o";
		name="";
		ConditionUser conditionUser=new ConditionUser("%"+name+"%",13,20);
		List<Duser>	 lists=sqlSession.selectList("test.getUserSQL", conditionUser);
		System.out.println(lists);
		sqlSession.close();
	}

}
