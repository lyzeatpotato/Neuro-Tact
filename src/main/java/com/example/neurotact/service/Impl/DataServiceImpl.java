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

    PortReader portReader = new PortReader("COM5");

    @Override
    public List<Float> getCurrentList(String portDescription) throws InterruptedException {

        // 启动线程类开始读取串口数据
        portReader.getPortData();
        Thread.sleep(100);
        String[] portData = portReader.getReorderedList();
        // 对获取到的String数据转成Float
        Float[] resPortData = new Float[portData.length];
        for (int i = 0; i < portData.length; i++) {
            resPortData[i] = 1 - Float.parseFloat(portData[i]);
        }

        // 加载原始点坐标数组
        Float[] fileData = new Float[144 * 8];  // 一共144 * (3个坐标值 + 1个法向量)
        try {
            String path = "E:\\Codestation\\Java\\Workspace\\Neuro-Tact\\src\\main\\resources\\txt\\surfacexyz.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
            String lineTxt = null;
            int count = 0;
            // 逐行读取
            while ((lineTxt = br.readLine()) != null) {
                String[] names = lineTxt.split(" ");
                // 设置点的坐标值
                for (int i = 0; i < names.length; i++) {
                    fileData[count*8 + i] = Float.parseFloat(names[i]) / 100;
                }
                fileData[count*8 + 3] = Float.valueOf(1);
                // 设置点的颜色值与当前点坐标值相同
                for (int i = 0; i < names.length; i++) {
                    fileData[count*8 + 4 + i] = Float.parseFloat(names[i]) / 100;
                }
                //fileData[count*8 + 4] = Float.valueOf(0);
                //fileData[count*8 + 5] = Float.valueOf((float) 0.74902);
                //fileData[count*8 + 6] = Float.valueOf(1);
                fileData[count*8 + 7] = Float.valueOf(1);
                count++;
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resPortData = UnitedUtils.reorderPointList(fileData, resPortData);
        //System.out.println("原始坐标点数组：" + Arrays.asList(fileData));
        //System.out.println("修改后坐标点数组：" + Arrays.asList(resPortData));

        return Arrays.asList(resPortData);
    }
}
