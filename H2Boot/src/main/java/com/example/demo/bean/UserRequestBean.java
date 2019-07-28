package com.example.demo.bean;

import java.io.Serializable;

public class UserRequestBean implements Serializable {

	private static final long serialVersionUID = -482579746519948977L;
    
    private String email;
    
    private String name;
    
	public UserRequestBean() {
		super();
	}

	public UserRequestBean(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
