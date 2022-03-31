package com.nengguoqieguo.service;

import com.nengguoqieguo.entity.Season;
import com.nengguoqieguo.entity.SeasonTuiJian;

import java.util.List;
import java.util.Map;

public interface HomePageService {
    public Map getWeather(String city);
    public List<Season> findAllSeason();
    public List<SeasonTuiJian> findAllSeasonTuiJian();
    public Season findSeasonBySeason(String season);
    public List<SeasonTuiJian> findSeasonTuiJianBySeason(String seaosn);
}
