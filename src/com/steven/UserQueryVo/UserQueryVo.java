package com.steven.UserQueryVo;

import com.steven.usermapper.User;

public class UserQueryVo {
	//��ѯ������
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserQueryVo [user=" + user + "]";
	}
}
