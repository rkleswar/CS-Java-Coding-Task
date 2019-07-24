package com.example.demo.bean;

import java.io.Serializable;

public class PostBean implements Serializable {

	private static final long serialVersionUID = -482579746519948977L;
    
    private long userId;

	private long postId;
    
    private String content;

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
