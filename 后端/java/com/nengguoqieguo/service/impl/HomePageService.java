package com.nengguoqieguo.service.impl;

import com.nengguoqieguo.dao.SeasonMapper;
import com.nengguoqieguo.dao.SeasonTuiJianMapper;
import com.nengguoqieguo.entity.Season;
import com.nengguoqieguo.entity.SeasonTuiJian;
import com.nengguoqieguo.utils.WeatherAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HomePageServiceImpl implements com.nengguoqieguo.service.HomePageService {

    @Autowired
    SeasonMapper seasonMapper;

    @Autowired
    SeasonTuiJianMapper seasonTuiJianMapper;

    @Override
    public Map getWeather(String city) {
        try {
            return WeatherAPI.getWeather(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Season> findAllSeason() {
        return seasonMapper.findAllSeason();
    }

    @Override
    public List<SeasonTuiJian> findAllSeasonTuiJian() {
        return seasonTuiJianMapper.findAllSeasonTuiJian();
    }

    @Override
    public Season findSeasonBySeason(String season) {
        return seasonMapper.findSeasonBySeason(season);
    }

    @Override
    public List<SeasonTuiJian> findSeasonTuiJianBySeason(String seaosn) {
        return seasonTuiJianMapper.findSeasonTuiJianBySeason(seaosn);
    }
}
