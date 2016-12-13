package com.steven.usermapper;

import java.util.List;

import com.steven.UserQueryVo.UserQueryVo;

public interface UserMapper {
	//����id��ѯ�û���Ϣ
	public User findUserById(int id) throws Exception;
	//�����û���ģ����ѯ
	public List<User> findUserByName(String name) throws Exception;
	//����û���Ϣ
	public void insertUser(User user) throws Exception;
	//ɾ���û���Ϣ
	public void deleteUser(int id) throws Exception;
	//�����û���Ϣ
	public void updateUser(User user) throws Exception;
	public List<User> findUserList(UserQueryVo queryVo) throws Exception; 
	public List<User> findUserListSQL(UserQueryVo queryVo) throws Exception; 
}
