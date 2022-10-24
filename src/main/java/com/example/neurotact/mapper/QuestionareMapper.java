package com.example.neurotact.mapper;

import com.example.neurotact.entity.Questionare;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionareMapper {

    @Insert({
            "insert into user_questionare" +
                    "(userid, pain_characteristic, pain_scale, emotion_state, isDel, gmt_create, gmt_modified)" +
                    "values (#{userId,jdbcType=BIGINT}, #{painCharacteristic,jdbcType=VARCHAR}," +
                    "#{painScale,jdbcType=VARCHAR}, #{emotionState,jdbcType=VARCHAR}," +
                    "#{isDel,jdbcType=CHAR}, #{gmtCreate,jdbcType=DATE}, #{gmtModified,jdbcType=DATE})"
    })
    int insertQuestionare(Questionare questionare);
}
