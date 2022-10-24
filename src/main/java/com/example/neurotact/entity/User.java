package com.example.neurotact.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;

    private String name;

    private String birth;

    private String gender;

    private String email;

    private String isDel;

    private Date gmtCreate;

    private Date gmtModified;
}
