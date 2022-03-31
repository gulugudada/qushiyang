package com.nengguoqieguo.controller;

import com.nengguoqieguo.dao.AccountMapper;
import com.nengguoqieguo.dao.FanKuiMapper;
import com.nengguoqieguo.dao.SeasonTuiJianMapper;
import com.nengguoqieguo.entity.Account;
import com.nengguoqieguo.service.*;
import com.nengguoqieguo.utils.Result;
import com.nengguoqieguo.utils.SHA_1;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags="管理类")
@RestController
@CrossOrigin
public class ManagerController {

    @Autowired
    AccountService accountService;

    @Autowired
    SeasonTuiJianService seasonTuiJianService;

    @Autowired
    CookbookService cookbookService;

    @Autowired
    ChineseMedicineService chineseMedicineService;

    @Autowired
    EnquireService enquireService;

    @Autowired
    FanKuiMapper fanKuiMapper;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    SeasonTuiJianMapper seasonTuiJianMapper;

    @PostMapping("SuperManagerLogin")
    public Result SuperManagerLogin(@RequestBody Map<String,String> map){
        String account = map.get("account");
        String password = map.get("password");
        account = account.replace("\"","");
        password = SHA_1.jdkSHA1(password.replace("\"",""));
        if(accountService.findAccountByAccount(account) == 0){
            return Result.error(-1,"该账号不存在！");
        }
        else if(accountService.findSuperManagerTypeByAccount(account) != 2){
            return Result.error(-1,"权限不足！");
        }
        else if(accountService.findPasswordByAccount(account).equals(password)){
            Result result = new Result();
            result.put("username",accountService.findUserNameByAccount(account));
            result.put("msg","登录成功");
            return Result.ok(accountService.findUserNameByAccount(account));
        }
        else{
            return Result.error(-1,"密码错误");
        }
    }

    @PostMapping("getAllAccount")
    public Result getAllAccount(@RequestBody Map<String, Integer> map){
        Result result = new Result();
        int start = map.get("pagenum");
        if(accountService.findCount()%5 == 0){
            result.put("total",accountService.findCount()/5);
        }
        else{
            result.put("total",accountService.findCount()/5+1);
        }
        result.put("accountList",accountService.findAllAccount1(start));
        return result;
    }

    @PostMapping("addAccount2")
    public Result addAccount2(@RequestBody Map<String, String> map){
        String account = map.get("account");
        String username = map.get("username");
        String password = map.get("password");
        if (accountService.findAccountByAccount(account) == 0) {
            if (accountService.findAccountByUserName(username) == 0) {
                Account account1 = new Account(account,username,password);
                account1.setPassword(SHA_1.jdkSHA1(password));
                if (accountService.addAccount(account1) > 0) {
                    return Result.ok("注册成功");
                }
            }
            return Result.error(-1, "该用户名已被使用");
        }
        return Result.error(-1, "该账号已被使用");
    }

    @PostMapping("searchAccount")
    public Result searchAccount(@RequestBody Map<String, Object> map){
        Result result = new Result();
        String search = (String) map.get("search");
        int start = (int) map.get("pagenum");
        if(accountService.findSearchCount(search)%5 == 0){
            result.put("total",accountService.findSearchCount(search)/5);
        }
        else{
            result.put("total",accountService.findSearchCount(search)/5+1);
        }
        result.put("accountList",accountService.findSearch(search,start));
        return result;
    }

    @PostMapping("updateAccount1")
    public Result updateAccount1(@RequestBody Map<String, String> map){
        String username = map.get("username");
        String account = map.get("account");
        if(accountMapper.updateAccount1(username,account)>0){
            return Result.ok("修改成功");
        }
        return Result.error(-1,"修改失败");
    }

    @PostMapping("deleteAccount1")
    public Result deleteAccount1(@RequestBody Map<String, String> map){
        String account = map.get("account");
        if(accountMapper.deleteAccount1(account)>0){
            return Result.ok("删除成功");
        }
        return Result.error(-1,"删除失败");
    }

    @PostMapping("getAllSersonTuiJian")
    public Result getAllSersonTuiJian(@RequestBody Map<String, Integer> map){
        Result result = new Result();
        int start = map.get("pagenum");
        if(seasonTuiJianService.findCount()%5 == 0){
            result.put("total",seasonTuiJianService.findCount()/5);
        }
        else{
            result.put("total",seasonTuiJianService.findCount()/5+1);
        }
        result.put("seasontuijianList",seasonTuiJianService.findAllSeasonTuiJian1(start));
        return result;
    }

    @PostMapping("searchSeasonTuiJian")
    public Result searchSeasonTuiJian(@RequestBody Map<String, Object> map){
        Result result = new Result();
        String search = (String) map.get("search");
        int start = (int) map.get("pagenum");
        if(seasonTuiJianService.findSearchCount(search)%5 == 0){
            result.put("total",seasonTuiJianService.findSearchCount(search)/5);
        }
        else{
            result.put("total",seasonTuiJianService.findSearchCount(search)/5+1);
        }
        result.put("seasontuijianList",seasonTuiJianService.findSearch(search,start));
        return result;
    }

