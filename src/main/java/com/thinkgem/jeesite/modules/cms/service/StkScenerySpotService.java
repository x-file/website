/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.db.DataSourceInterceptor;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cms.dao.StkScenerySpotDao;
import com.thinkgem.jeesite.modules.cms.entity.StkScenerySpot;

/**
 * 景点Service
 * @author ThinkGem
 * @version 2013-05-15
 */
@Service
@Transactional(readOnly = true)
public class StkScenerySpotService extends CrudService<StkScenerySpotDao, StkScenerySpot> {

	@Autowired
	private StkScenerySpotDao stkScenerySpotDao;
	
    /**
     * 切换数据源，查询景点列表
     * @author sa
     * @param 
     * @return
     */	
	@Transactional(readOnly = false)
	public List findAll() {
		//切换数据源
//		DynamicDataSource.setCurrentLookupKey("dataSource2");
		//查询景点列表
		List list = stkScenerySpotDao.findList();
		return list;
		
	}



	
	
	
	
}
