package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.Physique;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhysiqueMapper {
    public List<Physique> findAllPhysique();
    public Physique findPhysique(String physiquename);
    public String findImageByPhysiquename(String physiquename);
}
