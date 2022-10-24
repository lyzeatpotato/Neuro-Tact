package com.example.neurotact.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: Questionare
 * @Description: 最后的问卷实体类
 * @author: lyz
 * @date: 2022 10 2022/10/18 18:57
 */

@Data
public class Questionare {

    private Integer id;

    private Integer userId;

    private String painCharacteristic;

    private String painScale;

    private String emotionState;

    private String isDel;

    private Date gmtCreate;

    private Date gmtModified;
}
