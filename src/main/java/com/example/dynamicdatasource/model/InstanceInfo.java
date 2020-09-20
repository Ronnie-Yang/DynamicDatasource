package com.example.dynamicdatasource.model;

import lombok.Data;

@Data
public class InstanceInfo {
    private int id;

    private String instanceName;

    private String instanceIp;

    private String dbName;

    private String instancePort;

    private String userName;

    private String password;
}
