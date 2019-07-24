package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author KotilingeswararaoR
 *	This Is Custom Exception
 *	By Using "@ResponseStatus" We Can Bind Our Exception Class To Revert "404 Not Found" Status To API Caller
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException  {
	
	private static final long serialVersionUID = 1L;
	
	private String reason;
	
	public UserNotFoundException(String exception) {
		super(exception);
	}
	
	public UserNotFoundException(String exception,String reason) {
		super(exception);
		this.reason = reason;
	}
	
	public String getReason() {
		return reason;
	}
	
}

