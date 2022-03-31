package com.nengguoqieguo.service.impl;

import com.nengguoqieguo.dao.DishMapper;
import com.nengguoqieguo.entity.Dish;
import com.nengguoqieguo.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishMapper dishMapper;

    @Override
    public List<Dish> findAllDish() {
        return dishMapper.findAllDish();
    }

    @Override
    public Dish findDishByDishname(String dishname) {
        return dishMapper.findDishByDishname(dishname);
    }

    @Override
    public String findImageByDishname(String dishname) {
        return dishMapper.findImageByDishname(dishname);
    }
}
