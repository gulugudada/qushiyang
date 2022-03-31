package com.nengguoqieguo.service;

import com.nengguoqieguo.entity.Collect;

import java.util.List;

public interface CollectService {
    public List<Collect> findAllCollect();
    public List<Collect> findCollectByUsername(String username);
    public String findTypeByUsernameDishname(String username,String dishname);
    public int addCollect(Collect collect);
    public int deleteCollect(String username,String dishname);
    public int findIsCollectByUsernameDishname(String username,String dishname);
}
