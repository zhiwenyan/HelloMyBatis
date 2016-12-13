package com.steven.usermapper;

public class User {
	private int Id;
	private String UserName;
	private String PassWord;
	private String sex;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + ", UserName=" + UserName + ", PassWord=" + PassWord + ", sex=" + sex + "]";
	}


}
