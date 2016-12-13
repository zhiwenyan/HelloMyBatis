package com.steven.mapper;

public interface UserDao {
	//根据用户id查询用户信息
	public SUser findUserById(int id) throws Exception;
}
