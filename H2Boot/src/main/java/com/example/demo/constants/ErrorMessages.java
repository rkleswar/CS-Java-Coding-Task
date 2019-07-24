package com.example.demo.constants;

public enum ErrorMessages {
	
	USER_IS_EMPTY("UserId should not be 0 "),
	USER_NAME_EMPTY("User Name should not be empty"),
	USER_EMAIL_EMPTY("User Email should not be empty"),
	USER_NOT_EXIST("User is not avalable in system"),
	FOLLOWER_USER("follower is not avalable in system"),
	FOLLOWEE_USER("followee is not avalable in system");
	
	private String value;

    private ErrorMessages(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
}

