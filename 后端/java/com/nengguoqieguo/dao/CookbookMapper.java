package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.Account;
import com.nengguoqieguo.entity.Cookbook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CookbookMapper {
    public List<Cookbook> findAllCookbook();
    public List<Cookbook> findCookbookByPhysique(String physique);
    public List<Cookbook> findAllPhysiqueCookbook();
    public List<Cookbook> findAllPhysiqueCookbook1(int start);
    public List<Cookbook> findSearch(@Param("search") String search, @Param("start") int start);
    public int findCount();
    public int findSearchCount(String search);
}
