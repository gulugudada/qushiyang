package com.nengguoqieguo.service.impl;

import com.nengguoqieguo.dao.SeasonTuiJianMapper;
import com.nengguoqieguo.entity.Account;
import com.nengguoqieguo.entity.SeasonTuiJian;
import com.nengguoqieguo.service.SeasonTuiJianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeasonTuiJianServiceImpl implements SeasonTuiJianService {

    @Autowired
    SeasonTuiJianMapper seasonTuiJianMapper;

    @Override
    public List<SeasonTuiJian> findAllSeasonTuiJian() {
        return seasonTuiJianMapper.findAllSeasonTuiJian();
    }

    @Override
    public List<SeasonTuiJian> findSeasonTuiJianBySeason(String season) {
        return seasonTuiJianMapper.findSeasonTuiJianBySeason(season);
    }

    @Override
    public SeasonTuiJian findSeasonTuiJianByCookbook(String cookbook) {
        return seasonTuiJianMapper.findSeasonTuiJianByCookbook(cookbook);
    }

    @Override
    public String findImageByCookbook(String cookbook) {
        return seasonTuiJianMapper.findImageByCookbook(cookbook);
    }

    @Override
    public List<SeasonTuiJian> findAllSeasonTuiJian1(int start) {
        return seasonTuiJianMapper.findAllSeasonTuiJian1((start-1)*5);
    }

    @Override
    public List<SeasonTuiJian> findSearch(String search, int start) {
        return seasonTuiJianMapper.findSearch(search,(start-1)*5);
    }

    @Override
    public int findCount() {
        return seasonTuiJianMapper.findCount();
    }

    @Override
    public int findSearchCount(String search) {
        return seasonTuiJianMapper.findSearchCount(search);
    }
}
