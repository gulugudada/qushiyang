package com.nengguoqieguo.controller;

import com.nengguoqieguo.service.EnquireService;
import com.nengguoqieguo.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags="咨询类")
@RestController
@CrossOrigin
public class EnquireController {

    @Autowired
    EnquireService enquireService;

    @PostMapping("findEnquire")
    public Result findEnquire(String question){
        String answer = enquireService.findEnquire(question);
        if(answer != null){
            return Result.ok(answer);
        }
        return Result.error("问题太过复杂，小美女表示无能为力。");
    }
}
