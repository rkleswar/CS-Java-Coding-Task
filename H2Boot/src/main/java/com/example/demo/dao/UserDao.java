package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

/**
 * The Following dao interface helps to fetch UserEntity details form the table.
 * 
 * @author KotilingeswararaoR
 *
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

}


