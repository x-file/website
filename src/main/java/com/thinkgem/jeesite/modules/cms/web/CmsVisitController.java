/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cms.entity.CmsVisit;
import com.thinkgem.jeesite.modules.cms.service.CmsVisitService;

/**
 * 访问量统计Controller
 * @author sa
 * @version 2015-09-28
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/cmsVisit")
public class CmsVisitController extends BaseController {

	@Autowired
	private CmsVisitService cmsVisitService;
	
	@ModelAttribute
	public CmsVisit get(@RequestParam(required=false) String id) {
		CmsVisit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cmsVisitService.get(id);
		}
		if (entity == null){
			entity = new CmsVisit();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:cmsVisit:view")
	@RequestMapping(value = {"list", ""})
	public String list(CmsVisit cmsVisit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CmsVisit> page = cmsVisitService.findPage(new Page<CmsVisit>(request, response), cmsVisit); 
		model.addAttribute("page", page);
		return "modules/cms/cmsVisitList";
	}

	@RequiresPermissions("cms:cmsVisit:view")
	@RequestMapping(value = "form")
	public String form(CmsVisit cmsVisit, Model model) {
		model.addAttribute("cmsVisit", cmsVisit);
		return "modules/cms/cmsVisitForm";
	}

	@RequiresPermissions("cms:cmsVisit:edit")
	@RequestMapping(value = "save")
	public String save(CmsVisit cmsVisit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cmsVisit)){
			return form(cmsVisit, model);
		}
		cmsVisitService.save(cmsVisit);
		addMessage(redirectAttributes, "保存访问量统计成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsVisit/?repage";
	}
	
	@RequiresPermissions("cms:cmsVisit:edit")
	@RequestMapping(value = "delete")
	public String delete(CmsVisit cmsVisit, RedirectAttributes redirectAttributes) {
		cmsVisitService.delete(cmsVisit);
		addMessage(redirectAttributes, "删除访问量统计成功");
		return "redirect:"+Global.getAdminPath()+"/cms/cmsVisit/?repage";
	}

}