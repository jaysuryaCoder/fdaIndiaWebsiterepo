package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserKendraDataDto {
    private Long userId;
    private String name;
    private String email;
    private String username;
    private String mobile;
    private String state;
    private Long token;
    private Long total_amount;
}