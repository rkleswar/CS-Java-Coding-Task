package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Following Entity class helps to maintain users data.
 * 
 * @author KotilingeswararaoR
 *
 */

@Entity
@Table(name = "user_info")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -482579746519948977L;
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id_pk")
    private long userId;

    @Column(name = "user_email")
    private String email;
    
    @Column(name = "registred_on")
    private Timestamp registeredOn;

	public UserEntity() {
		super();
	}

	public UserEntity(String email, Timestamp registeredOn) {
		super();
		this.email = email;
		this.registeredOn = registeredOn;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(Timestamp registeredOn) {
		this.registeredOn = registeredOn;
	}
	
	
    
}