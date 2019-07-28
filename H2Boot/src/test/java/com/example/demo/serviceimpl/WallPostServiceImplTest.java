package com.example.demo.serviceimpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.bean.PostRequestBean;
import com.example.demo.bean.PostResponseBean;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserFollowerDao;
import com.example.demo.dao.WallPostDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserFollower;
import com.example.demo.entity.WallPostEntity;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.helper.MockData;
import com.example.demo.service.impl.WallPostServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class WallPostServiceImplTest {
	
	@InjectMocks
    private WallPostServiceImpl wallPostServiceImpl;

	@Mock
	private UserDao userDao;
	
	@Mock
	private UserFollowerDao userFollowerDao;
	
	@Mock
	private WallPostDao wallPostDao;
	
	@Test
	public void test_createPost() {
		PostRequestBean postBean = new PostRequestBean(1,1,"My Content");
		
		Optional<UserEntity> expectedUserEntityResult = Optional.of(new UserEntity());
		when(userDao.findById(postBean.getUserId())).thenReturn(expectedUserEntityResult);

		WallPostEntity wallPostEntity = new WallPostEntity(1, "My Post", expectedUserEntityResult.get() ,new Timestamp(new Date().getTime()));
		when(wallPostDao.save(wallPostEntity)).thenReturn(wallPostEntity);
		
		// when
        String actualResult = wallPostServiceImpl.createPost(postBean);
        // then
        assertThat(actualResult).isEqualTo("SUCCESS");
	}
	
	@Test(expected = UserNotFoundException.class)
	public void test_createPost_Exception() {
		PostRequestBean postBean = new PostRequestBean(1,1,"My Content");
		Optional<UserEntity> expectedResult = Optional.ofNullable(null);
		
		when(userDao.findById(postBean.getUserId())).thenReturn(expectedResult);
		
		// when
		wallPostServiceImpl.createPost(postBean);
	}
	
	@Test
	public void test_follow() {
		long followerId=MockData.FOLLOWER_ID.getLong(); 
		long followeeId=MockData.FOLLOWEE_ID.getLong();
		UserEntity userEntity = new UserEntity("rkl.eswar@gmail.com",null);

		Optional<UserEntity> expectedFollowerIdResult = Optional.of(userEntity);
		when(userDao.findById(followerId)).thenReturn(expectedFollowerIdResult);

		Optional<UserEntity> expectedFolloweeIdResult = Optional.of(userEntity);
		when(userDao.findById(followeeId)).thenReturn(expectedFolloweeIdResult);

		// when
		boolean actualResult = wallPostServiceImpl.follow(followerId, followeeId);
        // then
        assertThat(actualResult).isEqualTo(true);
	}

	@Test(expected = UserNotFoundException.class)
	public void test_follow_exception() {
		long followerId=MockData.FOLLOWER_ID.getLong(); 
		long followeeId=MockData.FOLLOWEE_ID.getLong();
		
		Optional<UserEntity> expectedFollowerIdResult = Optional.ofNullable(null);
		when(userDao.findById(followerId)).thenReturn(expectedFollowerIdResult);
		
		Optional<UserEntity> expectedFolloweeIdResult = Optional.ofNullable(null);
		when(userDao.findById(followeeId)).thenReturn(expectedFolloweeIdResult);
		
		// when
		wallPostServiceImpl.follow(followerId, followeeId);
	}
	
	@Test
	public void test_unFollow() {
		long followerId=MockData.FOLLOWER_ID.getLong(); 
		long followeeId=MockData.FOLLOWEE_ID.getLong();
		UserEntity userEntity = new UserEntity("rkl.eswar@gmail.com",null);

		Optional<UserEntity> expectedFollowerIdResult = Optional.of(userEntity);
		when(userDao.findById(followerId)).thenReturn(expectedFollowerIdResult);

		Optional<UserEntity> expectedFolloweeIdResult = Optional.of(userEntity);
		when(userDao.findById(followeeId)).thenReturn(expectedFolloweeIdResult);

		// when
		boolean actualResult = wallPostServiceImpl.follow(followerId, followeeId);
        // then
        assertThat(actualResult).isEqualTo(true);
	}

	@Test(expected = UserNotFoundException.class)
	public void test_unFollow_exception() {
		long followerId=MockData.FOLLOWER_ID.getLong(); 
		long followeeId=MockData.FOLLOWEE_ID.getLong();
		
		Optional<UserEntity> expectedFollowerIdResult = Optional.ofNullable(null);
		when(userDao.findById(followerId)).thenReturn(expectedFollowerIdResult);
		
		Optional<UserEntity> expectedFolloweeIdResult = Optional.ofNullable(null);
		when(userDao.findById(followeeId)).thenReturn(expectedFolloweeIdResult);
		
		// when
		wallPostServiceImpl.follow(followerId, followeeId);
	}

	@Test
	public void test_getNewsFeed() {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(1);
		userEntity.setEmail("rkl.eswar@gmail.com");

		long userPk = 1;
		Optional<UserEntity> expectedResult = Optional.of(userEntity);
		when(userDao.findById(userPk)).thenReturn(expectedResult);
		
		List<UserFollower> expected_userFollowerlist = prepare_UserFollowerList();
		when(userFollowerDao.findByFollowerId(expectedResult.get())).thenReturn(expected_userFollowerlist);
		
		List<UserEntity> followerlist = expected_userFollowerlist.stream().map(item -> item.getFollowerId()).collect(Collectors.toList());
		
		List<WallPostEntity> expected_wallPostEntityList = prepare_WallPostEntityList();
		when(wallPostDao.findFirst20ByUserEntityInOrderByPostedOnDesc(followerlist)).thenReturn(expected_wallPostEntityList);

		List<PostResponseBean> listOfFeeds = expected_wallPostEntityList
				.stream().map(item -> new PostResponseBean(item.getUserEntity().getUserId(),
						item.getUserEntity().getEmail(), item.getPostId(), item.getContent()))
				.collect(Collectors.toList());

		// when
		List<PostResponseBean> actualPostResponse = wallPostServiceImpl.getNewsFeed(1l);
        // then
		assertThat(actualPostResponse.size() == listOfFeeds.size());
		assertThat(actualPostResponse.size()).isLessThanOrEqualTo(20);

	}

	private List<UserFollower> prepare_UserFollowerList() {
		UserEntity userEntity1 = new UserEntity("rkl.eswar1@gmail.com", null);
		UserEntity userEntity2 = new UserEntity("rkl.eswar2@gmail.com", null);
		UserEntity userEntity3 = new UserEntity("rkl.eswar3@gmail.com", null);
		
		//List<UserFollower> userFollowerlist = new ArrayList<>();
		UserFollower userFollower1 = new UserFollower(1l, userEntity1, userEntity2, true, null);
		UserFollower userFollower2 =new UserFollower(2l, userEntity1, userEntity3, true, null);
				
		List<UserFollower> userFollowerlist = Arrays.asList(userFollower1,userFollower2);
		return userFollowerlist;
	}
	
	private List<WallPostEntity> prepare_WallPostEntityList() {
		UserEntity userEntity1 = new UserEntity("rkl.eswar1@gmail.com", null);
		UserEntity userEntity2 = new UserEntity("rkl.eswar2@gmail.com", null);
		UserEntity userEntity3 = new UserEntity("rkl.eswar3@gmail.com", null);
		
		//List<UserFollower> userFollowerlist = new ArrayList<>();
		WallPostEntity wallPostEntity1 = new WallPostEntity(1l, "", userEntity1, null);
		WallPostEntity wallPostEntity2 = new WallPostEntity(2l, "", userEntity2, null);
		WallPostEntity wallPostEntity3 = new WallPostEntity(2l, "", userEntity3, null);
				
		List<WallPostEntity> wallPostEntityList = Arrays.asList(wallPostEntity1, wallPostEntity2, wallPostEntity3);
		return wallPostEntityList;
	}
}
