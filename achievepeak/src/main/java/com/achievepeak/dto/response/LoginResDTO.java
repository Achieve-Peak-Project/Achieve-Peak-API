package com.achievepeak.dto.response;

import lombok.Data;  
import jakarta.validation.constraints.NotNull;

@Data
public class LoginResDTO {

    @NotNull  
    private String email; 
    @NotNull
    private String nickname; 
    private String profileImage;
    @NotNull 
    private String accessToken; 
    @NotNull  
    private String refreshToken;  
    
}
