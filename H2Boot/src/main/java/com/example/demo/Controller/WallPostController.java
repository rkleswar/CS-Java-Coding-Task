package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.FeedBean;
import com.example.demo.bean.PostBean;
import com.example.demo.service.WallPostService;

import io.swagger.annotations.ApiOperation;

/**
 * The Class consists of Posts related rest end-points.
 * 
 * @author KotilingeswararaoR
 *
 */
@RestController
@RequestMapping("/api")
public class WallPostController {
 
    @Autowired
    private WallPostService wallPostService;
 
	@ApiOperation(value = "Create Posts", 
			notes = "This service Create Posts", 
			response = String.class
	)
    @PostMapping("/posts")
    public String createWallPost(@RequestBody PostBean postBean) {
        return wallPostService.createPost(postBean);
    }
    
	@ApiOperation(value = "Featch latest 20 feeds by userId", 
			notes = "This service Fetch the most recent 20 posts in the user's news", 
			response = List.class
	)
    @GetMapping("/posts/{userId}")
    public List<FeedBean> getNewsFeed(@RequestParam long userId) {
        return wallPostService.getNewsFeed(userId);
    }
    
	@ApiOperation(value = "Follower follows a followee", 
			notes = "This service follows a followee", 
			response = Boolean.class
	)
    @PostMapping("/follow/{followerId}/{followeeId}")
    public boolean follow(@PathVariable long followerId, @PathVariable long followeeId) {
        return wallPostService.follow(followerId, followeeId);
    }

	@ApiOperation(value = "Follower unfollows a followee", 
			notes = "This service unfollows a followee", 
			response = Boolean.class
	)
    @PutMapping("/unfollow/{followerId}/{followeeId}")
    public boolean unFollow(@PathVariable long followerId, @PathVariable long followeeId) {
    	return wallPostService.unFollow(followerId,followeeId);
    }
 
}
