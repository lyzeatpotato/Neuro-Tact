package com.example.neurotact.controller;

import com.example.neurotact.common.ResultEntity;
import com.example.neurotact.service.BehaveService;
import com.example.neurotact.utils.BasicResponseUtils;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "1.用户行为")
@ApiSupport(order = 2)
@RestController
@RequestMapping("behave")
public class BehaveController {

    @Resource
    BehaveService behaveService;

    @ApiOperation(value = "用户行为数据保存", notes = "接口返回当前新注册用户的id，如返回-1表示当前注册所用的邮箱已被占用")
    @ApiOperationSupport(order = 1)
    @GetMapping("save")
    public ResultEntity saveUserBehaveData(@RequestParam("userid") String userid, @RequestParam("c_btn1") String c_btn1,
                                           @RequestParam("s1_btn1") String s1_btn1, @RequestParam("front_label1") String front_label1,
                                           @RequestParam("back_label1") String back_label1, @RequestParam("front_label2") String front_label2,
            @RequestParam("back_label2") String back_label2, @RequestParam("s1_btn2") String s1_btn2, @RequestParam("score") String score) {
        String[] params = new String[]{userid, c_btn1, s1_btn1, s1_btn2, front_label1, back_label1, front_label2, back_label2, score};
        return BasicResponseUtils.success(behaveService.saveUserBehaveData(params));
    }
}
