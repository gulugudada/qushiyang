package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountMapper {
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
    public List<Account> findSearch(@Param("search") String search,@Param("start") int start);
    public int findCount();
    public int findSearchCount(String search);
    public int updateAccount1(String username,String account);
    public int deleteAccount1(String account);
}
