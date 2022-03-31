package com.nengguoqieguo.service;

import com.nengguoqieguo.entity.Account;

import java.util.List;

public interface AccountService {
    public List<Account> findAllAccount();
    public String findPasswordByAccount(String account);
    public String findUserNameByAccount(String account);
    public int findAccountByUserName(String username);
    public int findAccountByAccount(String account);
    public int addAccount(Account account);
    public int updateAccount(Account account);
    public int updatePasswordByAccount(String account,String password);
    public int findSuperManagerTypeByAccount(String account);
    public List<Account> findAllAccount1(int start);
    public List<Account> findSearch(String search,int start);
    public int findCount();
    public int findSearchCount(String search);
}
