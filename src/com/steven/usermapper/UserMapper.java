package com.steven.usermapper;

import java.util.List;

import com.steven.UserQueryVo.UserQueryVo;

public interface UserMapper {
	//根据id查询用户信息
	public User findUserById(int id) throws Exception;
	//根据用户名模糊查询
	public List<User> findUserByName(String name) throws Exception;
	//添加用户信息
	public void insertUser(User user) throws Exception;
	//删除用户信息
	public void deleteUser(int id) throws Exception;
	//更新用户信息
	public void updateUser(User user) throws Exception;
	public List<User> findUserList(UserQueryVo queryVo) throws Exception; 
	public List<User> findUserListSQL(UserQueryVo queryVo) throws Exception; 
}
