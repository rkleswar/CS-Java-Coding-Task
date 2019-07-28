package com.example.demo.service;

import com.example.demo.bean.UserRequestBean;

public interface UserService {
	
	public String createUser(UserRequestBean user);
	
	public String getUserById(long userId);
}
