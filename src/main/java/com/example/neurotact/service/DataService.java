package com.example.neurotact.service;

import com.example.neurotact.common.PortReader;

import java.util.List;

public interface DataService {

    /**
     * 获取当前时刻串口读到的最后一列数据（144个）
     * @return 144个串口数据字符
     */
    public List<String> getCurrentList(String portDescription) throws InterruptedException;
}
