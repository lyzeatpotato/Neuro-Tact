package com.example.neurotact.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Behave {

    private Long id;

    private Long userId;

    private String c_btn1;

    private String s1_btn1;

    private String s1_btn2;

    private String front_label1;

    private String back_label1;

    private String front_label2;

    private String back_label2;

    private String score;

    private String isDel;

    private Date gmtCreate;

    private Date gmtModified;
}
