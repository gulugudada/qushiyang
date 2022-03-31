package com.nengguoqieguo.controller;

import com.nengguoqieguo.service.CookbookService;
import com.nengguoqieguo.service.DishService;
import com.nengguoqieguo.service.HomePageService;
import com.nengguoqieguo.service.SeasonTuiJianService;
import com.nengguoqieguo.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags="主页类")
@RestController
@CrossOrigin
public class HomePageController {

    @Autowired
    HomePageService homePageService;

    @Autowired
    CookbookService cookbookService;

    @Autowired
    SeasonTuiJianService seasonTuiJianService;

    @Autowired
    DishService dishService;

    @PostMapping("getWeather")
    public Result getWeather(String city){
//        要去掉双引号
        city = city.replace("\"", "");
        return Result.ok(homePageService.getWeather(city));
    }

    @PostMapping("findAllSeason")
    public Result findAllSeason(){
        return Result.ok(homePageService.findAllSeason());
    }

    @PostMapping("findAllSeasonTuiJian")
    public Result findAllSeasonTuiJian(){
        return Result.ok(homePageService.findAllSeasonTuiJian());
    }

    @PostMapping("findSeasonBySeason")
    public Result findSeasonBySeason(String season){
        return Result.ok(homePageService.findSeasonBySeason(season));
    }

    @PostMapping("findSeasonTuiJianBySeason")
    public Result findSeasonTuiJianBySeason(String season){
        return Result.ok(homePageService.findSeasonTuiJianBySeason(season));
    }

    @PostMapping("findSeasonTuiJianByCookbook")
    public Result findSeasonTuiJianByCookbook(String cookbook){
        return Result.ok(seasonTuiJianService.findSeasonTuiJianByCookbook(cookbook));
    }

    @PostMapping("findCookbookByPhysique")
    public Result findCookbookByPhysique(String physique){
        return Result.ok(cookbookService.findCookbookByPhysique(physique));
    }

    @PostMapping("findDishByDishname")
    public Result findDishByDishname(String dishname){
        return Result.ok(dishService.findDishByDishname(dishname));
    }
}
