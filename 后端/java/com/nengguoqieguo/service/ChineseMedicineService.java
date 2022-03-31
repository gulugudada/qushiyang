package com.nengguoqieguo.service;

import com.nengguoqieguo.entity.ChineseMedicine;

import java.util.List;

public interface ChineseMedicineService {
    public ChineseMedicine FindMassage(String ChineseName);
    public List<ChineseMedicine> getMedicineLike(String NameLike);
    public List<ChineseMedicine> getMedicineClass(String Class);
    public  List<ChineseMedicine> findAllDate();
    public List<ChineseMedicine> queryMedicineByArray(int currPage);
    public String findImageByChineseName(String ChineseName);
    public List<ChineseMedicine> findAllChineseMedicine(int start);
    public List<ChineseMedicine> findSearch(String search,int start);
    public int findCount();
    public int findSearchCount(String search);
}
