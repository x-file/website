/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cms.entity.CmsVisit;

/**
 * 访问量统计DAO接口
 * @author sa
 * @version 2015-09-28
 */
@MyBatisDao
public interface CmsVisitDao extends CrudDao<CmsVisit> {
	
}