package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {

    private Integer status;
    private String message;
    private Throwable throwable;
}
