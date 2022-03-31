package com.nengguoqieguo.service.impl;

import com.nengguoqieguo.dao.PhyMapper;
import com.nengguoqieguo.dao.PhysiqueMapper;
import com.nengguoqieguo.entity.Phy;
import com.nengguoqieguo.entity.Physique;
import com.nengguoqieguo.service.PhysiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhysiqueServiceImpl implements PhysiqueService {

    @Autowired
    PhyMapper phyMapper;

    @Autowired
    PhysiqueMapper physiqueMapper;

    @Override
    public List<Phy> findAllPhy() {
        return phyMapper.findAllPhy();
    }

    @Override
    public List<Physique> findAllPhysique() {
        return physiqueMapper.findAllPhysique();
    }

    @Override
    public Physique findPhysique(String physiquename) {
        return physiqueMapper.findPhysique(physiquename);
    }

    @Override
    public String findImageByPhysiquename(String physiquename) {
        return physiqueMapper.findImageByPhysiquename(physiquename);
    }
}
