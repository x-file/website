/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.entity.Video;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.service.FileTplService;
import com.thinkgem.jeesite.modules.cms.service.SiteService;
import com.thinkgem.jeesite.modules.cms.service.StkScenerySpotService;
import com.thinkgem.jeesite.modules.cms.service.VideoDataService;
import com.thinkgem.jeesite.modules.cms.service.VideoService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.cms.utils.TplUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 视频Controller
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/video")
public class VideoController extends BaseController {

	@Autowired
	private VideoService videoService;
	@Autowired
	private VideoDataService videoDataService;
	@Autowired
	private CategoryService categoryService;
    @Autowired
   	private FileTplService fileTplService;
    @Autowired
   	private SiteService siteService;
    
    @Autowired
    private StkScenerySpotService stkScenerySpotService;
	
    //@ModelAttribute注解的方法会在此controller中每个方法执行前被执行
	@ModelAttribute
	public Video get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return videoService.get(id);
		}else{
			return new Video();
		}
	}
	
	//@RequestMapping(value = {"list", ""})表示：可以通过/list或者/来调用
	@RequiresPermissions("cms:video:view")
	@RequestMapping(value = {"list", ""})
	public String list(Video video, HttpServletRequest request, HttpServletResponse response, Model model) {
//		for (int i=0; i<10000000; i++){
//			Article a = new Article();
//			a.setCategory(new Category(article.getCategory().getId()));
//			a.setTitle("测试测试测试测试测试测试测试测试"+a.getCategory().getId());
//			a.setArticleData(new ArticleData());
//			a.getArticleData().setContent(a.getTitle());
//			articleService.save(a);
//		}
		
        Page<Video> page = videoService.findPage(new Page<Video>(request, response), video, true);
        DynamicDataSource.setCurrentLookupKey("dataSource2");
		List list = stkScenerySpotService.findAll();
        model.addAttribute("page", page);
		return "modules/cms/videoList";
	}

	@RequiresPermissions("cms:video:view")
	@RequestMapping(value = "form")
	public String form(Video video, Model model) {
		// 如果当前传参有子节点，则选择取消传参选择
		if (video.getCategory()!=null && StringUtils.isNotBlank(video.getCategory().getId())){
			List<Category> list = categoryService.findByParentId(video.getCategory().getId(), Site.getCurrentSiteId());
			if (list.size() > 0){
				video.setCategory(null);
			}else{
				video.setCategory(categoryService.get(video.getCategory().getId()));
			}
		}
		video.setVideoData(videoDataService.get(video.getId()));
//		if (article.getCategory()=null && StringUtils.isNotBlank(article.getCategory().getId())){
//			Category category = categoryService.get(article.getCategory().getId());
//		}
        model.addAttribute("contentViewList",getTplContent());
        model.addAttribute("video_DEFAULT_TEMPLATE",Video.DEFAULT_TEMPLATE);
		model.addAttribute("video", video);
		CmsUtils.addViewConfigAttribute(model, video.getCategory());
		return "modules/cms/videoForm";
	}

	@RequiresPermissions("cms:video:edit")
	@RequestMapping(value = "save")
	public String save(Video video, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, video)){
			return form(video, model);
		}
		videoService.save(video);
		addMessage(redirectAttributes, "保存视频'" + StringUtils.abbr(video.getTitle(),50) + "'成功");
		String categoryId = video.getCategory()!=null?video.getCategory().getId():null;
		return "redirect:" + adminPath + "/cms/video/?repage&category.id="+(categoryId!=null?categoryId:"");
	}
	
	@RequiresPermissions("cms:video:edit")
	@RequestMapping(value = "delete")
	public String delete(Video video, String categoryId, @RequestParam(required=false) Boolean isRe, RedirectAttributes redirectAttributes) {
		// 如果没有审核权限，则不允许删除或发布。
		if (!UserUtils.getSubject().isPermitted("cms:video:audit")){
			addMessage(redirectAttributes, "你没有删除或发布权限");
		}
		videoService.delete(video, isRe);
		addMessage(redirectAttributes, (isRe!=null&&isRe?"发布":"删除")+"视频成功");
		return "redirect:" + adminPath + "/cms/video/?repage&category.id="+(categoryId!=null?categoryId:"");
	}

	/**
	 * 视频选择列表
	 */
	@RequiresPermissions("cms:video:view")
	@RequestMapping(value = "selectList")
	public String selectList(Video video, HttpServletRequest request, HttpServletResponse response, Model model) {
        list(video, request, response, model);
		return "modules/cms/videoSelectList";
	}
	
	/**
	 * 通过编号获取视频标题
	 */
	@RequiresPermissions("cms:video:view")
	@ResponseBody
	@RequestMapping(value = "findByIds")
	public String findByIds(String ids) {
		List<Object[]> list = videoService.findByIds(ids);
		return JsonMapper.nonDefaultMapper().toJson(list);
	}

    private List<String> getTplContent() {
   		List<String> tplList = fileTplService.getNameListByPrefix(siteService.get(Site.getCurrentSiteId()).getSolutionPath());
   		tplList = TplUtils.tplTrim(tplList, Video.DEFAULT_TEMPLATE, "");
   		return tplList;
   	}
}
