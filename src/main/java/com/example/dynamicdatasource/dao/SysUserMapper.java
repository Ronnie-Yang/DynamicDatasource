package com.example.dynamicdatasource.dao;

import com.example.dynamicdatasource.model.UserInfo;

import java.util.List;

public interface SysUserMapper {
    /**
     * 查询全部用户
     * @return
     */
    List<UserInfo> selectAll();

    /**
     * 多数据源切换测试
     * @param instanceName
     * @return
     */
    List<UserInfo> dynamicdTest(String instanceName);
}
