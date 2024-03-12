package com.mini.emoti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mini.emoti.config.constant.ExceptionConstant;
import com.mini.emoti.config.error.EmotionException;
import com.mini.emoti.model.dto.UserDto;
import com.mini.emoti.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    // 회원가입
    // localhost:8080/api/v1/user/join
    @PostMapping("/join")
    public ResponseEntity<String> joinUser(@Valid @RequestBody UserDto dto) throws Exception {
        
        if(dto.getNickname() == null){
            throw new EmotionException(ExceptionConstant.USER_NOT_FOUND);
        }

        userService.insertUser(dto);
        return ResponseEntity.ok("가입 성공");
    }

    // 유저 조회
    // localhost:8080/api/v1/user/{userName}
    @GetMapping("/{userName}")
    public ResponseEntity<String> selectUser(@PathVariable("userName") String userName) throws Exception {
        
        return ResponseEntity.ok(userService.findByUserName(userName).toString());
    }

    // 삭제
    // localhost:8080/api/v1/user/{userName}
    @DeleteMapping("/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable("userName") String userName) throws Exception {

        userService.deleteUser(userName);
        return ResponseEntity.ok("삭제 성공");
    }

    // 프로필 수정 
    // localhost:8080/api/v1/user/join
    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDto dto) throws Exception {
        
        if(dto.getNickname() == null){
            throw new EmotionException(ExceptionConstant.INVALID_REQUEST);
        }

        userService.updateUser(dto);

        return ResponseEntity.ok("업데이트 성공");
    }
}
