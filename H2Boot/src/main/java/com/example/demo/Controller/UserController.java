package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.UserRequestBean;
import com.example.demo.service.UserService;

import io.swagger.annotations.ApiOperation;


/**
 * The Class consists of User related rest end-points.
 * 
 * @author KotilingeswararaoR
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {
 
    @Autowired
    private UserService userService;
 
    @ApiOperation(value = "Create User", 
			notes = "This service Create user", 
			response = String.class
	)
    @PostMapping("/users")
    public String createUser(@RequestBody UserRequestBean user) {
        return userService.createUser(user);
    }
    
    @ApiOperation(value = "Fetch User by userId", 
			notes = "This service Fetch User by userId", 
			response = String.class
	)
    @GetMapping("/users/{userId}")
    public String fetchUserById(@PathVariable long userId) {
        return userService.getUserById(userId);
    }
    
}
