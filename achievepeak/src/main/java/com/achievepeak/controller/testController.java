package com.achievepeak.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/test")
@Tag(name = "TEST", description = "테스트용 API")  
public class testController {  
  
    @GetMapping("")  
    @Operation(summary = "test", description = "테스트용 API") 
    public String test() {  
        String test = "테스트 입니다.";
        return test;
    }  
}  