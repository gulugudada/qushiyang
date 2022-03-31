package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.Season;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeasonMapper {
    public List<Season> findAllSeason();
    public Season findSeasonBySeason(String season);
}
