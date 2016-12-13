package com.steven.mapper;


import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
	
	@Override
	public SUser findUserById(int id) throws Exception {
		  //�̳�SqlSessionDaoSupport��ͨ��this.getSqlSession()���ܵõ�sqlSession��
		 //��ΪSqlSessionDaoSupport���и÷���
        SUser user = getSqlSession().selectOne("test.findById", 1);
        return user;
	}
}
