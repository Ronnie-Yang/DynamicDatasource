package com.example.dynamicdatasource.service.impl;

import com.example.dynamicdatasource.config.dds.DynamicDataSource;
import com.example.dynamicdatasource.config.dds.DynamicDataSourceContextHolder;
import com.example.dynamicdatasource.dao.InstanceInfoMapper;
import com.example.dynamicdatasource.dao.SysUserMapper;
import com.example.dynamicdatasource.model.InstanceInfo;
import com.example.dynamicdatasource.model.UserInfo;
import com.example.dynamicdatasource.service.UserInfoService;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private InstanceInfoMapper instanceInfoMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public List<UserInfo> findAll() {
        return sysUserMapper.selectAll();
    }


    @Override
    public List<UserInfo> dynamicdTest(String instanceName){
        InstanceInfo instanceInfo = instanceInfoMapper.findByName(instanceName);

        HikariDataSource dataSource = new HikariDataSource();

        String instanceIp = instanceInfo.getInstanceIp();
        String instancePort = instanceInfo.getInstancePort();
        String dbName = instanceInfo.getDbName();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8", instanceIp, instancePort, dbName));
        dataSource.setUsername(instanceInfo.getUserName());
        dataSource.setPassword(instanceInfo.getPassword());

        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        Map<Object, Object> dataSourceMap = dynamicDataSource.getDataSourceMap();

        dataSourceMap.put(instanceName, dataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);

        DynamicDataSourceContextHolder.setDataSourceKey(instanceName);

        List<UserInfo> userInfos = userInfoService.findAll();

        return userInfos;
    }
}