    @PostMapping("addSeasonTuiJian")
    public Result addSeasonTuiJian(@RequestBody Map<String, String> map){
        String season = map.get("season");
        String cookbook = map.get("cookbook");
        String method = map.get("method");
        if(seasonTuiJianMapper.addSeasonTuiJian(season,cookbook,method)>0){
            return Result.ok("添加成功");
        }
        return Result.ok("添加失败");
    }

    @PostMapping("updateSeasonTuiJian")
    public Result updateSeasonTuiJian(@RequestBody Map<String, String> map){
        String season = map.get("season");
        String cookbook = map.get("cookbook");
        String method = map.get("method");
        if(seasonTuiJianMapper.updateSeasonTuiJian(season,cookbook,method)>0){
            return Result.ok("修改成功");
        }
        return Result.error(-1,"修改失败");
    }

    @PostMapping("deleteAccount1")
    public Result deleteSeasonTuiJian(@RequestBody Map<String, String> map){
        String cookbook = map.get("cookbook");
        if(seasonTuiJianMapper.deleteSeasonTuiJian(cookbook)>0){
            return Result.ok("删除成功");
        }
        return Result.error(-1,"删除失败");
    }

    @PostMapping("getAllPhysiqueCookbook")
    public Result getAllPhysiqueCookbook(@RequestBody Map<String, Integer> map){
        Result result = new Result();
        int start = map.get("pagenum");
        if(cookbookService.findCount()%3 == 0){
            result.put("total",cookbookService.findCount()/3);
        }
        else{
            result.put("total",cookbookService.findCount()/3+1);
        }
        result.put("cookbookList",cookbookService.findAllPhysiqueCookbook1(start));
        return result;
    }

    @PostMapping("searchPhysiqueCookbook")
    public Result searchPhysiqueCookbook(@RequestBody Map<String, Object> map){
        Result result = new Result();
        String search = (String) map.get("search");
        int start = (int) map.get("pagenum");
        if(cookbookService.findSearchCount(search)%3 == 0){
            result.put("total",cookbookService.findSearchCount(search)/3);
        }
        else{
            result.put("total",cookbookService.findSearchCount(search)/3+1);
        }
        result.put("cookbookList",cookbookService.findSearch(search,start));
        return result;
    }

    @PostMapping("getAllChineseMedicine")
    public Result getAllChineseMedicine(@RequestBody Map<String, Integer> map){
        Result result = new Result();
        int start = map.get("pagenum");
        if(chineseMedicineService.findCount()%2 == 0){
            result.put("total",chineseMedicineService.findCount()/2);
        }
        else{
            result.put("total",chineseMedicineService.findCount()/2+1);
        }
        result.put("chineseMedicineList",chineseMedicineService.findAllChineseMedicine(start));
        return result;
    }

    @PostMapping("searchChineseMedicine")
    public Result searchChineseMedicine(@RequestBody Map<String, Object> map){
        Result result = new Result();
        String search = (String) map.get("search");
        int start = (int) map.get("pagenum");
        if(chineseMedicineService.findSearchCount(search)%2 == 0){
            result.put("total",chineseMedicineService.findSearchCount(search)/2);
        }
        else{
            result.put("total",chineseMedicineService.findSearchCount(search)/2+1);
        }
        result.put("chineseMedicineList",chineseMedicineService.findSearch(search,start));
        return result;
    }

    @PostMapping("getAllEnquire")
    public Result getAllEnquire(@RequestBody Map<String, Integer> map){
        Result result = new Result();
        int start = map.get("pagenum");
        if(enquireService.findCount()%5 == 0){
            result.put("total",enquireService.findCount()/5);
        }
        else{
            result.put("total",enquireService.findCount()/5+1);
        }
        result.put("enquireList",enquireService.findEnquire1(start));
        return result;
    }

    @PostMapping("searchEnquire")
    public Result searchEnquire(@RequestBody Map<String, Object> map){
        Result result = new Result();
        String search = (String) map.get("search");
        int start = (int) map.get("pagenum");
        if(enquireService.findSearch(search)%5 == 0){
            result.put("total",enquireService.findSearch(search)/5);
        }
        else{
            result.put("total",enquireService.findSearch(search)/5+1);
        }
        result.put("enquireList",enquireService.findSearch(search,start));
        return result;
    }

    @PostMapping("getAllFankui")
    public Result getAllFankui(@RequestBody Map<String, Integer> map){
        Result result = new Result();
        int start = map.get("pagenum");
        if(fanKuiMapper.findCount()%5 == 0){
            result.put("total",fanKuiMapper.findCount()/5);
        }
        else{
            result.put("total",fanKuiMapper.findCount()/5+1);
        }
        result.put("fankuiList",fanKuiMapper.findAllFankui((start-1)*5));
        return result;
    }

    @PostMapping("searchFankui")
    public Result searchFankui(@RequestBody Map<String, Object> map){
        Result result = new Result();
        String search = (String) map.get("search");
        int start = (int) map.get("pagenum");
        if(fanKuiMapper.findSearchCount(search)%5 == 0){
            result.put("total",fanKuiMapper.findSearchCount(search)/5);
        }
        else{
            result.put("total",fanKuiMapper.findSearchCount(search)/5+1);
        }
        result.put("fankuiList",fanKuiMapper.findSearch(search,(start-1)*5));
        return result;
    }
}
