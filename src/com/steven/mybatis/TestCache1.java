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
	//一级缓存
	/**一级缓存是SqlSession级别的缓存。
	 * 在操作数据库时需要构造sqlSession对象，在对象中有一个数据结构（HashMap）用于存储缓存数据。
	 * 不同的sqlSession之间的缓存数据区域（HashMap）是互相不影响的。
	 * 
	 * 
	 *第一次发起查询用户id为1的用户信息，先去找缓存中是否有id为1的用户信息，如果没有，从数据库查询用户信息。得到用户信息，将用户信息存储到一级缓存中。 
	　　如果中间sqlSession去执行commit操作（执行插入、更新、删除），则会清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。 
	　　第二次发起查询用户id为1的用户信息，先去找缓存中是否有id为1的用户信息，缓存中有，直接从缓存中获取用户信息。 
	 */
	@Test
	public void testCacheOne() throws IOException {
		SqlSession sqlSession = getSession(); //调用上面的方法获取sqlSession
		Cuser cuser=sqlSession.selectOne("test.getCUser", 1);
		System.out.println(cuser.toString());
		System.out.println("------------");
		//执行CUD操作

		sqlSession.update("test.updateCUser", new Cuser(1, "Tom", 15));
		sqlSession.commit();
		//		sqlSession.clearCache();

		cuser=sqlSession.selectOne("test.getCUser", 1);
		System.out.println(cuser.toString());



		sqlSession.close();
	}


}
