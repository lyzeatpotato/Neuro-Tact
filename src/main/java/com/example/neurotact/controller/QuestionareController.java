package com.example.neurotact.controller;

import com.example.neurotact.common.ResultEntity;
import com.example.neurotact.service.QuestionareService;
import com.example.neurotact.utils.BasicResponseUtils;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: QuestionareController
 * @Description: 调查问卷最后暴露接口
 * @author: lyz
 * @date: 2022 10 2022/10/18 19:09
 */

@Api(tags = "3.调差问卷")
@ApiSupport(order = 3)
@RestController
@RequestMapping("questionare")
public class QuestionareController {

    @Resource
    QuestionareService questionareService;

    @ApiOperation(value = "新增调查问卷信息", notes = "用户名-疼痛信息-疼痛等级-情绪状态")
    @ApiOperationSupport(order = 1)
    @PostMapping("addQuestionare")
    public ResultEntity addQuestionare(@RequestParam("userid") String userId, @RequestParam("pain_characteristic") String painCharacter,
                                       @RequestParam("pain_scale") String painScale, @RequestParam("emotion_state") String emotionState) {
        String[] params = new String[] {userId, painCharacter, painScale, emotionState};
        return BasicResponseUtils.success(questionareService.addQuestionare(params));
    }
}
