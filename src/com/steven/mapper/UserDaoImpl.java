package com.steven.mapper;


import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
	
	@Override
	public SUser findUserById(int id) throws Exception {
		  //继承SqlSessionDaoSupport，通过this.getSqlSession()就能得到sqlSession，
		 //因为SqlSessionDaoSupport中有该方法
        SUser user = getSqlSession().selectOne("test.findById", 1);
        return user;
	}
}
