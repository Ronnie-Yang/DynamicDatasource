package com.example.dynamicdatasource.enums;

public enum DataSourceType {
    master("master"),
    slave("slave");

    private String db;
    DataSourceType(String db){
        this.db = db;
    }

    public String getDb(){
        return db;
    }

    public void setDb(String db){
        this.db = db;
    }
}
