package com.example.neurotact.common;

import com.example.neurotact.utils.UnitedUtils;
import com.fazecast.jSerialComm.SerialPort;

import static java.lang.Thread.interrupted;

/**
 * @author: lyz
 * @date: 2022-10-2022/10/24
 * @description: 串口读取类，单独的线程
 */

public class PortReader {

    volatile String[] reorderedList;     // 存储最终结果的数组
    private String portDescription;     // 串口名称，如COM5
    private Integer[] reorder_list = new Integer[] {
            0,
            1, 18, 39, 60, 81, 102, 123,
            2, 10, 19, 29, 40, 50, 61, 71, 82, 92, 103, 113, 124, 134,
            6, 14, 24, 34, 45, 55, 66, 76, 87, 97, 108, 118, 129, 139,
            3, 7, 11, 15, 20, 25, 30, 35, 41, 46, 51, 56, 62, 67, 72, 77, 83, 88, 93, 98, 104, 109, 114, 119, 125, 130, 135, 140,
            4, 8, 12, 16, 21, 26, 31, 36, 42, 47, 52, 57, 63, 68, 73, 78, 84, 89, 94, 99, 105, 110, 115, 120, 126, 131, 136, 141,
            5, 9, 13, 17, 22, 27, 32, 37, 43, 48, 53, 58, 64, 69, 74, 79, 85, 90, 95, 100, 106, 111, 116, 121, 127, 132, 137, 142,
            23, 28, 33, 38, 44, 49, 54, 59, 65, 70, 75, 80, 86, 91, 96, 101, 107, 112, 117, 122, 128, 133, 138, 143
    };      // 读取串口数据后重新排列

    public PortReader(String portDescription) {
        this.portDescription = portDescription;
    }

    public String[] getReorderedList() {
        return reorderedList;
    }

    public void getPortData() {
        SerialPort[] commPorts = SerialPort.getCommPorts();
        for (SerialPort commPort : commPorts) {
            System.out.println("Port：" + commPort.getSystemPortName());
            System.out.println("PortMsg:" + commPort.getPortDescription());
        }
        SerialPort comPort = SerialPort.getCommPort(portDescription);
        System.out.println(comPort.getSystemPortPath());
        comPort.setBaudRate(115200);
        comPort.openPort();
        new Thread(() -> {
            byte[] readBuffer = new byte[723];
            while (!Thread.currentThread().isInterrupted()) {
                if (comPort.bytesAvailable() != 0) {
                    int numRead = comPort.readBytes(readBuffer, 723);
                    String getStr;
                    try {
                        getStr = new String(readBuffer, "ascii");
                        getStr = getStr.substring(1, getStr.length() - 3);
                        String[] strList = getStr.split(",");
                        if (strList.length == 143 || strList.length == 144) {
                            reorderedList = UnitedUtils.listReorder(strList, reorder_list);
//                            System.out.println(Arrays.asList(reorderedList));
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                if (interrupted()) {
                    // 若当前线程被主动关闭则直接停止，并抛出异常从而直接中断while循环
                    Thread.currentThread().interrupt();
                    System.out.println("线程停止");
                    return;
                }
                // comPort.closePort();
            }
        }, "portThread").start();
    }
}
