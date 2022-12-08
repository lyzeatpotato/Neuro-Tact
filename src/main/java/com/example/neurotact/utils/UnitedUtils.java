package com.example.neurotact.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author: lyz
 * @date: 2022-10-2022/10/24
 * @description: 统一的工具方法类
 */
public class UnitedUtils {

    /**
     * 根据指定索引位置，重新排列原始数组
     * @param arr    原数组
     * @param arrIdx 索引位置数组
     * @return String类型的重排后的数组
     */
    public static String[] listReorder(String[] arr, Integer[] arrIdx) {
        String[] arrResult = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrResult[i] = arr[arrIdx[i]];
        }
        return arrResult;
    }

    /**
     * 根据新传入的R值数组修改坐标点
     * @param pointArr 坐标点数组
     * @param portArr 串口数据
     * @return 根据串口更新后的坐标点
     */
    public Float[] reorderPointList(Float[] pointArr, Float[] portArr) {
        // 首先需要根据传入的串口数据，得到球坐标系的theta/beta值
        Float[][] polarData = new Float[pointArr.length][2];  // 一共144 * 2(每个点对应一个theta跟一个beta)
        try {
            String path = "/txt/polar.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(path)));
            String lineTxt = null;
            int count = 0;
            // 逐行读取
            while ((lineTxt = br.readLine()) != null) {
                // 输出内容到控制台
                String[] names = lineTxt.split(" ");
                for (int i = 0; i < names.length; i++) {
                    polarData[count][i] = Float.parseFloat(names[i]);
                }
                count++;
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Float[] resultPointArr = new Float[pointArr.length];
        for (int i = 0; i < portArr.length; i++) {  //144次点循环
            // 分别更新x,y,z的值
            float theta = polarData[i][0];
            float beta = polarData[i][1];
            resultPointArr[i * 3 + 0] = new Float(pointArr[i * 3 + 0] + portArr[i] * Math.sin(theta) * Math.cos(beta) * 1.5 * 100);
            resultPointArr[i * 3 + 1] = new Float(pointArr[i * 3 + 1] + portArr[i] * Math.sin(theta) * Math.sin(beta) * 1.5 * 100);
            resultPointArr[i * 3 + 2] = new Float(pointArr[i * 3 + 2] + portArr[i] * Math.cos(theta) * 1.5 * 100);
        }
        return resultPointArr;
    }
}
