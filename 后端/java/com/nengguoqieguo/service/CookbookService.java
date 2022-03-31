package com.nengguoqieguo.service;

import com.nengguoqieguo.entity.Cookbook;
import com.nengguoqieguo.entity.SeasonTuiJian;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CookbookService {
    public List<Cookbook> findAllCookbook();
    public List<Cookbook> findCookbookByPhysique(String physique);
    public List<Cookbook> findAllPhysiqueCookbook1(int start);
    public List<Cookbook> findSearch(String search,int start);
    public int findCount();
    public int findSearchCount(String search);
}
