package com.example.neurotact.service;

import org.springframework.stereotype.Service;

@Service
public interface BehaveService {

    // 将用户行为数据保存
    int saveUserBehaveData(String[] param);
}
