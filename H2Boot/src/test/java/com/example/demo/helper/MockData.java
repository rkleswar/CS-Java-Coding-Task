package com.example.demo.helper;

/**
 * The enum consists of mock data that can be used in test cases.
 * 
 * @author KotilingeswararaoR
 *
 */
public enum MockData {

    SUCCESS("Success"),

    FAILURE("Failure"),

    UTF_8("UTF-8"),

    UESR_ID("1"),

    FOLLOWER_ID("1"),

    FOLLOWEE_ID("2");

    private String data;

    private MockData(String data) {
        this.data = data;
    }

    public String getString() {
        return data;
    }

    public Long getLong() {
        return Long.parseLong(data);
    }

}

