package com.example.neurotact.service;

import java.util.List;

public interface DataService {

    /**
     * 获取当前时刻串口读到的最后一列数据（144个）
     * @return 144个串口数据字符
     */
    public List<Float> getCurrentList(String portDescription);

    /**
     * 触发启动串口读取线程
     * @param portDescription
     * @return
     */
    public Object startPortThread(String portDescription) throws InterruptedException;
}
