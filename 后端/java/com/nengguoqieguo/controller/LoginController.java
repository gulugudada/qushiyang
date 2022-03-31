package com.nengguoqieguo.controller;

import com.nengguoqieguo.entity.Account;
import com.nengguoqieguo.entity.YanZhengMa;
import com.nengguoqieguo.service.AccountService;
import com.nengguoqieguo.service.YanZhengMaService;
import com.nengguoqieguo.utils.Result;
import com.nengguoqieguo.utils.SHA_1;
import com.nengguoqieguo.utils.SMS;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//后端提供接口 JSON  RestController = Controller + ResponseBody
@Api(tags="登录类")
@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    AccountService accountService;

    @Autowired
    YanZhengMaService yanZhengMaService;

    @PostMapping("findAllAccount")
    public Result findAllAccount(){
        return Result.ok(accountService.findAllAccount());
    }

    @PostMapping("findUserNameByAccount")
    public Result findUserNameByAccount(String account){
        return Result.ok(accountService.findUserNameByAccount(SHA_1.jdkSHA1(account)));
    }

    @PostMapping("findPasswordByAccount")
    public Result findPasswordByAccount(String account,String password){
        account = account.replace("\"","");
        password = SHA_1.jdkSHA1(password.replace("\"",""));
        if(accountService.findAccountByAccount(account) == 0){
            return Result.error(-1,"该账号未注册");
        }
        else if(accountService.findPasswordByAccount(account).equals(password)){
            Result result = new Result();
            result.put("username",accountService.findUserNameByAccount(account));
            result.put("msg","登录成功");
            return result;
        }
        else{
            return Result.error(-1,"密码错误");
        }
    }

    @PostMapping("addAccount")
    public Result addAccount(String account,String username,String password,String yanzhengma){
        yanzhengma = yanzhengma.replace("\"","");
        if(yanzhengma.equals(yanZhengMaService.findYanZhengMaByAccount(account))) {
            if (accountService.findAccountByAccount(account) == 0) {
                if (accountService.findAccountByUserName(username) == 0) {
                    Account account1 = new Account(account,username,password);
                    account1.setPassword(SHA_1.jdkSHA1(password));
                    if (accountService.addAccount(account1) > 0) {
                        yanZhengMaService.deleteYanZhengMaByAccount(account);
                        return Result.ok("注册成功");
                    }
                }
                return Result.error(-1, "该用户名已被使用");
            }
            return Result.error(-1, "该账号已被使用");
        }
        return Result.error(-1,"验证码输入错误");
    }

    @PostMapping("updateAccount")
    public Result updateAccount(String account,String username,String password){
        if(accountService.findAccountByUserName(username) == 0){
            Account account1 = new Account(account,username,password);
            if(accountService.updateAccount(account1) > 0){
                return Result.ok("更新成功");
            }
        }
        return Result.error(-1,"该用户名已被使用");
    }

    @PostMapping("updatePasswordByAccount")
    public Result updatePasswordByAccount(String account,String password){
        System.out.println(password);
        if(accountService.updatePasswordByAccount(account,SHA_1.jdkSHA1(password)) > 0){
            return Result.ok("修改成功");
        }
        return Result.error("修改失败");
    }

    @PostMapping("getYanZhengMa")
    public void getYanZhengMa(String account) throws Exception {
//        Result result = new Result();
//        要去掉双引号
        account = account.replace("\"", "");
        YanZhengMa yanZhengMa = new YanZhengMa();
        yanZhengMa.setAccount(account);
        yanZhengMa.setYanzhengma(SMS.getSMS(account));
        if(yanZhengMaService.findAcountIsUse(account)){
            yanZhengMaService.updateYanZhengMa(yanZhengMa);
        }
        else {
            yanZhengMaService.addYanZhengMa(yanZhengMa);
        }
//        yanzhengma = "123456";
//        System.out.println(account);
//        result.put("验证码",yanzhengma);
//        return result;
    }
}