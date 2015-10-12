package com.thinkgem.jeesite.common.db;

import java.util.HashMap;
import java.util.Map;

public final class ThreadLocalUtils {

	private static final ThreadLocal<Map<String, String>> threadContext = new ThreadLocal<Map<String, String>>();

	public static void put(String key, String value) {
		getContext().put(key, value);
	}

	public static String get(String key) {
		return getContext().get(key);
	}

	public static void remove(String key) {
		getContext().remove(key);
	}

	public static Map<String, String> getContext() {
		Map<String, String> value = threadContext.get();
		if (null == value) {
			threadContext.set(new HashMap<String, String>());
		}
		return threadContext.get();
	}
}