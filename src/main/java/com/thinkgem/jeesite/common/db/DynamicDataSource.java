package com.thinkgem.jeesite.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Mysql 多数据源切换
 *
 * @author sa
 * @version V1.0
 * @Description:
 * @date 2015/10/09
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
	  
    /** 
     *  
     * @author sa 
     * @date 2012-5-18 下午4:06:44 
     * @return the currentLookupKey 
     */  
    public static String getCurrentLookupKey() {  
        return (String) contextHolder.get();  
    }  
  
    /** 
     *  
     * @author sa 
     * @date 2012-5-18 下午4:06:44 
     * @param currentLookupKey 
     *            the currentLookupKey to set 
     */  
    public static void setCurrentLookupKey(String currentLookupKey) {  
        contextHolder.set(currentLookupKey);  
    }  
  
    /* 
     * (non-Javadoc) 
     *  
     * @see 
     * org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource# 
     * determineCurrentLookupKey() 
     */  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return getCurrentLookupKey();  
    }  
}