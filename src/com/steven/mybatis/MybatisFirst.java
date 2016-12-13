package com.steven.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import com.steven.bean.Users;

/**
 * ԭʼDao����
 * 
 */
public class MybatisFirst {
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
	//CRUD
	//����id��ѯ�û�����Ϣ���õ�һ����¼�Ľ��
	@Test
	public void findUserById() throws IOException {
		SqlSession sqlSession = getSession(); //��������ķ�����ȡsqlSession
		//ͨ��SqlSession�������ݿ�
		//��һ��������ӳ���ļ���statement��id��= namespace + statement��id
		//�ڶ���������ָ����ӳ���ļ�����ƥ���parameterType���͵Ĳ���
		//selectOne��ʾ��ѯ��һ����¼����ӳ��
		Users sql=sqlSession.selectOne("test.findUserById", 15);
		System.out.println(sql);
		int a=sqlSession.insert("test.addUser", new Users("dfa","4646"));
		System.out.println(a);

		int b=sqlSession.delete("test.deleteUser", 6);
		System.out.println(b);
		int m=sqlSession.update("test.updateUser", new Users(3, "stevenyan", "11"));
		System.out.println(m);
		sqlSession.commit();//�ύ����
		sqlSession.close();
	}


}
