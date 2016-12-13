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

public class TestCache1 {
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
	//һ������
	/**һ��������SqlSession����Ļ��档
	 * �ڲ������ݿ�ʱ��Ҫ����sqlSession�����ڶ�������һ�����ݽṹ��HashMap�����ڴ洢�������ݡ�
	 * ��ͬ��sqlSession֮��Ļ�����������HashMap���ǻ��಻Ӱ��ġ�
	 * 
	 * 
	 *��һ�η����ѯ�û�idΪ1���û���Ϣ����ȥ�һ������Ƿ���idΪ1���û���Ϣ�����û�У������ݿ��ѯ�û���Ϣ���õ��û���Ϣ�����û���Ϣ�洢��һ�������С� 
	��������м�sqlSessionȥִ��commit������ִ�в��롢���¡�ɾ������������SqlSession�е�һ�����棬��������Ŀ��Ϊ���û����д洢�������µ���Ϣ����������� 
	�����ڶ��η����ѯ�û�idΪ1���û���Ϣ����ȥ�һ������Ƿ���idΪ1���û���Ϣ���������У�ֱ�Ӵӻ����л�ȡ�û���Ϣ�� 
	 */
	@Test
	public void testCacheOne() throws IOException {
		SqlSession sqlSession = getSession(); //��������ķ�����ȡsqlSession
		Cuser cuser=sqlSession.selectOne("test.getCUser", 1);
		System.out.println(cuser.toString());
		System.out.println("------------");
		//ִ��CUD����

		sqlSession.update("test.updateCUser", new Cuser(1, "Tom", 15));
		sqlSession.commit();
		//		sqlSession.clearCache();

		cuser=sqlSession.selectOne("test.getCUser", 1);
		System.out.println(cuser.toString());



		sqlSession.close();
	}


}
