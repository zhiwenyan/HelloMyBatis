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
 * 原始Dao开发
 * 
 */
public class MybatisFirst {
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
	//CRUD
	//根据id查询用户的信息，得到一条记录的结果
	@Test
	public void findUserById() throws IOException {
		SqlSession sqlSession = getSession(); //调用上面的方法获取sqlSession
		//通过SqlSession操作数据库
		//第一个参数：映射文件中statement的id，= namespace + statement的id
		//第二个参数：指定和映射文件中所匹配的parameterType类型的参数
		//selectOne表示查询出一条记录进行映射
		Users sql=sqlSession.selectOne("test.findUserById", 15);
		System.out.println(sql);
		int a=sqlSession.insert("test.addUser", new Users("dfa","4646"));
		System.out.println(a);

		int b=sqlSession.delete("test.deleteUser", 6);
		System.out.println(b);
		int m=sqlSession.update("test.updateUser", new Users(3, "stevenyan", "11"));
		System.out.println(m);
		sqlSession.commit();//提交事务
		sqlSession.close();
	}


}
