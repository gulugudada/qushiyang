package com.nengguoqieguo.service.impl;

import com.nengguoqieguo.dao.CollectMapper;
import com.nengguoqieguo.entity.Collect;
import com.nengguoqieguo.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectMapper collectMapper;

    @Override
    public List<Collect> findAllCollect() {
        return collectMapper.findAllCollect();
    }

    @Override
    public List<Collect> findCollectByUsername(String username) {
        return collectMapper.findCollectByUsername(username);
    }

    @Override
    public String findTypeByUsernameDishname(String username, String dishname) {
        return collectMapper.findTypeByUsernameDishname(username,dishname);
    }

    @Override
    public int addCollect(Collect collect) {
        return collectMapper.addCollect(collect);
    }

    @Override
    public int deleteCollect(String username, String dishname) {
        return collectMapper.deleteCollect(username,dishname);
    }

    @Override
    public int findIsCollectByUsernameDishname(String username, String dishname) {
        return collectMapper.findIsCollectByUsernameDishname(username,dishname);
    }
}
