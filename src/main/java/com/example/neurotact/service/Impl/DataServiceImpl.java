package com.example.neurotact.service.Impl;

import com.example.neurotact.common.PortReader;
import com.example.neurotact.service.DataService;
import com.example.neurotact.utils.UnitedUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lyz
 * @date: 2022-10-2022/10/24
 * @description: 用户数据传输业务逻辑实现类
 */

@Service
public class DataServiceImpl implements DataService {
    private static PortReader portReader;

    @Override
    public List<Float> getCurrentList(String portDescription) {

        String[] portData = portReader.getReorderedList();
        // 对获取到的String数据转成Float
        Float[] resPortData = new Float[portData.length];
        for (int i = 0; i < portData.length; i++) {
            resPortData[i] = 1 - Float.parseFloat(portData[i]);
        }

        // 加载原始点坐标数组
        Float[] fileData = new Float[144 * 3];  // 一共144 * (3个坐标值)
        try {
            String path = "/txt/surfacexyz.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(path)));
            String lineTxt = null;
            int count = 0;
            // 逐行读取
            while ((lineTxt = br.readLine()) != null) {
                String[] names = lineTxt.split(" ");
                // 设置点的坐标值
                for (int i = 0; i < names.length; i++) {
                    fileData[count*3 + i] = Float.parseFloat(names[i]);
                }
                count++;
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UnitedUtils utils = new UnitedUtils();
        resPortData = utils.reorderPointList(fileData, resPortData);
        //System.out.println("原始坐标点数组：" + Arrays.asList(fileData));
        //System.out.println("修改后坐标点数组：" + Arrays.asList(resPortData));

        return Arrays.asList(resPortData);
    }

    @Override
    public Object startPortThread(String portDescription) throws InterruptedException {
        portReader = new PortReader("COM5");
        // 启动线程类开始读取串口数据
        portReader.getPortData();
        Thread.sleep(100);
        return null;
    }
}
