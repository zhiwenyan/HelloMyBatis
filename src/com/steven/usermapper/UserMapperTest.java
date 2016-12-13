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
 *使用Mapper代理的方式代替Dao
 *
 */
public class UserMapperTest {
	private SqlSessionFactory sqlSessionFactory;

	@Before 
	//创建sqlSessionFactory
	public void setUp() throws Exception {
		String resource = "SqlMapConfig.xml"; //mybatis配置文件 
		//得到配置文件的流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂SqlSessionFactory,要传入mybaits的配置文件的流
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);      
	}
	@Test
	public void testFindUserById() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}
	//	
	@Test
	public void testFindUserByName() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> user = userMapper.findUserByName("s");
		System.out.println(user);
	}

	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user=new User();
		user.setSex("女");
		user.setUserName("s");
		UserQueryVo queryVo=new UserQueryVo();
		queryVo.setUser(user);
		List<User> userList = userMapper.findUserList(queryVo);
		System.out.println(userList);
	}
	@Test
	public void findUserListSQL() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mybatis自动生成mapper代理对象
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
