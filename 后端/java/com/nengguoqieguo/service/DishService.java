package com.nengguoqieguo.service;

import com.nengguoqieguo.entity.Dish;

import java.util.List;

public interface DishService {
    public List<Dish> findAllDish();
    public Dish findDishByDishname(String dishname);
    public String findImageByDishname(String dishname);
}
