package com.mini.emoti.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.mini.emoti.config.constant.ExceptionConstant;
import com.mini.emoti.config.error.EmotionException;
import com.mini.emoti.model.user.dao.UserDao;
import com.mini.emoti.model.user.dto.UserDto;
import com.mini.emoti.model.user.entity.UserEntity;
import com.mini.emoti.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 로그인 성공시 >> 로그인 유무 저장
    @Override
    public void updateIsLoginByEmail(String email, Boolean isLogin) throws Exception {
        // TODO Auto-generated method stub
        UserEntity entity = userDao.findByEmail(email);
        log.info("[UserServiceImpl][updateIsLoginByEmail] "+email+" / "+entity);
        entity.setIsLogin(isLogin);
        userDao.updateIsLoginByEmail(entity);
    }

    @Override
    public void deleteUser(String userName) throws Exception{
        // TODO Auto-generated method stub
        UserEntity entity = userDao.findByNickname(userName);
        userDao.deleteUser(entity.getUserId());

    }

    @Override
    public UserDto findByUserName(String userName) throws Exception{
        // TODO Auto-generated method stub
        UserEntity entity = userDao.findByNickname(userName);

        UserDto dto = new UserDto();
        dto.setCommentCnt(entity.getCommentCnt());
        dto.setEmail(entity.getEmail());
        dto.setEmotionCnt(entity.getEmotionCnt());
        dto.setNickname(entity.getNickname());
        dto.setPostCnt(entity.getPostCnt());
        dto.setProfileImage(entity.getProfileImage());

        return dto;
    }


    @Override
    public void joinUser(UserDto dto) throws Exception {
        // TODO Auto-generated method stub
        log.info("[UserServiceImpl][insertUser] "+dto);

        UserEntity entity = new UserEntity();
        entity.setCommentCnt(dto.getCommentCnt());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setEmotionCnt(dto.getEmotionCnt());
        entity.setNickname(dto.getNickname());
        entity.setPostCnt(dto.getPostCnt());
        entity.setProfileImage(dto.getProfileImage());

        // 비밀번호 암호화 적용
        String rawPwd = entity.getPassword();
        String encodedPwd = bCryptPasswordEncoder.encode(rawPwd);
        entity.setPassword(encodedPwd);
        
        log.info("[UserServiceImpl][insertUser] - rawPwd : "+rawPwd+" / encodedPwd : "+ encodedPwd);
        log.info("[UserServiceImpl][insertUser] - entity : "+entity.toString());
        

        entity.setIsLogin(false);
        
        // 신규 유저 database에 저장
        userDao.joinUser(entity);    
        
    }

    @Override
    public void updateUser(UserDto dto) throws Exception{
        // TODO Auto-generated method stub
        UserEntity entity = userDao.findByNickname(dto.getNickname());
        entity.setCommentCnt(dto.getCommentCnt());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setEmotionCnt(dto.getEmotionCnt());
        entity.setNickname(dto.getNickname());
        entity.setPostCnt(dto.getPostCnt());
        entity.setProfileImage(dto.getProfileImage());
        userDao.updateUser(entity); 
        
    }

    @Override
    public void loginUser(String email, String password) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
}
