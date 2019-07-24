/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.FeedBean;
import com.example.demo.bean.PostBean;

/**
 * @author KotilingeswararaoR
 *
 */
public interface WallPostService {

	public String createPost(PostBean postBean);
	
	public List<FeedBean> getNewsFeed(long userId);
	
	public boolean follow(long followerId,long followeeId);
	
	public boolean unFollow(long followerId,long followeeId);
	
}
