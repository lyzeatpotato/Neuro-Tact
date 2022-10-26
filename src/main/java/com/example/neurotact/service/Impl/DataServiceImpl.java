package com.example.neurotact.service.Impl;

import ch.qos.logback.core.util.TimeUtil;
import com.example.neurotact.common.PortReader;
import com.example.neurotact.service.DataService;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.neurotact.utils.UnitedUtils.listReorder;

/**
 * @author: lyz
 * @date: 2022-10-2022/10/24
 * @description: 用户数据传输业务逻辑实现类
 */

@Service
public class DataServiceImpl implements DataService {

    PortReader portReader = new PortReader("COM5");

    @Override
    public List<String> getCurrentList(String portDescription) throws InterruptedException {

        // 启动线程类开始读取串口数据
        portReader.getPortData();
        Thread.sleep(100);
        String[] portData = portReader.getReorderedList();
//        System.out.println("当前请求接口获取到的线程内容为：" + Arrays.asList(portData));
//        System.out.println("串口读取线程是否已经停止1：" + portReader.isInterrupted());
//        System.out.println("串口读取线程是否已经停止2：" + portReader.isInterrupted());
        return Arrays.asList(portData);
    }
}
