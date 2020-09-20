package com.example.dynamicdatasource.service.impl;

import com.example.dynamicdatasource.dao.InstanceInfoMapper;
import com.example.dynamicdatasource.model.InstanceInfo;
import com.example.dynamicdatasource.service.InstanceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanceInfoServiceImpl implements InstanceInfoService {

    @Autowired
    private InstanceInfoMapper instanceInfoMapper;

    @Override
    public InstanceInfo findByName(String instanceName) {
        return instanceInfoMapper.findByName(instanceName);
    }

}
