package com.steven.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.catalina.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.steven.bean.Classes;
import com.steven.bean.Users;
import com.sun.nio.sctp.SctpStandardSocketOptions.InitMaxStreams;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Mybatis4 {
	//��Ϊ�������Ĳ��Դ����У���ȡsqlSession�ⲿ�ֶ���ͬ�����Գ�ȡ��һ������
	public SqlSession getSession() throws IOException {
		String resource = "SqlMapConfig.xml"; //MyBaits�����ļ�
		//�õ������ļ�����
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//�����Ự����SqlSessionFactory,Ҫ����MyBaits�������ļ�����
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//ͨ�������õ�SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(); //Ĭ�����ֶ��ύ
		return sqlSession;
	}
	
	@Test
	public void getClasses() throws IOException {
		SqlSession sqlSession = getSession(); 
		Classes classes=sqlSession.selectOne("test.getClasses2", 1);
		System.out.println(classes.toString());
	}

}