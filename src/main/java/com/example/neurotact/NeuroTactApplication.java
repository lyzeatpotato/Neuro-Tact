package com.example.neurotact;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.neurotact.mapper")
public class NeuroTactApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeuroTactApplication.class, args);
    }
}
