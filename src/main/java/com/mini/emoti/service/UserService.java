package com.mini.emoti.service;

import com.mini.emoti.model.dto.UserDto;

public interface UserService {

    // 가입 insert
    public void insertUser(UserDto dto) throws Exception;

    // 로그인 login 
    public void loginUser(String email, String password) throws Exception;

    // 조회 select
    public UserDto findByUserName(String userName) throws Exception;

    // 프로필 수정 update
    public void updateUser(UserDto dto) throws Exception;

    // 회원 탈퇴 delete
    public void deleteUser(String userName) throws Exception;

    
    
}
