package com.mini.emoti.model.user.dao;

import com.mini.emoti.model.user.entity.UserEntity;

public interface UserDao {

    //select
    public UserEntity findByNickname(String UserName) ;

    //join 
    public void joinUser(UserEntity entity);


    //update -> profile edit
    public void updateUser(UserEntity entity);

    //delete 
    public void deleteUser(Long id);
    
    // login 성공 확인 
    public void updateIsLoginByEmail(UserEntity entity);

    public UserEntity findByEmail(String email) ;


    
}
