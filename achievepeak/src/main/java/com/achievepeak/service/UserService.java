package com.achievepeak.service;  
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.achievepeak.dto.request.SignupReqDTO;
// import com.achievepeak.dto.request.UserUpdateReqDTO;
import com.achievepeak.dto.response.LoginResDTO;
import com.achievepeak.entity.User;
import com.achievepeak.repository.UserRepository;
@Service  
public class UserService {  

    @Autowired  
    private UserRepository userRepository;  
  
    public User createUser(String nickname, String email, String profileImage) {    
        
        User user = new User();  
        user.setNickname(nickname);  
        user.setEmail(email);  
        user.setProfileImage(profileImage);
        user.setCreatedBy(email);  
  
        // User Entity의 @PrePersist 메서드에 의해 생성일이 현재 시간으로 자동 설정
        return userRepository.save(user); 

    }

    // public User updateUser(UserUpdateReqDTO userUpdateReqDTO, String email) {  
    //     User user = userRepository.findByEmail(email);  
    //     if (user != null) {  
    //         if (userUpdateReqDTO.getNickname() != null) {  
    //             user.setNickname(userUpdateReqDTO.getNickname());  
    //         }  
    //         if (userUpdateReqDTO.getIsPublic() != null) {  
    //             user.setIsPublic(userUpdateReqDTO.getIsPublic());  
    //         }  
    //         return userRepository.save(user);  
    //     } else {  
    //         // 예외 처리 또는 오류 응답 리턴  
    //         throw new RuntimeException("User not found with email: " + email);  
    //     } 
    // } 

    public User updateUser(String accessToken, String refreshToken, String email) {  
        User user = userRepository.findByEmail(email);  
        if (user != null) {  
            if (accessToken != null) {  
                user.setAccessToken(accessToken);  
            }  
            if (refreshToken != null) {  
                user.setRefreshToken(refreshToken);  
            }  
            return userRepository.save(user);  
        } else {  
            // 예외 처리 또는 오류 응답 리턴  
            throw new RuntimeException("User not found with email: " + email);  
        } 
    } 

    public User getUserByEmail(String email) {  
        return userRepository.findByEmail(email);  
    } 

    public User getUserByNickname(String nickname) {  
        return userRepository.findByNickname(nickname);  
    } 

    public void deleteUser(User user) {  
        userRepository.delete(user);  
    }  
    
}  