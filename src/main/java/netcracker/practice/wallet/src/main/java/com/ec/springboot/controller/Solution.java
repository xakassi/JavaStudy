package com.ec.springboot.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ec.springboot")
public class Solution {
    public static void main(String[] args) {
        SpringApplication.run(Solution.class, args);
    }
}
