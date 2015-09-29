package com.thinkgem.jeesite.common.ip;

/**
 * 
 * 功能描述：封装IP地址信息POJO
 * 			一条IP范围记录，不仅包括国家和区域，也包括起始IP和结束IP
 *
 * @author  Administrator
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class IPEntry {
	
	// 起始IP
    public String beginIp;
    // 结束IP
    public String endIp;
    // 归属国家
    public String country;
    // 归属地区
    public String area;
    
    /**
     * 
     * 构造函数：清空信息
     *
     */
    public IPEntry() {
    	
        beginIp = ""; 
        endIp = "";
        country = "";
        area = "";
    }
}