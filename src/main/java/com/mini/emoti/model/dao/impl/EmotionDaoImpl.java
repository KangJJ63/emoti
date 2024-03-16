package com.mini.emoti.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.emoti.model.dao.EmotionDao;
import com.mini.emoti.model.entity.EmotionEntity;
import com.mini.emoti.model.repository.EmotionRepository;


@Service
public class EmotionDaoImpl implements EmotionDao{

    @Autowired
    private EmotionRepository emotionRepository;

    // delete
    @Override
    public void deleteEmotion(Long emotionId) {
        emotionRepository.deleteById(emotionId);
    }

    // select
    @Override
    public EmotionEntity findByEmotionId(Long emotionId) {
        return emotionRepository.findByEmotionId(emotionId);
    }

    // insert
    @Override
    public void isertEmotion(EmotionEntity entity) {
        emotionRepository.save(entity);
    }

    // update
    @Override
    public void updateEmotion(EmotionEntity entity) {
        emotionRepository.save(entity);
    }
}
