package com.nengguoqieguo.service;

import com.nengguoqieguo.entity.Phy;
import com.nengguoqieguo.entity.Physique;

import java.util.List;

public interface PhysiqueService {
    public List<Phy> findAllPhy();
    public List<Physique> findAllPhysique();
    public Physique findPhysique(String physiquename);
    public String findImageByPhysiquename(String physiquename);
}
