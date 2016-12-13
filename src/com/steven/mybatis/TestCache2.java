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
	//��Ϊ�������Ĳ��Դ����У���ȡsqlSession�ⲿ�ֶ���ͬ�����Գ�ȡ��һ������
	public SqlSession getSession() throws IOException {
		String resource = "SqlMapConfig.xml"; //mybatis�����ļ�
		//�õ������ļ�����
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//�����Ự����SqlSessionFactory,Ҫ����mybaits�������ļ�����
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//ͨ�������õ�SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(); //Ĭ�����ֶ��ύ
		return sqlSession;
	}

	@Test
	public void testCacheTwo() throws IOException {
		SqlSession sqlSession1 = getSession(); //��������ķ�����ȡsqlSession
		SqlSession sqlSession2 = getSession(); //��������ķ�����ȡsqlSession
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
