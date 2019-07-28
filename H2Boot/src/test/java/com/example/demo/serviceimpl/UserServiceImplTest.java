package com.example.demo.serviceimpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.bean.UserRequestBean;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	@InjectMocks
    private UserServiceImpl userServiceImpl;

	@Mock
	private UserDao userDao;
	
	@Test
	public void test_createUser() {
		UserRequestBean user = new UserRequestBean("koti","koti@yopmail.com");
		
		// when
        String actualResult = userServiceImpl.createUser(user);
        // then
        assertThat(actualResult).isEqualTo("SUCCESS");
	}

	@Test
	public void test_getUserById() {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(1l);
		userEntity.setEmail("rkl.eswar@gmail.com");

		long userPk = 1;
		Optional<UserEntity> expectedResult = Optional.of(userEntity);
		
		when(userDao.findById(userPk)).thenReturn(expectedResult);

		// when
		String actualResult = userServiceImpl.getUserById(userPk);
        // then
        assertThat(actualResult).isEqualTo(expectedResult.get().getEmail());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void test_getUserById_Exception() {

		long userPk = 1;
		Optional<UserEntity> expectedResult = Optional.ofNullable(null);
		
		when(userDao.findById(userPk)).thenReturn(expectedResult);
		
		// when
		userServiceImpl.getUserById(userPk);
	}

}
