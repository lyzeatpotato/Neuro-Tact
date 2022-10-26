package com.example.neurotact.utils;

/**
 * @author: lyz
 * @date: 2022-10-2022/10/24
 * @description: 统一的工具方法类
 */
public class UnitedUtils {
    public static String[] listReorder(String[] arr, Integer[] arrIdx) {
        String[] arrResult = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrResult[i] = arr[arrIdx[i]];
        }
        return arrResult;
    }
}
