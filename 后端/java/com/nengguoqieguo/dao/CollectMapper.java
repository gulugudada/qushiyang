package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectMapper {
    public List<Collect> findAllCollect();
    public List<Collect> findCollectByUsername(String username);
    public String findTypeByUsernameDishname(String username,String dishname);
    public int addCollect(Collect collect);
    public int deleteCollect(String username,String dishname);
    public int findIsCollectByUsernameDishname(String username,String dishname);
}
