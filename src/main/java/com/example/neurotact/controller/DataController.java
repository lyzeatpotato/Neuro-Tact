package com.example.neurotact.controller;

import com.example.neurotact.common.PortReader;
import com.example.neurotact.common.ResultEntity;
import com.example.neurotact.service.DataService;
import com.example.neurotact.utils.BasicResponseUtils;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lyz
 * @date: 2022-10-2022/10/24
 * @description: 用户数据相关接口
 */

@Api(tags = "2.用户数据")
@ApiSupport(order = 2)
@RestController
@RequestMapping("data")
public class DataController {

    @Autowired
    DataService dataService;

    @ApiOperation(value = "获取当前串口读入的数据", notes = "返回一个list")
    @ApiOperationSupport(order = 1)
    @GetMapping("getCurrentPortList")
    public ResultEntity getCurrentPortList(@RequestParam("current_port_name") String portDescription) throws InterruptedException {
        return BasicResponseUtils.success(dataService.getCurrentList(portDescription));
    }

    @ApiOperation(value = "启动串口读取线程")
    @ApiOperationSupport(order = 2)
    @GetMapping("startPortThread")
    public ResultEntity startPortThread(@RequestParam("current_port_name") String portDescription) throws InterruptedException {
        return BasicResponseUtils.success(dataService.startPortThread(portDescription));
    }
}
