package com.thinkgem.jeesite.modules.cms.utils;

public class SocketConfig {
    /**
     * 服务端要连接的服务端对应的地址、IP地址
     */
    public static String HOST = "218.28.9.82";
    
    /**
     * 要连接的服务端对应的监听端口
     */
    public static int PORT = 9996;
    
    /**
     * 获取展播地址(所约定的数据格式)
     * web端向服务端发送的socket消息，用于获取展播视频地址
     * web-->server
     */
    public static String GET_SHOW_URL = "STKNET_DATA;STK_WEBSYS;STKMOD_NETVIDEO;STKNETMSG_GET_LIVESCENERY_RES;";
    
    /**
     * 成功获取展播地址
     * 服务器接收消息后,正常返回给web端的数据(所约定的数据格式)
     * server-->web
     */
    public static String RETURN_SUCCESS_SHOW_URL = "STKNET_DATA;STK_SERVER;STK_WEBSYS;STKNETMSG_RET_LIVESCENERY_RES;";
    
    /**
     * 无法获取展播地址，服务端异常
     * 服务端出现异常返回给web端的数据(所约定的数据格式)
     * server-->web
     */
    public static String RETURN_ERROR_SHOW_URL = "STKNET_DATA;STK_SERVER;STK_WEBSYS;STKNETMSG_ERROR_LIVESCENERY";
    
    /**
     * 获取直播地址(所约定的数据格式)
     * web-->server
     */
    public static String GET_LIVE_URL = "STKNET_DATA;STK_WEBSYS;STKMOD_NETVIDEO;STKNETMSG_LIVETV_GETRES;";
    
    /**
     * 正常返回直播地址(所预定的数据格式)
     * server-->web
     * 用于判断返回的数据是否正确（正确则包含直播地址）
     */
    public static String GET_SUCCESS_LIVE_URL = "STKNET_DATA;STK_SERVER;STK_WEBSYS;STKNETMSG_LIVETV_RES;";
    
    
    /**
     * 获取景区中的摄像点
     * web-->server
     */
    public static String GET_CAM_LIST = "STKNET_DATA;STK_WEBSYS;STKMOD_NETVIDEO;STKNETMSG_GET_CAM_LIST;";
    
    /**
     * 返回景区中的摄像点
     * 用于判断返回的数据是否正确（正确则包含摄像点列表数据）
     * server-->web
     */
    public static String GET_SUCCESS_CAM_LIST = "STKNET_DATA;STK_SERVER;STK_WEBSYS;STKNETMSG_CAM_LIST";
    
    
    /**
     * 启动监控并获取摄像机的状态(焦距、是否可控等)
     * web-->server
     */
    public static String START_MONITOR_AND_GET_CAM_STATE= "STKNET_DATA;STK_WEBSYS;STKMOD_NETVIDEO;STKNETMSG_CAMMON_ON;";
    
    /**
     * 结束直播
     * web-->server
     */
    public static String STOP_MONITOR = "STKNET_DATA;STK_WEBSYS;STKMOD_NETVIDEO;STKNETMSG_MONITOR_OFF;";
}
