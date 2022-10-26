package com.example.neurotact;

import com.example.neurotact.common.PortReader;
import com.example.neurotact.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NeuroTactApplicationTests {

    @Autowired
    DataService dataService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testPortsReading () throws InterruptedException {
        List<String> com5 = dataService.getCurrentList("COM5");
        com5.forEach(System.out::println);
    }

    @Test
    public void testThreadVar() {
        InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set("主线程设置的值");
        new Thread(() -> {
            System.out.println("子线程通过InheritableThreadLocal获取主线程的值:" + inheritableThreadLocal.get());

        }).start();
        System.out.println("主线程通过InheritableThreadLocal获取值:" + inheritableThreadLocal.get());
    }
}
