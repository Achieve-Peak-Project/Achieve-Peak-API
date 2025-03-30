package com.achievepeak.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;  

@Data 
public class LoginReqDTO {
    @NotBlank  
    private String accessToken;   
}
