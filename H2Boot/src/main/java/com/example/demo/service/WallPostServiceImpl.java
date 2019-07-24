/**
 * 
 */
package com.example.demo.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.bean.FeedBean;
import com.example.demo.bean.PostBean;
import com.example.demo.constants.ErrorMessages;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserFollowerDao;
import com.example.demo.dao.WallPostDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserFollower;
import com.example.demo.entity.WallPostEntity;
import com.example.demo.exception.RequestViolationException;
import com.example.demo.exception.UserNotFoundException;

/**
 * @author KotilingeswararaoR
 *
 */
@Service
public class WallPostServiceImpl implements WallPostService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private WallPostDao wallPostDao;
	
	@Autowired
	private UserFollowerDao userFollowerDao;

	@Override
	public String createPost(@RequestBody PostBean postBean) {
		
		Optional<UserEntity> user = userDao.findById(postBean.getUserId());
		
		if(user.isPresent()){
			WallPostEntity wallPost = new WallPostEntity(postBean.getPostId(), postBean.getContent(), user.get(), new Timestamp(new Date().getTime()));
			/*wallPost.setUserEntity( user.get());
			wallPost.setPostId(postBean.getPostId());
			wallPost.setContent(postBean.getContent());
			wallPost.setPostedOn(new Timestamp(new Date().getTime()));*/
			
			wallPostDao.save(wallPost);
			return "SUCCESS";
		}else{
			throw new UserNotFoundException(ErrorMessages.USER_NOT_EXIST.getValue());
		}
		
	}

	@Override
	public List<FeedBean> getNewsFeed(long userId) {
		
		Optional<UserEntity> user= userDao.findById(userId);
		if(!user.isPresent()){
			throw new UserNotFoundException(ErrorMessages.USER_NOT_EXIST.getValue());
		}
		List<UserFollower> userFollowerlist = userFollowerDao.findByFollwees(user.get());
		List<UserEntity> followerlist = userFollowerlist.stream().map(item -> item.getFollowerId()).collect(Collectors.toList());
		List<WallPostEntity> postsList = wallPostDao.findFirst10ByUserEntityInOrderByPostedOnDesc(followerlist);
		List<FeedBean> listOfFeeds = postsList.stream().map(item -> new FeedBean(item.getUserEntity().getUserId(),item.getUserEntity().getEmail(),item.getPostId(),item.getContent())).collect(Collectors.toList());
		
		return listOfFeeds;
	}
	
	@Override
	public boolean follow(long followerId,long followeeId) {
		
		if (followerId == 0 || followeeId == 0) {
			throw new RequestViolationException(ErrorMessages.USER_IS_EMPTY.getValue());
		}
		
		Optional<UserEntity> followerUser= userDao.findById(followerId);
		if(!followerUser.isPresent()){
			throw new UserNotFoundException(ErrorMessages.FOLLOWER_USER.getValue());
		}
		
		Optional<UserEntity> followeeUser= userDao.findById(followeeId);
		if(!followeeUser.isPresent()){
			throw new UserNotFoundException(ErrorMessages.FOLLOWEE_USER.getValue());
		}
		
		try {
			UserFollower userFollower = new UserFollower();
			userFollower.setFollow(true);
			userFollower.setFollowerId(followerUser.get());
			userFollower.setFolloweeId(followeeUser.get());
			userFollower.setFallowedOn(new Timestamp(new Date().getTime()));
			
			userFollower = userFollowerDao.save(userFollower);
			
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	@Override
	public boolean unFollow(long followerId,long followeeId) {
		
		Optional<UserEntity> followerUser= userDao.findById(followerId);
		if(!followerUser.isPresent()){
			throw new UserNotFoundException(ErrorMessages.FOLLOWER_USER.getValue());
		}
		Optional<UserEntity> followeeUser= userDao.findById(followeeId);
		if(!followeeUser.isPresent()){
			throw new UserNotFoundException(ErrorMessages.FOLLOWEE_USER.getValue());
		}
		try {
			userFollowerDao.updateUserFollowStatus(followerUser.get(),followeeUser.get(),false);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
