package com.example.demo.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.UserBean;
import com.example.demo.constants.ErrorMessages;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.RequestViolationException;
import com.example.demo.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public String createUser(UserBean user) {
		if("".equals(user.getEmail())){
			throw new RequestViolationException(ErrorMessages.USER_EMAIL_EMPTY.getValue());
		}
		if("".equals(user.getName())){
			throw new RequestViolationException(ErrorMessages.USER_NAME_EMPTY.getValue());
		}
		String status = "FAILED";
		try {
			UserEntity userEntity =new UserEntity();
			userEntity.setEmail(user.getEmail());
			userEntity.setRegisteredOn(new Timestamp(new Date().getTime()));
			userDao.save(userEntity);
			status = "SUCCESS";
		} catch (Exception e) {
			
		}
		return status;
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
