package com.example.demo.response;


import lombok.Data;

@Data
public class UserResponse {
	private boolean status;
    private String message;
    private Object object;

}
