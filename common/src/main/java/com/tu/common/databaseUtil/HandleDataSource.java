package com.tu.common.databaseUtil;

/**
 * Created by len on 2019/1/15.
 */
public class HandleDataSource {

    public static final ThreadLocal<String> threadLocal= new ThreadLocal<String>();

    public static void setDbType(String dbType) {
        threadLocal.set(dbType);
    }
    public static String getDbType() {
        return ((String) threadLocal.get());
    }
    public static void clearDbType() {
        threadLocal.remove();
    }

}
