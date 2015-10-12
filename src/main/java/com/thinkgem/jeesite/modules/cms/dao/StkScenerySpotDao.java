/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cms.entity.StkScenerySpot;

/**
 * 景点DAO接口
 * @author sa
 * @version 2015-10-09
 */
@MyBatisDao
public interface StkScenerySpotDao extends CrudDao<StkScenerySpot> {
	
	public List<StkScenerySpot> findList();
	
}
