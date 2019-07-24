/**
 * 
 */
package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Following Entity class helps to maintain user feeds data.
 * 
 * @author KotilingeswararaoR
 *
 */

@Entity
@Table(name = "wall_post_info")
public class WallPostEntity implements Serializable {

	private static final long serialVersionUID = -482579746519948977L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wall_post_id_pk")
    private long wallPostId;
    
    @Column(name = "post_id")
    private long postId;

    @Column(name = "wall_post_content")
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private UserEntity userEntity;
    
    
    @Column(name = "posted_on")
    private Timestamp postedOn;

	public WallPostEntity() {
		super();
	}

	public WallPostEntity(long postId, String content, UserEntity userEntity, Timestamp postedOn) {
		super();
		this.postId = postId;
		this.content = content;
		this.userEntity = userEntity;
		this.postedOn = postedOn;
	}


	public long getWallPostId() {
		return wallPostId;
	}


	public void setWallPostId(long wallPostId) {
		this.wallPostId = wallPostId;
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


	public Date getPostedOn() {
		return postedOn;
	}


	public void setPostedOn(Timestamp postedOn) {
		this.postedOn = postedOn;
	}


	public UserEntity getUserEntity() {
		return userEntity;
	}


	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

    
}