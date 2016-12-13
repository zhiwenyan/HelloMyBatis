package com.steven.usermapper;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.steven.UserQueryVo.UserQueryVo;

import sun.launcher.resources.launcher;
/**
 *ʹ��Mapper����ķ�ʽ����Dao
 *
 */
public class UserMapperTest {
	private SqlSessionFactory sqlSessionFactory;

	@Before 
	//����sqlSessionFactory
	public void setUp() throws Exception {
		String resource = "SqlMapConfig.xml"; //mybatis�����ļ� 
		//�õ������ļ�����
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//�����Ự����SqlSessionFactory,Ҫ����mybaits�������ļ�����
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);      
	}
	@Test
	public void testFindUserById() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//����UserMapper����mybatis�Զ�����mapper�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}
	//	
	@Test
	public void testFindUserByName() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//����UserMapper����mybatis�Զ�����mapper�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> user = userMapper.findUserByName("s");
		System.out.println(user);
	}

	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//����UserMapper����mybatis�Զ�����mapper�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user=new User();
		user.setSex("Ů");
		user.setUserName("s");
		UserQueryVo queryVo=new UserQueryVo();
		queryVo.setUser(user);
		List<User> userList = userMapper.findUserList(queryVo);
		System.out.println(userList);
	}
	@Test
	public void findUserListSQL() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//����UserMapper����mybatis�Զ�����mapper�������
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user=new User();
		user.setSex("");
		user.setUserName("s");
		UserQueryVo queryVo=new UserQueryVo();
		queryVo.setUser(user);
		List<User> userList = userMapper.findUserListSQL(queryVo);
		System.out.println(userList);
	}

}
