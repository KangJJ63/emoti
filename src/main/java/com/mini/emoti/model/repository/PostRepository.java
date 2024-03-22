package com.mini.emoti.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mini.emoti.model.entity.PostEntity;


public interface PostRepository extends JpaRepository<PostEntity, Long>{
// public PostEntity findByCreatedDateAndUserUserId(LocalDateTime createDate, Long userId);

@Transactional
public void deleteByPostId(Long postId);

} 