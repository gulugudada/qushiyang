package com.nengguoqieguo.controller;

import com.nengguoqieguo.dao.FanKuiMapper;
import com.nengguoqieguo.entity.Education;
import com.nengguoqieguo.entity.FanKui;
import com.nengguoqieguo.entity.User;
import com.nengguoqieguo.service.UserService;
import com.nengguoqieguo.utils.Constellation;
import com.nengguoqieguo.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@Api(tags="用户信息类")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FanKuiMapper fanKuiMapper;

    @PostMapping("login")
    public Result login(){
        return Result.ok();
    }

    @PostMapping("findAllUser")
    public Result findAllUser(){
        return Result.ok(userService.findAllUser());
    }

    @PostMapping("findUserByUsername")
    public Result findUserByUsername(String username){
        return Result.ok(userService.findUserByUsername(username));
    };

    @PostMapping("addUser")
    public Result addUser(String username,Date birthday,char sex,String school,String grade,String college,String administrativeclass,String location,String physique){
        User user = new User();
        user.setUsername(username);
        user.setPhysique(physique);
        Education education = new Education();
        education.setSchool(school);
        education.setGrade(grade);
        education.setCollege(college);
        education.setAdministrativeclass(administrativeclass);
        user.setUsername(username);
        user.setBirthday(birthday);
        user.setSex(sex);
        user.setConstellation(Constellation.getConstellation(birthday));
        user.setEducation(education);
        user.setLocation(location);
        user.setPhysique(physique);
        user.setTouxiang("/image/touxiang/lalala.jpg");
        if(userService.addUser(user) >0){
            return Result.ok(user);
        }
        return Result.error(-1,"添加失败");
    }

    @PostMapping("addUser2")
    public Result addUser2(String username,String physique){
        User user = new User();
        user.setUsername(username);
        user.setPhysique(physique);
        user.setTouxiang("/image/touxiang/lalala.jpg");
        if(userService.addUser2(user) >0){
            return Result.ok(user);
        }
        return Result.error(-1,"添加失败");
    }

    @PostMapping("updateUser")
    public Result updateUser(String username,Date birthday,char sex,String school,String grade,String college,String administrativeclass,String location,String physique){
        System.out.println(birthday);
        User user = new User();
        Education education = new Education();
        education.setSchool(school);
        education.setGrade(grade);
        education.setCollege(college);
        education.setAdministrativeclass(administrativeclass);
        user.setUsername(username);
        user.setBirthday(birthday);
        user.setSex(sex);
        user.setConstellation(Constellation.getConstellation(birthday));
        user.setEducation(education);
        user.setLocation(location);
        user.setPhysique(physique);
        if(userService.updateUser(user) >0){
            return Result.ok(user);
        }
        return Result.error(-1,"添加失败");
    }

    @PostMapping("updatePhysique")
    public Result updatePhysique(String username,String physique){
        if(userService.updatePhysique(physique,username) >0){
            return Result.ok("修改成功");
        }
        return Result.error(-1,"修改失败");
    }

    @PostMapping("addFanKui")
    public String addFanKui(String username,String fankui){
        FanKui fanKui = new FanKui();
        fanKui.setUsername(username);
        fanKui.setFankui(fankui);
        if(fanKuiMapper.addFanKui(fanKui) > 0){
            return "反馈成功";
        }
        return "反馈失败";
    }
}
