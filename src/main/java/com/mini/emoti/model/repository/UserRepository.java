package com.mini.emoti.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.emoti.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    public UserEntity findByNickname(String UserName);

}
