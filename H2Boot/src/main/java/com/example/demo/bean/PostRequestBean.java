package com.example.demo.bean;

import java.io.Serializable;

public class PostRequestBean implements Serializable {

	private static final long serialVersionUID = -482579746519948977L;
    
    private long userId;

	private long postId;
    
    private String content;

    public PostRequestBean() {
		super();
	}

	public PostRequestBean(long userId, long postId, String content) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.content = content;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
