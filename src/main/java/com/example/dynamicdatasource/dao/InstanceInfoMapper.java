package com.example.dynamicdatasource.dao;

import com.example.dynamicdatasource.model.InstanceInfo;

public interface InstanceInfoMapper {
    InstanceInfo findByName(String instanceName);
}
