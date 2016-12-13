package com.steven.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.catalina.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.steven.bean.Cuser;
import com.steven.bean.Users;
import com.sun.nio.sctp.SctpStandardSocketOptions.InitMaxStreams;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class TestCache2 {
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
	public void testCacheTwo() throws IOException {
		SqlSession sqlSession1 = getSession(); //调用上面的方法获取sqlSession
		SqlSession sqlSession2 = getSession(); //调用上面的方法获取sqlSession
		Cuser cuser=sqlSession1.selectOne("test.getCUser", 2);
		sqlSession1.commit();

		System.out.println(cuser.toString());
		System.out.println("------------");
		sqlSession1.close();

		//sqlSession.update("test.updateCUser", new Cuser(1, "Tom", 15));

		cuser=sqlSession2.selectOne("test.getCUser", 2);
		sqlSession2.commit();
		System.out.println(cuser.toString());


		sqlSession2.close();
	}


}
