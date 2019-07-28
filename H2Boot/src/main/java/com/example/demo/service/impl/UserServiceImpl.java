package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.UserRequestBean;
import com.example.demo.constants.ErrorMessages;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.RequestViolationException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public String createUser(UserRequestBean user) {
		if("".equals(user.getEmail())){
			throw new RequestViolationException(ErrorMessages.USER_EMAIL_EMPTY.getValue());
		}
		if("".equals(user.getName())){
			throw new RequestViolationException(ErrorMessages.USER_NAME_EMPTY.getValue());
		}
		UserEntity userEntity =new UserEntity(user.getEmail(),new Timestamp(new Date().getTime()));
		userDao.save(userEntity);
		return "SUCCESS";
	}

	@Override
	public String getUserById(long userId) {
		Optional<UserEntity> user = userDao.findById(userId);
		if(user.isPresent()){
			return user.get().getEmail();
		}else{
			throw new UserNotFoundException(ErrorMessages.USER_NOT_EXIST.getValue());
		}
	}

}
