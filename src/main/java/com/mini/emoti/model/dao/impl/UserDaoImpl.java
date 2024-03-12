package com.mini.emoti.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.emoti.model.dao.UserDao;
import com.mini.emoti.model.entity.UserEntity;
import com.mini.emoti.model.repository.UserRepository;

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

    @Override
    public void insertUser(UserEntity entity) {
        // TODO Auto-generated method stub
        userRepository.save(entity);
        
    }

    @Override
    public void updateUser(UserEntity entity) {
        // TODO Auto-generated method stub
        userRepository.save(entity);


        
    }

    @Override
    public void loginUser(String email, String password) {
        // TODO Auto-generated method stub
        
    }
    
}
