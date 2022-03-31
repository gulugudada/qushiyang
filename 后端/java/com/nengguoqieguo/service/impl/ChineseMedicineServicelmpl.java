package com.nengguoqieguo.service.impl;

import com.nengguoqieguo.dao.ChineseMedicineMapper;
import com.nengguoqieguo.entity.ChineseMedicine;
import com.nengguoqieguo.service.ChineseMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChineseMedicineServicelmpl implements ChineseMedicineService {
    @Autowired
    ChineseMedicineMapper chineseMedicineMapper;

    //查看药物详细信息
    @Override
    public ChineseMedicine FindMassage(String ChineseName) {
        return chineseMedicineMapper.FindMassage(ChineseName);
    }

    //药品模糊查询
    @Override
    public List<ChineseMedicine> getMedicineLike(String NameLike) {
        return chineseMedicineMapper.getMedicineLike(NameLike);
    }

    @Override
    public List<ChineseMedicine> getMedicineClass(String Class) {
        return chineseMedicineMapper.getMedicineClass(Class);
    }

    @Override
    public List<ChineseMedicine> findAllDate() {
        return chineseMedicineMapper.findAllDate();
    }

    @Override
    public List<ChineseMedicine> queryMedicineByArray(int currPage) {
        List<ChineseMedicine> chineseMedicines = chineseMedicineMapper.queryMedicineByArray(currPage);
        int firstdate = (currPage-1)*10;
        int lastdate = currPage*10;
        return chineseMedicines.subList(firstdate,lastdate);
    }

    @Override
    public String findImageByChineseName(String ChineseName) {
        return chineseMedicineMapper.findImageByChineseName(ChineseName);
    }

    @Override
    public List<ChineseMedicine> findAllChineseMedicine(int start) {
        return chineseMedicineMapper.findAllChineseMedicine((start-1)*2);
    }

    @Override
    public List<ChineseMedicine> findSearch(String search, int start) {
        return chineseMedicineMapper.findSearch(search,(start-1)*2);
    }

    @Override
    public int findCount() {
        return chineseMedicineMapper.findCount();
    }

    @Override
    public int findSearchCount(String search) {
        return chineseMedicineMapper.findSearchCount(search);
    }
}
