/**
 * 
 */
package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Following Entity class helps to maintain follower and followees data.
 * 
 * @author KotilingeswararaoR
 *
 */

@Entity
@Table(name = "follower_info")
public class UserFollower implements Serializable {

	private static final long serialVersionUID = -482579746519948977L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pk")
    private long Id;

    @ManyToOne
    @JoinColumn(name = "follower_id_fk")
    private UserEntity followerId;
    
    @ManyToOne
    @JoinColumn(name = "followee_id_fk")
    private UserEntity followeeId;
    

    @Column(name = "is_follow")
    private boolean isFollow;
    
    @Column(name = "posted_on")
    private Timestamp fallowedOn;

	public UserFollower() {
		super();
	}

	public UserFollower(long id, UserEntity followerId, UserEntity followeeId, boolean isFollow, Timestamp fallowedOn) {
		super();
		Id = id;
		this.followerId = followerId;
		this.followeeId = followeeId;
		this.isFollow = isFollow;
		this.fallowedOn = fallowedOn;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}


	public UserEntity getFollowerId() {
		return followerId;
	}

	public void setFollowerId(UserEntity followerId) {
		this.followerId = followerId;
	}

	public UserEntity getFolloweeId() {
		return followeeId;
	}

	public void setFolloweeId(UserEntity followeeId) {
		this.followeeId = followeeId;
	}

	public boolean isFollow() {
		return isFollow;
	}

	public void setFollow(boolean isFollow) {
		this.isFollow = isFollow;
	}

	public Timestamp getFallowedOn() {
		return fallowedOn;
	}

	public void setFallowedOn(Timestamp fallowedOn) {
		this.fallowedOn = fallowedOn;
	}

    
}