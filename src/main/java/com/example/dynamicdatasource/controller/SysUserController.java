package com.example.dynamicdatasource.controller;

import com.example.dynamicdatasource.config.dds.DataSource;
import com.example.dynamicdatasource.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private UserInfoService userInfoService;

    @DataSource(value = "master")
    @PostMapping(value="/findAll")
    public Object findAll() {
        return userInfoService.findAll();
    }

    @PostMapping(value="/findAll2")
    @DataSource(value = "master")
    public Object findAll2() {
        return userInfoService.dynamicdTest("多数据源测试");
    }
}
