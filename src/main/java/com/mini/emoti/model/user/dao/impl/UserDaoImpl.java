package com.mini.emoti.model.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.emoti.model.user.dao.UserDao;
import com.mini.emoti.model.user.entity.UserEntity;
import com.mini.emoti.model.user.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteUser(Long id) {
        // TODO Auto-generated method stub
        userRepository.deleteById(id);
    }
    

    @Override
    public UserEntity findByNickname(String UserName) {
        // TODO Auto-generated method stub
        
        return userRepository.findByNickname(UserName);
    }

    // security 적용 
    @Override
    public void joinUser(UserEntity entity) {
        // TODO Auto-generated method stub
        userRepository.save(entity);
        
    }

    @Override
    public void updateUser(UserEntity entity) {
        // TODO Auto-generated method stub
        userRepository.save(entity);
        
    }
 
    // 로그인 성공시 >> 로그인 유무 저장
    @Override
    public void updateIsLoginByEmail(UserEntity entity) {
        // TODO Auto-generated method stub
        if(entity != null){
        userRepository.save(entity);
        }
    }


    @Override
    public UserEntity findByEmail(String email) {
        // TODO Auto-generated method stub
        return  userRepository.findByEmail(email);
    }
    
}
