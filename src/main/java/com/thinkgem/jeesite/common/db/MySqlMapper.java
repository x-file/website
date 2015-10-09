package com.thinkgem.jeesite.common.db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface MySqlMapper {
	@Select("select * from cms_video")
    List<Map<String,Object>> getList();
}
