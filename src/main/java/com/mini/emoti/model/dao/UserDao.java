package com.mini.emoti.model.dao;

import com.mini.emoti.model.entity.UserEntity;

public interface UserDao {

    //select
    public UserEntity findByNickname(String UserName) ;

    //insert -> join 
    public void insertUser(UserEntity entity);

    //login 
    public void loginUser(String email, String password);

    //update -> profile edit
    public void updateUser(UserEntity entity);

    //delete 
    public void deleteUser(Long id);
    

    
}
