package com.nengguoqieguo.service;

import com.nengguoqieguo.entity.YanZhengMa;

public interface YanZhengMaService {
    public String findYanZhengMaByAccount(String account);
    public boolean findAcountIsUse(String account);
    public int addYanZhengMa(YanZhengMa yanZhengMa);
    public int updateYanZhengMa(YanZhengMa yanZhengMa);
    public int deleteYanZhengMaByAccount(String account);
}
