package com.example.demo.utils;

import java.util.Random;

public class EmployeeCodeGenerator {

    private static final String PREFIX = "FDAI";

    public static String generateEmployeeCode() {
        Random random = new Random();
        int number = random.nextInt(9000) + 1000; 
        return PREFIX + number;
    }
}