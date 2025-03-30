package com.achievepeak.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.achievepeak.dto.response.LoginResDTO;
import com.achievepeak.dto.request.LoginReqDTO;
import com.achievepeak.dto.response.KakaoUserResDTO;

import com.achievepeak.entity.User;
import com.achievepeak.service.TokenService;
import com.achievepeak.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "AUTH", description = "로그인, 토큰 발급 등 인증 관련 API")  
public class authController {

    @Autowired  
    private UserService userService; 
    
    @Autowired  
    private TokenService tokenService; 

    @PostMapping("/login")  
    @Operation(summary = "login", description = "카카오 로그인 API")
    public ResponseEntity<LoginResDTO> addUser(@Validated @RequestBody LoginReqDTO loginReqDTO) {  

        // Kakao API를 사용하기 위해 RestTemplate 객체 생성  
        RestTemplate restTemplate = new RestTemplate();  
  
        String url = "https://kapi.kakao.com/v2/user/me";  
          
        // HTTP headers 설정  
        HttpHeaders headers = new HttpHeaders();  
        headers.set("Authorization", "Bearer " + loginReqDTO.getAccessToken());  
          
        HttpEntity<String> entity = new HttpEntity<>(headers);  
  
        // Kakao API 호출 및 응답 받기  
        ResponseEntity<KakaoUserResDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, KakaoUserResDTO.class);  
          
        // 응답을 바탕으로 사용자 정보를 UserAddReqDTO 객체에 매핑  
        KakaoUserResDTO kakaoUser = response.getBody();  
          
        if (kakaoUser == null) {  
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);  
        }  

        String email = kakaoUser.getKakao_account().getEmail();
        String nickname = kakaoUser.getKakao_account().getProfile().getNickname();
        String profileImage = kakaoUser.getKakao_account().getProfile().getProfile_image_url();
        // 카카오 이메일로 회원가입 여부 조회  
        User user = userService.getUserByEmail(email);

        // 사용자 인증을 위한 데이터  
        Map<String, Object> data = new HashMap<>();  
        data.put("email", email); 
        
        if (user == null) {
            user = userService.createUser(nickname, email, profileImage);
        }

        // Access Token과 Refresh Token 생성  
        String accessToken = tokenService.createToken(data, "access");  
        String refreshToken = tokenService.createToken(data, "refresh"); 
        
        user = userService.updateUser(accessToken, refreshToken, email);

        LoginResDTO loginResDTO = new LoginResDTO();
        loginResDTO.setNickname(user.getNickname());
        loginResDTO.setEmail(user.getEmail());
        loginResDTO.setProfileImage(user.getProfileImage());
        loginResDTO.setAccessToken(user.getAccessToken());
        loginResDTO.setRefreshToken(user.getRefreshToken());

        return new ResponseEntity<>(loginResDTO, HttpStatus.CREATED);
    }
    
}
