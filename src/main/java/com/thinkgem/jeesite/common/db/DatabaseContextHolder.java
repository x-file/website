package com.thinkgem.jeesite.common.db;


public class DatabaseContextHolder {

	public static final String DATA_DOURCE = "DATA_SOURCE";

	public static void setCustomerType(String customerType) {
		ThreadLocalUtils.put(DATA_DOURCE, customerType);
	}

	public static String getCustomerType() {
		return ThreadLocalUtils.get(DATA_DOURCE);
	}

	public static void clearCustomerType() {
		ThreadLocalUtils.remove(DATA_DOURCE);
	}
}