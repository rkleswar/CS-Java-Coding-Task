package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserFollower;

/**
 * The Following dao interface helps to fetch UserFollower details form the table.
 * 
 * @author KotilingeswararaoR
 *
 */
@Repository
public interface UserFollowerDao extends JpaRepository<UserFollower, Long> {

	 List<UserFollower> findByFollowerId(@Param("followerId") UserEntity followerId);

	 @Transactional
	 @Modifying
	 @Query("UPDATE UserFollower u set u.isFollow = :isFollow where u.followerId = :followerId and u.followeeId = :followeeId")
	 int updateUserFollowStatus(@Param("followerId") UserEntity followerId, @Param("followeeId") UserEntity followeeId, @Param("isFollow") boolean isFollow);
	 
}


