package com.thinkgem.jeesite.common.db;

public class DataSourceInterceptor {

	private static final String DATESOURCE_ONE = "dataSource";
	private static final String DATESOURCE_TWO = "dataSource2";

	public static void setdataSourceOne() {
		DatabaseContextHolder.setCustomerType(DATESOURCE_ONE);
	}

	public static void setdataSourceTwo() {
		DatabaseContextHolder.setCustomerType(DATESOURCE_TWO);
	}
}