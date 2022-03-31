package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.Education;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EducationMapper {
    public Education findEducationById(int id);
    public int addEducation(Education education);
    public int updateEducation(Education education);
}
