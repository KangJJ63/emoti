package com.mini.emoti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.mini.emoti.model.dao.UserDao;
import com.mini.emoti.model.dto.UserDto;
import com.mini.emoti.model.entity.UserEntity;
import com.mini.emoti.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

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
    public void insertUser(UserDto dto) throws Exception {
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
        userDao.insertUser(entity);    
        
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
