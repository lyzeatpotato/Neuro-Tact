package com.example.neurotact.service.Impl;

import com.example.neurotact.entity.Behave;
import com.example.neurotact.mapper.BehaveMapper;
import com.example.neurotact.service.BehaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class BehaveServiceImpl implements BehaveService {

    @Resource
    BehaveMapper behaveMapper;

    @Override
    public int saveUserBehaveData(String[] param) {
        Behave behave = new Behave();
        behave.setUserId(Long.valueOf(param[0]));
        behave.setC_btn1(param[1]);
        behave.setS1_btn1(param[2]);
        behave.setS1_btn2(param[3]);
        behave.setFront_label1(param[4]);
        behave.setBack_label1(param[5]);
        behave.setFront_label2(param[6]);
        behave.setBack_label2(param[7]);
        behave.setScore(param[8]);
        behave.setIsDel("0");
        Date nowTime = new Date();
        behave.setGmtCreate(nowTime);
        behave.setGmtModified(nowTime);
        return behaveMapper.save(behave);
    }
}
