package com.mini.emoti.model.post.entity;

import java.util.List;

import com.mini.emoti.config.BaseEntity;
import com.mini.emoti.model.user.entity.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "postEntity")
@Table(name = "post")
public class PostEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private UserEntity user; // FK 
    @Column(length = 300, name="content")
    private String content;
    @PositiveOrZero
    @Column(name = "licke_cnt", columnDefinition = "int default 0")
    private int likeCnt;
    @PositiveOrZero
    @Column(name = "hate_cnt", columnDefinition = "int default 0")
    private int hateCnt;
    
}