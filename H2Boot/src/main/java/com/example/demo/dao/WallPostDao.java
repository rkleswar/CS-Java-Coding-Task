package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;
import com.example.demo.entity.WallPostEntity;

/**
 * The Following dao interface helps to fetch WallPostEntity details form the table.
 * 
 * @author KotilingeswararaoR
 *
 */
@Repository
public interface WallPostDao extends JpaRepository<WallPostEntity, Long> {

	List<WallPostEntity> findByUserEntity(@Param("userEntity") UserEntity userEntity);
	
	List<WallPostEntity> findFirst20ByUserEntityInOrderByPostedOnDesc(@Param("userEntity") List<UserEntity> userEntity);
	
}


