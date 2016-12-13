package com.steven.UserQueryVo;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.setven.dao.ItemsMapper;
import com.setven.po.Items;
import com.setven.po.ItemsExample;

public class ItemsMapperTest {
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// 创建sqlSessionFactory
		String resource = "SqlMapConfig.xml"; // mybatis配置文件

		// 得到配置文件的流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂SqlSessionFactory,要传入mybaits的配置文件的流
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}

	//普通的插入，跟我们之前的插入是一样的
	@Test
	public void testInsert() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);

		Items items = new Items();
		items.setName("手机");
		items.setPrice(5000f);
		items.setCreatetime(new Date());
		itemsMapper.insert(items);
		sqlSession.commit();
		//还有一个insertSelective(items);方法，是插入的项不为空时，才将那个字段拼接到sql中
		//可以下自动生成的xml文件就知道了。
	}

	//自定义查询
	@Test
	public void testSelectByExample() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);

		//自定义查询，这就用到了ItemsExample类了，里面有个Criteria内部类，专门用来封装自定义查询条件的
		ItemsExample itemsExample = new ItemsExample();
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		//andNameEqualTo相当于在sql中拼接一个“AND name='背包'”
		//还有其他很多方法，都是用来自定义查询条件的，可以自己看一下不同的方法
		criteria.andNameEqualTo("背包");
		List<Items> itemsList = itemsMapper.selectByExample(itemsExample);
		System.out.println(itemsList);
	}

	//根据主键查询，跟原来一样
	@Test
	public void testSelectByPrimaryKey() {
		ItemsMapper itemsMapper = sqlSessionFactory.openSession().getMapper(ItemsMapper.class);
		Items items = itemsMapper.selectByPrimaryKey(1);
		System.out.println(items);
	}

	//根据主键更新item，跟原来一样
	@Test
	public void testUpdateByPrimaryKey() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		Items items = itemsMapper.selectByPrimaryKey(1);
		items.setPrice(3540f);
		itemsMapper.updateByPrimaryKey(items);
		sqlSession.commit();
	}
}