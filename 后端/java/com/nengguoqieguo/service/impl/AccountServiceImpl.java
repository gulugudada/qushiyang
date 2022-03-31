package com.nengguoqieguo.service.impl;

import com.nengguoqieguo.dao.AccountMapper;
import com.nengguoqieguo.dao.UserMapper;
import com.nengguoqieguo.entity.Account;
import com.nengguoqieguo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<Account> findAllAccount() {
        return accountMapper.findAllAccount();
    }

    @Override
    public String findPasswordByAccount(String account) {
        return accountMapper.findPasswordByAccount(account);
    }

    @Override
    public String findUserNameByAccount(String account) {
        return accountMapper.findUserNameByAccount(account);
    }

    @Override
    public int findAccountByUserName(String username) {
        return accountMapper.findAccountByUserName(username);
    }

    @Override
    public int findAccountByAccount(String account) {
        return accountMapper.findAccountByAccount(account);
    }

    @Override
    public int addAccount(Account account) {
        return accountMapper.addAccount(account);
    }

    @Override
    public int updateAccount(Account account) {
        return accountMapper.updateAccount(account);
    }

    @Override
    public int updatePasswordByAccount(String account, String password) {
        return accountMapper.updatePasswordByAccount(account,password);
    }

    @Override
    public int findSuperManagerTypeByAccount(String account) {
        return accountMapper.findSuperManagerTypeByAccount(account);
    }

    @Override
    public List<Account> findAllAccount1(int start) {
        return accountMapper.findAllAccount1((start-1)*5);
    }

    @Override
    public List<Account> findSearch(String search, int start) {
        return accountMapper.findSearch(search,(start-1)*5);
    }

    @Override
    public int findCount() {
        return accountMapper.findCount();
    }

    @Override
    public int findSearchCount(String search) {
        return accountMapper.findSearchCount(search);
    }
}
