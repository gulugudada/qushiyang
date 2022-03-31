package com.nengguoqieguo.service.impl;

import com.nengguoqieguo.dao.CookbookMapper;
import com.nengguoqieguo.entity.Cookbook;
import com.nengguoqieguo.entity.SeasonTuiJian;
import com.nengguoqieguo.service.CookbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CookbookServiceImpl implements CookbookService {

    @Autowired
    CookbookMapper cookbookMapper;

    @Override
    public List<Cookbook> findAllCookbook() {
        return cookbookMapper.findAllCookbook();
    }

    @Override
    public List<Cookbook> findCookbookByPhysique(String physique) {
        return cookbookMapper.findCookbookByPhysique(physique);
    }

    @Override
    public List<Cookbook> findAllPhysiqueCookbook1(int start) {
        return cookbookMapper.findAllPhysiqueCookbook1((start-1)*3);
    }

    @Override
    public List<Cookbook> findSearch(String search, int start) {
        return cookbookMapper.findSearch(search,(start-1)*3);
    }

    @Override
    public int findCount() {
        return cookbookMapper.findCount();
    }

    @Override
    public int findSearchCount(String search) {
        return cookbookMapper.findSearchCount(search);
    }
}
