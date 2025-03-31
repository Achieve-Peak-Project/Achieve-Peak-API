package com.achievepeak;

import io.github.cdimascio.dotenv.Dotenv;  
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest  
class AchievepeakApplicationTests {  
  
    @BeforeAll  
    public static void setUp() {  
        // .env 파일 로드  
        Dotenv dotenv = Dotenv.configure().load();  
  
        // 환경 변수 설정  
		System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));  
        System.setProperty("JWT_SECRET_KEY", dotenv.get("JWT_SECRET_KEY"));  
        System.setProperty("JWT_REFRESH_SECRET_KEY", dotenv.get("JWT_REFRESH_SECRET_KEY"));  
    }  
  
    @Test  
    void contextLoads() {  
    }  
} 
