package com.example.dynamicdatasource.service;

import com.example.dynamicdatasource.model.InstanceInfo;

public interface InstanceInfoService {
    /**
     * 获取数据源实例信息
     * @param instanceName
     * @return
     */
    InstanceInfo findByName(String instanceName);
}
