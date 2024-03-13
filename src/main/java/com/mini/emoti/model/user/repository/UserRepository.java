package com.mini.emoti.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mini.emoti.model.user.dto.UserDto;
import com.mini.emoti.model.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    public UserEntity findByNickname(String UserName);

    public UserEntity findByEmail(String email);


    // @Query(value = "select * from user where name = :name", nativeQuery = true)
    // public UserDto getUserDtoByName(@Param(value = "name") String name);

}
