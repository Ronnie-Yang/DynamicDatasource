package com.example.dynamicdatasource.config.dds;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源实现类
 * @author Louis
 * @date Jun 17, 2019
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static Map<Object,Object> dataSourceMap=new HashMap<Object, Object>();
    private static DynamicDataSource instance;
    private static byte[] lock=new byte[0];

    /**
     * 单例模式，保证获取到都是同一个对象
     * @return
     */
    public static synchronized DynamicDataSource getInstance(){
        if(instance==null){
            synchronized (lock){
                if(instance==null){
                    instance=new DynamicDataSource();
                }
            }
        }
        return instance;
    }

    /**
     * 重写setTargetDataSources，通过入参targetDataSources进行数据源的添加
     * @param targetDataSources
     */
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 如果希望所有数据源在启动配置时就加载好，这里通过设置数据源Key值来切换数据，定制这个方法
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

    /**
     * 设置默认数据源
     * @param defaultDataSource
     */
    public void setDefaultDataSource(Object defaultDataSource) {
        super.setDefaultTargetDataSource(defaultDataSource);
    }

    /**
     * 设置数据源
     * @param dataSources
     */
    public void setDataSources(Map<Object, Object> dataSources) {
        super.setTargetDataSources(dataSources);
        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
        DynamicDataSourceContextHolder.addDataSourceKeys(dataSources.keySet());
    }

    /**
     *  获取到原有的多数据源，并从该数据源基础上添加一个或多个数据源后，
     *  通过上面的setTargetDataSources进行加载
     * @return
     */
    public Map<Object, Object> getDataSourceMap() {
        return dataSourceMap;
    }
}