package com.nengguoqieguo.controller;

import com.nengguoqieguo.service.PhysiqueService;
import com.nengguoqieguo.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 颜自翔
 * @since 2021-12-08
 */
@Api(tags="体质信息类")
@RestController
@CrossOrigin
public class PhysiqueController {

    @Autowired
    PhysiqueService physiqueService;

//    得到9种体质
    @PostMapping("findAllPhy")
    public Result findAllPhy(){
        return Result.ok(physiqueService.findAllPhy());
    }

//    得到9种体质的详细情况
    @PostMapping("findAllPhysique")
    public Result findAllPhysique(){
        return Result.ok(physiqueService.findAllPhysique());
    }

    @PostMapping("findPhysique")
    public Result findPhysique(String physiquename){
        return Result.ok(physiqueService.findPhysique(physiquename));
    }

}

