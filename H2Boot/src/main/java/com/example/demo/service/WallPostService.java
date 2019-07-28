/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.PostResponseBean;
import com.example.demo.bean.PostRequestBean;

/**
 * @author KotilingeswararaoR
 *
 */
public interface WallPostService {

	public String createPost(PostRequestBean postBean);
	
	public List<PostResponseBean> getNewsFeed(long userId);
	
	public boolean follow(long followerId,long followeeId);
	
	public boolean unFollow(long followerId,long followeeId);
	
}
