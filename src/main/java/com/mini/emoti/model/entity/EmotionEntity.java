package com.mini.emoti.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.mini.emoti.config.BaseEntity;
import com.mini.emoti.config.constant.EmotionTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "emotionEntity")
@Table(name = "emotion")
public class EmotionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emotionId;
    @ManyToOne(cascade = CascadeType.ALL) // 다대일 관계 
    @JoinColumn(name = "user_id") // 컬럼명 
    private UserEntity users; //테이블 명
    @NotBlank
    private EmotionTypes emotionType; 
 
    // @CreatedDate
    // @Column(name = "created_date", updatable = false)
    // private LocalDateTime createdDate;
}
