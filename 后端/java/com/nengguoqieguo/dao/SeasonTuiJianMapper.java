package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.Account;
import com.nengguoqieguo.entity.SeasonTuiJian;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeasonTuiJianMapper {
    public List<SeasonTuiJian> findAllSeasonTuiJian();
    public List<SeasonTuiJian> findSeasonTuiJianBySeason(String season);
    public SeasonTuiJian findSeasonTuiJianByCookbook(String cookbook);
    public String findImageByCookbook(String cookbook);
    public List<SeasonTuiJian> findAllSeasonTuiJian1(int start);
    public List<SeasonTuiJian> findSearch(@Param("search") String search,@Param("start") int start);
    public int findCount();
    public int findSearchCount(String search);
    public int addSeasonTuiJian(String season,String cookbook,String method);
    public int updateSeasonTuiJian(String season,String cookbook,String method);
    public int deleteSeasonTuiJian(String cookbook);
}
