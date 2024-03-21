package com.mini.emoti.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.emoti.model.entity.PostEntity;


public interface PostRepository extends JpaRepository<PostEntity, Long>{
// public PostEntity findByCreatedDateAndUserUserId(LocalDateTime createDate, Long userId);

} 