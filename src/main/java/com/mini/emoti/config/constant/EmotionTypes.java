package com.mini.emoti.config.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmotionTypes {

    // - 감정 imotiType (int)
    // - 1 = happy
    // - 2 = calm
    // - 3 = soso
    // - 4 = sad
    // - 5 = angry

    HAPPY("happy", "행복"),
    CALM("calm", "평온"),
    SOSO("soso","보통"),
    SAD("sad","슬픔"),
    ANGRY("angry","화남");

    private String emotionType;
    private String emotionMsg;
    
    
}
