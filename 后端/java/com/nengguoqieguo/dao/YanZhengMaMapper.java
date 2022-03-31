package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.YanZhengMa;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YanZhengMaMapper {
    public String findYanZhengMaByAccount(String account);
    public int findAcountIsUse(String account);
    public int addYanZhengMa(YanZhengMa yanZhengMa);
    public int updateYanZhengMa(YanZhengMa yanZhengMa);
    public int deleteYanZhengMaByAccount(String account);
}
