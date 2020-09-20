package com.example.dynamicdatasource.service;

import com.example.dynamicdatasource.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    /**
     * 查找所有用户
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 多数据源切换测试
     * @param instanceName
     * @return
     */
    List<UserInfo> dynamicdTest(String instanceName);
}
