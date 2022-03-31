package com.nengguoqieguo.dao;

import com.nengguoqieguo.entity.ChineseMedicine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChineseMedicineMapper {
    //查看药物详细信息
    public ChineseMedicine FindMassage(String ChineseName);
    //药品模糊查询
    public List<ChineseMedicine> getMedicineLike(String NameLike);
    //药品分类
    public List<ChineseMedicine> getMedicineClass(String Class);
    //返回全部数据，在前端进行分页
    public  List<ChineseMedicine> findAllDate();
    //前端发送信息，后端进行分页后发送
    public List<ChineseMedicine> queryMedicineByArray(int currPage);
    public String findImageByChineseName(String ChineseName);
    public List<ChineseMedicine> findAllChineseMedicine(int start);
    public List<ChineseMedicine> findSearch(@Param("search") String search,@Param("start") int start);
    public int findCount();
    public int findSearchCount(String search);
}
