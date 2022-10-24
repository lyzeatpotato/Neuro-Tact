package com.example.neurotact.service.Impl;

import com.example.neurotact.entity.Questionare;
import com.example.neurotact.mapper.QuestionareMapper;
import com.example.neurotact.service.QuestionareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: QuestionareServiceImpl
 * @Description: 问卷Service实现类
 * @author: lyz
 * @date: 2022 10 2022/10/18 19:06
 */

@Service
public class QuestionareServiceImpl implements QuestionareService {

    @Autowired
    QuestionareMapper questionareMapper;

    @Override
    public int addQuestionare(String[] param) {
        Questionare questionare = new Questionare();
        questionare.setUserId(Integer.valueOf(param[0]));
        questionare.setPainCharacteristic(param[1]);
        questionare.setPainScale(param[2]);
        questionare.setEmotionState(param[3]);
        questionare.setIsDel("0");
        Date nowTime = new Date();
        questionare.setGmtCreate(nowTime);
        questionare.setGmtModified(nowTime);
        return questionareMapper.insertQuestionare(questionare);
    }
}
