package com.nengguoqieguo.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.nengguoqieguo.entity.ChineseMedicine;
import com.nengguoqieguo.service.ChineseMedicineService;
import com.nengguoqieguo.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="中医药品类")
@RestController
@CrossOrigin
public class ChineseMedicineController {
    @Autowired
    ChineseMedicineService chineseMedicineService;

    //查看药物详细信息
    @PostMapping("FindMagess")
    public Result FindMagess(String ChineseName){
        ChineseName = ChineseName.replace("\"","");
        ChineseMedicine chineseMedicine = chineseMedicineService.FindMassage(ChineseName);
        Result result = new Result();
        result.put("ChineseName",chineseMedicine.getChineseName());
        result.put("Alias",chineseMedicine.getAlias());
        result.put("MedicinalParts",chineseMedicine.getMedicinalParts());
        result.put("OriginDistribution",chineseMedicine.getOriginDistribution());
        result.put("MedicinalProperties",chineseMedicine.getMedicinalProperties());
        result.put("EfficacyAndFunction",chineseMedicine.getEfficacyAndFunction());
        result.put("ClinicalApplication",chineseMedicine.getClinicalApplication());
        result.put("UseTaboo",chineseMedicine.getUseTaboo());
        result.put("Image",chineseMedicine.getImage());
        result.put("Medicinalclass",chineseMedicine.getMedicinalclass());
        return result;
    }

    //药品模糊查询
    @PostMapping("getLike")
    public Result getLike(String NameLike){
        NameLike = NameLike.replace("\"","");
        return Result.ok(chineseMedicineService.getMedicineLike(NameLike));
    }

    //药品分类查询
    @PostMapping("getMedicineClass")
    public Result getMedicineClass(String Class){
        Class = Class.replace("\"","");
        return Result.ok(chineseMedicineService.getMedicineClass(Class));
    }

    //返回所有数据
    @GetMapping("findAllDate")
    public Result findAllDate(){
        return Result.ok(chineseMedicineService.findAllDate());
    }

    //返回固定数量的数据,传入的数据从1开始
    @PostMapping("queryMedicineByArray")
    public List<ChineseMedicine> queryMedicineByArray(int currPage){
        List<ChineseMedicine> medicines = chineseMedicineService.queryMedicineByArray(currPage);
            return medicines;
    }
}
