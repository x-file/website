/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cms.entity.CmsVisit;
import com.thinkgem.jeesite.modules.cms.dao.CmsVisitDao;

/**
 * 访问量统计Service
 * @author sa
 * @version 2015-09-28
 */
@Service
@Transactional(readOnly = true)
public class CmsVisitService extends CrudService<CmsVisitDao, CmsVisit> {

	public CmsVisit get(String id) {
		return super.get(id);
	}
	
	public List<CmsVisit> findList(CmsVisit cmsVisit) {
		return super.findList(cmsVisit);
	}
	
	public Page<CmsVisit> findPage(Page<CmsVisit> page, CmsVisit cmsVisit) {
		return super.findPage(page, cmsVisit);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsVisit cmsVisit) {
		super.save(cmsVisit);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsVisit cmsVisit) {
		super.delete(cmsVisit);
	}
	
}