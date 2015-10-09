package com.thinkgem.jeesite.common.db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface SqlServerMapper {
	@Select("select * from stk_scenery_show")
    List<Map<String,Object>> getList();
}
