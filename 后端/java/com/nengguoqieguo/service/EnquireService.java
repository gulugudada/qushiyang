package com.nengguoqieguo.service;

import com.nengguoqieguo.entity.Enquire;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnquireService {
    public String findEnquire(String question);
    public List<Enquire> findEnquire1(int start);
    public List<Enquire> findSearch(String search,int start);
    public int findCount();
    public int findSearch(String search);
}
