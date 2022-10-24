package com.example.neurotact.controller;

import com.example.neurotact.common.ResultEntity;
import com.example.neurotact.service.UserService;
import com.example.neurotact.utils.BasicResponseUtils;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "0.用户登录")
@ApiSupport(order = 1)
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    @ApiOperation(value = "用户注册", notes = "接口返回当前新注册用户的id，如返回-1表示当前注册所用的邮箱已被占用")
    @ApiOperationSupport(order = 1)
    @GetMapping("register")
    public ResultEntity userRegister(@RequestParam("name") String name, @RequestParam("birth") String birth,
                                     @RequestParam("gender") String gender, @RequestParam("email") String email){
        String[] param = new String[]{name, birth, gender, email};
        return BasicResponseUtils.success(userService.userRegister(param));
    }

    @ApiOperation(value = "检查邮箱是否已被占用", notes = "接口返回true表示当前邮箱未使用，可以用于注册; 接口返回false表示当前邮箱已使用，不能用于注册;")
    @ApiOperationSupport(order = 2)
    @GetMapping("isMailUsed")
    public ResultEntity isMailUsed(@RequestParam("email") String email){
        return BasicResponseUtils.success(userService.isMailLogin(email));
    }

    @ApiOperation(value = "根据邮箱号查询用户信息")
    @ApiOperationSupport(order = 3)
    @GetMapping("findByEmail")
    public ResultEntity findUserByEmail(@RequestParam("email") String email){
        return BasicResponseUtils.success(userService.findByMail(email));
    }
}
