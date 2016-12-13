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
