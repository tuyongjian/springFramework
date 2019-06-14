package com.tu.common.databaseUtil;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by len on 2019/1/15.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 获取与数据源相关的key 此key是Map<String,DataSource> resolvedDataSources 中与数据源绑定的key值
     * 在通过determineTargetDataSource获取目标数据源时使用
     */

    protected Object determineCurrentLookupKey() {
        return HandleDataSource.getDbType();
    }
}
