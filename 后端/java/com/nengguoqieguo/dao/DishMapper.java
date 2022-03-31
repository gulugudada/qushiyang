package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 直接针对Dish表的操作
 */
@Mapper
public interface DishMapper {
    public List<Dish> findAllDish();
    public Dish findDishByDishname(String dishname);
    public String findImageByDishname(String dishname);
}
