package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

	@Query("SELECT u FROM WallPostEntity u WHERE u.userEntity = :userEntity")
	List<WallPostEntity> findPostsByByUserId(@Param("userEntity") UserEntity userEntity);
	
	@Query(value = "SELECT u FROM WallPostEntity u WHERE u.userEntity IN :userEntity ORDER BY u.postedOn DESC offset=1 limit =3", nativeQuery = true)
	List<WallPostEntity> findPostsByByUserList(@Param("userEntity") List<UserEntity> userEntity);
	
	//@Query(value = "SELECT u FROM WallPostEntity u WHERE u.userEntity IN :userEntity ORDER BY u.postedOn DESC")
	List<WallPostEntity> findFirst10ByUserEntityInOrderByPostedOnDesc(@Param("userEntity") List<UserEntity> userEntity);
	
	//findFirst2ByDeptOrderBySalaryDesc
}


