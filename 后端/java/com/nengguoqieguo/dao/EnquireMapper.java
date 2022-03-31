package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.Enquire;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EnquireMapper {
    public String findEnquire(String question);
    public List<Enquire> findEnquire1(int start);
    public List<Enquire> findSearch(@Param("search") String search, @Param("start") int start);
    public int findCount();
    public int findSearchCount(String search);
}
