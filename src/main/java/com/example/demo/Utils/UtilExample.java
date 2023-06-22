package com.example.demo.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilExample {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123"));
    }
}
