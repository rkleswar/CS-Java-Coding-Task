package com.example.demo.service;

import com.example.demo.bean.UserBean;

public interface UserService {
	
	public String createUser(UserBean user);
	
	public String getUserById(long userId);
}
