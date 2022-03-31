package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.FanKui;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FanKuiMapper {
    public int addFanKui(FanKui fanKui);
    public List<FanKui> findAllFankui(int start);
    public List<FanKui> findSearch(String search,int start);
    public int findCount();
    public int findSearchCount(String search);
}
