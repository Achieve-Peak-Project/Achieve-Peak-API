package com.achievepeak.entity;  

import java.time.LocalDateTime; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
  
@Entity  
@Data
@Table(name = "users")  
public class User {  
  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
  
    @Column(name = "nickname", nullable = false, length = 100)  
    private String nickname;  
  
    @Column(name = "email", nullable = false, length = 255)  
    private String email;  
  
    @Column(name = "profile_image", nullable = true, length = 4000)  
    private String profileImage;  
  
    @Column(name = "access_token", nullable = true, length = 4000)  
    private String accessToken;  
  
    @Column(name = "refresh_token", nullable = true, length = 4000)  
    private String refreshToken;  

    @Column(nullable = false, length = 100)  
    private String createdBy;  
  
    @Column(nullable = false)  
    private LocalDateTime createdDate;  
  
    @Column(nullable = false)  
    private LocalDateTime updatedDate;  
 
    @PrePersist  
    protected void onCreate() {  
        LocalDateTime nowKST = LocalDateTime.now();    
        createdDate = nowKST;  
        updatedDate = nowKST;  
    }  
  
    @PreUpdate  
    protected void onUpdate() {  
        updatedDate = LocalDateTime.now();  
    }  
  
}  
