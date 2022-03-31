package com.nengguoqieguo.service;

import com.nengguoqieguo.entity.Account;
import com.nengguoqieguo.entity.SeasonTuiJian;

import java.util.List;

public interface SeasonTuiJianService {
    public List<SeasonTuiJian> findAllSeasonTuiJian();
    public List<SeasonTuiJian> findSeasonTuiJianBySeason(String season);
    public SeasonTuiJian findSeasonTuiJianByCookbook(String cookbook);
    public String findImageByCookbook(String cookbook);
    public List<SeasonTuiJian> findAllSeasonTuiJian1(int start);
    public List<SeasonTuiJian> findSearch(String search, int start);
    public int findCount();
    public int findSearchCount(String search);
}
