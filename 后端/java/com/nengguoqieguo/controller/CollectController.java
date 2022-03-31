package com.nengguoqieguo.controller;

import com.nengguoqieguo.entity.Collect;
import com.nengguoqieguo.service.*;
import com.nengguoqieguo.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags="收藏类")
@RestController
@CrossOrigin
public class CollectController {

    @Autowired
    CollectService collectService;

    @Autowired
    DishService dishService;

    @Autowired
    SeasonTuiJianService seasonTuiJianService;

    @Autowired
    ChineseMedicineService chineseMedicineService;

    @PostMapping("findAllCollect")
    public Result findAllCollect(){
        return Result.ok(collectService.findAllCollect());
    }

    @PostMapping("findCollectByUsername")
    public Result findCollectByUsername(String username){
        List<Collect> collectList = collectService.findCollectByUsername(username);
        List<Collect> dishList = new ArrayList<>();
        List<Collect> seasontuijianList = new ArrayList<>();
        List<Collect> druglistList = new ArrayList<>();
        for(int i = 0;i<collectList.size();i++){
            if(collectList.get(i).getType().equals("dish")){
                dishList.add(collectList.get(i));
            }
            else if(collectList.get(i).getType().equals("seasontuijian")){
                seasontuijianList.add(collectList.get(i));
            }
            else if(collectList.get(i).getType().equals("druglist")){
                druglistList.add(collectList.get(i));
            }
        }
        Result result = new Result();
        result.put("season",seasontuijianList);
        result.put("tizhi",dishList);
        result.put("drug",druglistList);
        return result;
    }

    @PostMapping("findCollectByDishname")
    public Result findCollectByDishname(String username,String dishname){
        String type = collectService.findTypeByUsernameDishname(username,dishname);
        System.out.println(type);
        switch (type){
            case "dish":return Result.ok(dishService.findDishByDishname(dishname));
            case "seasontuijian":return Result.ok(seasonTuiJianService.findSeasonTuiJianByCookbook(dishname));
            case "druglist":return Result.ok(chineseMedicineService.FindMassage(dishname));
        }
        return Result.error();
    }

    @PostMapping("addCollect")
    public Result addCollect(String username,String dishname,String type){
        Collect collect = new Collect(username,dishname,type);
//        switch (type){
//            case "dish":System.out.println(dishService.findImageByDishname(dishname));image = "" + dishService.findImageByDishname(dishname);
//            case "seasontuijian":image = seasonTuiJianService.findImageByCookbook(dishname);
//            case "druglist":image = chineseMedicineService.findImageByChineseName(dishname);
//        }
        if(type.equals("dish")){
            collect.setImage(dishService.findImageByDishname(dishname));
        }
        else if(type.equals("seasontuijian")){
            collect.setImage(seasonTuiJianService.findImageByCookbook(dishname));
        }
        else if(type.equals("druglist")){
            collect.setImage(chineseMedicineService.findImageByChineseName(dishname));
        }
        System.out.println(collect.getImage());
        if(collectService.addCollect(collect) > 0){
            return Result.ok("收藏成功");
        }
        return Result.error("收藏失败");
    }

    @PostMapping("deleteCollect")
    public Result deleteCollect(String username,String dishname){
        if(collectService.deleteCollect(username,dishname) > 0){
            return Result.ok("取消收藏成功");
        }
        return Result.error("取消收藏失败");
    }

    @PostMapping("isCollect")
    public boolean isCollect(String username,String dishname){
        if(collectService.findIsCollectByUsernameDishname(username,dishname) > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
