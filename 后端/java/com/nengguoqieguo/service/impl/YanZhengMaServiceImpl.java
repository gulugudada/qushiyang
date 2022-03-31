package com.nengguoqieguo.service.impl;

import com.nengguoqieguo.dao.YanZhengMaMapper;
import com.nengguoqieguo.entity.YanZhengMa;
import com.nengguoqieguo.service.YanZhengMaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YanZhengMaServiceImpl implements YanZhengMaService {

    @Autowired
    YanZhengMaMapper yanZhengMaMapper;

    @Override
    public String findYanZhengMaByAccount(String account) {
        return yanZhengMaMapper.findYanZhengMaByAccount(account);
    }

    @Override
    public boolean findAcountIsUse(String account) {
        if(yanZhengMaMapper.findAcountIsUse(account) > 0){
            return true;
        }
        return false;
    }

    @Override
    public int addYanZhengMa(YanZhengMa yanZhengMa) {
        return yanZhengMaMapper.addYanZhengMa(yanZhengMa);
    }

    @Override
    public int updateYanZhengMa(YanZhengMa yanZhengMa) {
        return yanZhengMaMapper.updateYanZhengMa(yanZhengMa);
    }

    @Override
    public int deleteYanZhengMaByAccount(String account) {
        return yanZhengMaMapper.deleteYanZhengMaByAccount(account);
    }
}
