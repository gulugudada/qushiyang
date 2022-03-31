package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.Phy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhyMapper {
    public List<Phy> findAllPhy();
}
