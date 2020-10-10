package com.exam.vo;



public class UserVO {

	private String user_id;
	private int user_pass;
	private String user_name;
	private String user_tel;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	private int rule;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(int user_pass) {
		this.user_pass = user_pass;
	}
	public int getRule() {
		return rule;
	}
	public void setRule(int rule) {
		this.rule = rule;
	}
	
	
	
}
