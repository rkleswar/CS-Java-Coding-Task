package com.example.demo.bean;

import java.io.Serializable;

public class PostResponseBean implements Serializable {

	private static final long serialVersionUID = -482579746519948977L;
    
    private long userId;
    
    private String userEmailId;

	private long postId;
    
    private String content;
    
	public PostResponseBean() {
		super();
	}

	public PostResponseBean(long userId, String userEmailId, long postId, String content) {
		super();
		this.userId = userId;
		this.userEmailId = userEmailId;
		this.postId = postId;
		this.content = content;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
}
