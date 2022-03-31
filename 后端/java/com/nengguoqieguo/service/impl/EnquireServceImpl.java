package com.nengguoqieguo.service.impl;

import com.nengguoqieguo.dao.EnquireMapper;
import com.nengguoqieguo.entity.Enquire;
import com.nengguoqieguo.service.EnquireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquireServceImpl implements EnquireService {

    @Autowired
    EnquireMapper enquireMapper;

    @Override
    public String findEnquire(String question) {
        return enquireMapper.findEnquire(question);
    }

    @Override
    public List<Enquire> findEnquire1(int start) {
        return enquireMapper.findEnquire1((start-1)*5);
    }

    @Override
    public List<Enquire> findSearch(String search, int start) {
        return enquireMapper.findSearch(search,(start-1)*5);
    }

    @Override
    public int findCount() {
        return enquireMapper.findCount();
    }

    @Override
    public int findSearch(String search) {
        return enquireMapper.findSearchCount(search);
    }
}
