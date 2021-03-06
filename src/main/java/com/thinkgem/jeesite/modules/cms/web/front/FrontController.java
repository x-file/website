/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web.front;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.ip.IPSeeker;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.CmsVisit;
import com.thinkgem.jeesite.modules.cms.entity.Comment;
import com.thinkgem.jeesite.modules.cms.entity.Link;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.entity.Video;
import com.thinkgem.jeesite.modules.cms.service.ArticleDataService;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.service.CmsVisitService;
import com.thinkgem.jeesite.modules.cms.service.CommentService;
import com.thinkgem.jeesite.modules.cms.service.LinkService;
import com.thinkgem.jeesite.modules.cms.service.SiteService;
import com.thinkgem.jeesite.modules.cms.service.VideoDataService;
import com.thinkgem.jeesite.modules.cms.service.VideoService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.cms.utils.ScoketClient;

/**
 * 网站Controller
 * 
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class FrontController extends BaseController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private ArticleDataService articleDataService;
	@Autowired
	private VideoDataService videoDataService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SiteService siteService;
	@Autowired
	private CmsVisitService visitService;

	// 存放sessionId，用于计算当前在线人数，和网站方位次数 by sa
	Set<String> session_id_set = new HashSet<String>();
	//Map<sessionId,uuid>
	Map<String,String>map = new HashMap<String, String>();
	CmsVisit visit;
	

	/**
	 * 网站首页
	 */
	@RequestMapping
	public String index(Model model, HttpServletRequest request) {
		try {
			String uuid = map.get(request.getSession().getId());
			//判断map不为null并且有值，并且该sessionId存在于HashMap
			if(map!=null&&map.size()>0&&uuid!=null&&!uuid.equals("")){
				visit = visitService.get(map.get(request.getSession().getId()));
			}else{
				visit = visitService.get(doVisit(request));
			}
			
			// model.addAttribute("visit", visit);
			//model.addAttribute("online", session_id_set.size());
			request.getSession().setAttribute("visit", visit);
			request.getSession().setAttribute("online", session_id_set.size());
		} finally {
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			model.addAttribute("isIndex", true);
			return "modules/cms/front/themes/" + site.getTheme() + "/frontIndex";
		}

	}

	/**
	 * 网站首页
	 */
	@RequestMapping(value = "index-{siteId}${urlSuffix}")
	public String index(@PathVariable String siteId, Model model) {
		if (siteId.equals("1")) {
			return "redirect:" + Global.getFrontPath();
		}
		Site site = CmsUtils.getSite(siteId);
		// 子站有独立页面，则显示独立页面
		if (StringUtils.isNotBlank(site.getCustomIndexView())) {
			model.addAttribute("site", site);
			model.addAttribute("isIndex", true);
			return "modules/cms/front/themes/" + site.getTheme() + "/frontIndex" + site.getCustomIndexView();
		}
		// 否则显示子站第一个栏目
		List<Category> mainNavList = CmsUtils.getMainNavList(siteId);
		if (mainNavList.size() > 0) {
			String firstCategoryId = CmsUtils.getMainNavList(siteId).get(0).getId();
			return "redirect:" + Global.getFrontPath() + "/list-" + firstCategoryId + Global.getUrlSuffix();
		} else {
			model.addAttribute("site", site);
			return "modules/cms/front/themes/" + site.getTheme() + "/frontListCategory";
		}
	}

	/**
	 * 内容列表
	 */
	@RequestMapping(value = "list-{categoryId}${urlSuffix}")
	public String list(@PathVariable String categoryId,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(required = false, defaultValue = "15") Integer pageSize, Model model) {
		Category category = categoryService.get(categoryId);
		if (category == null) {
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		Site site = siteService.get(category.getSite().getId());
		model.addAttribute("site", site);
		// 2：简介类栏目，栏目第一条内容
		if ("2".equals(category.getShowModes()) && "article".equals(category.getModule())) {
			// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
			List<Category> categoryList = Lists.newArrayList();
			if (category.getParent().getId().equals("1")) {
				categoryList.add(category);
			} else {
				categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
			}
			model.addAttribute("category", category);
			model.addAttribute("categoryList", categoryList);
			// 获取文章内容
			Page<Article> page = new Page<Article>(1, 1, -1);
			Article article = new Article(category);
			page = articleService.findPage(page, article, false);
			if (page.getList().size() > 0) {
				article = page.getList().get(0);
				article.setArticleData(articleDataService.get(article.getId()));
				articleService.updateHitsAddOne(article.getId());
			}
			model.addAttribute("article", article);
			CmsUtils.addViewConfigAttribute(model, category);
			CmsUtils.addViewConfigAttribute(model, article.getViewConfig());
			return "modules/cms/front/themes/" + site.getTheme() + "/" + getTpl(article);
		} else {
			List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
			// 展现方式为1 、无子栏目或公共模型，显示栏目内容列表
			if ("1".equals(category.getShowModes()) || categoryList.size() == 0) {
				// 有子栏目并展现方式为1，则获取第一个子栏目；无子栏目，则获取同级分类列表。
				if (categoryList.size() > 0) {
					category = categoryList.get(0);
				} else {
					// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
					if (category.getParent().getId().equals("1")) {
						categoryList.add(category);
					} else {
						categoryList = categoryService.findByParentId(category.getParent().getId(),
								category.getSite().getId());
					}
				}
				model.addAttribute("category", category);
				model.addAttribute("categoryList", categoryList);
				// 获取内容列表
				if ("article".equals(category.getModule())) {
					Page<Article> page = new Page<Article>(pageNo, pageSize);
					// System.out.println(page.getPageNo());
					page = articleService.findPage(page, new Article(category), false);
					model.addAttribute("page", page);
					// 如果第一个子栏目为简介类栏目，则获取该栏目第一篇文章
					if ("2".equals(category.getShowModes())) {
						Article article = new Article(category);
						if (page.getList().size() > 0) {
							article = page.getList().get(0);
							article.setArticleData(articleDataService.get(article.getId()));
							articleService.updateHitsAddOne(article.getId());
						}
						model.addAttribute("article", article);
						CmsUtils.addViewConfigAttribute(model, category);
						CmsUtils.addViewConfigAttribute(model, article.getViewConfig());
						return "modules/cms/front/themes/" + site.getTheme() + "/" + getTpl(article);
					}
				} else if ("link".equals(category.getModule())) {
					Page<Link> page = new Page<Link>(1, -1);
					page = linkService.findPage(page, new Link(category), false);
					model.addAttribute("page", page);
				}
				String view = "/frontList";
				if (StringUtils.isNotBlank(category.getCustomListView())) {
					view = "/" + category.getCustomListView();
				}
				CmsUtils.addViewConfigAttribute(model, category);
				site = siteService.get(category.getSite().getId());
				// System.out.println("else 栏目第一条内容 _2
				// :"+category.getSite().getTheme()+","+site.getTheme());
				return "modules/cms/front/themes/" + siteService.get(category.getSite().getId()).getTheme() + view;
				// return
				// "modules/cms/front/themes/"+category.getSite().getTheme()+view;
			}
			// 有子栏目：显示子栏目列表
			else {
				model.addAttribute("category", category);
				model.addAttribute("categoryList", categoryList);
				String view = "/frontListCategory";
				if (StringUtils.isNotBlank(category.getCustomListView())) {
					view = "/" + category.getCustomListView();
				}
				CmsUtils.addViewConfigAttribute(model, category);
				return "modules/cms/front/themes/" + site.getTheme() + view;
			}
		}
	}

	/**
	 * 内容列表（通过url自定义视图）
	 */
	@RequestMapping(value = "listc-{categoryId}-{customView}${urlSuffix}")
	public String listCustom(@PathVariable String categoryId, @PathVariable String customView,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(required = false, defaultValue = "15") Integer pageSize, Model model) {
		Category category = categoryService.get(categoryId);
		if (category == null) {
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		Site site = siteService.get(category.getSite().getId());
		model.addAttribute("site", site);
		List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryList);
		CmsUtils.addViewConfigAttribute(model, category);
		return "modules/cms/front/themes/" + site.getTheme() + "/frontListCategory" + customView;
	}

	/**
	 * 显示内容
	 */
	@RequestMapping(value = "view-{categoryId}-{contentId}${urlSuffix}")
	public String view(@PathVariable String categoryId, @PathVariable String contentId, Model model) {
		Category category = categoryService.get(categoryId);
		if (category == null) {
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		model.addAttribute("site", category.getSite());
		// 文章内容页
		if ("article".equals(category.getModule())) {
			// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
			List<Category> categoryList = Lists.newArrayList();
			if (category.getParent().getId().equals("1")) {
				categoryList.add(category);
			} else {
				categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
			}
			// 获取文章内容
			Article article = articleService.get(contentId);
			if (article == null || !Article.DEL_FLAG_NORMAL.equals(article.getDelFlag())) {
				return "error/404";
			}
			// 文章阅读次数+1
			articleService.updateHitsAddOne(contentId);
			// 获取推荐文章列表
			List<Object[]> relationList = articleService
					.findByIds(articleDataService.get(article.getId()).getRelation());
			// 将数据传递到视图
			model.addAttribute("category", categoryService.get(article.getCategory().getId()));
			model.addAttribute("categoryList", categoryList);
			article.setArticleData(articleDataService.get(article.getId()));
			model.addAttribute("article", article);
			model.addAttribute("relationList", relationList);
			CmsUtils.addViewConfigAttribute(model, article.getCategory());
			CmsUtils.addViewConfigAttribute(model, article.getViewConfig());
			Site site = siteService.get(category.getSite().getId());
			model.addAttribute("site", site);
			// return
			// "modules/cms/front/themes/"+category.getSite().getTheme()+"/"+getTpl(article);
			return "modules/cms/front/themes/" + site.getTheme() + "/" + getTpl(article);

			// 视频内容页 by sa
			// 该页面需要对3种视频作相应的处理(1.点播、2.展播、3直播)
		} else if ("video".equals(category.getModule())) {
			// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
			List<Category> categoryList = Lists.newArrayList();
			if (category.getParent().getId().equals("1")) {
				categoryList.add(category);
			} else {
				categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
			}
			// 获取视频内容
			Video video = videoService.get(contentId);
			if (video == null || !Video.DEL_FLAG_NORMAL.equals(video.getDelFlag())) {
				return "error/404";
			} else {
				// 判断视频类型(1点播,2展播,3直播 )
				ScoketClient sc = new ScoketClient();
				String msg = "服务器你好，请给我视频地址";
				StringBuffer sb = sc.send("127.0.0.1", 8899, msg);
				// 1.默认点播，不做处理
				if (video.getVideoType() == 1) {
					// 2.展播,向中心服务器发送消息,获取展播地址
				} else if (video.getVideoType() == 2) {
					// 3.直播,向中心服务器发送消息，获取直播URL
				} else if (video.getVideoType() == 3) {

				}
			}

			// 文章阅读次数+1
			videoService.updateHitsAddOne(contentId);
			// 获取推荐视频列表
			List<Object[]> relationList = videoService.findByIds(videoDataService.get(video.getId()).getRelation());
			// 将数据传递到视图
			model.addAttribute("category", categoryService.get(video.getCategory().getId()));
			model.addAttribute("categoryList", categoryList);
			video.setVideoData(videoDataService.get(video.getId()));
			model.addAttribute("video", video);
			model.addAttribute("relationList", relationList);
			CmsUtils.addViewConfigAttribute(model, video.getCategory());
			CmsUtils.addViewConfigAttribute(model, video.getViewConfig());
			Site site = siteService.get(category.getSite().getId());
			model.addAttribute("site", site);
			// return
			// "modules/cms/front/themes/"+category.getSite().getTheme()+"/"+getTpl(article);
			return "modules/cms/front/themes/" + site.getTheme() + "/" + getVideoTpl(video);
		}
		return "error/404";
	}

	/**
	 * 内容评论
	 */
	@RequestMapping(value = "comment", method = RequestMethod.GET)
	public String comment(String theme, Comment comment, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<Comment> page = new Page<Comment>(request, response);
		Comment c = new Comment();
		c.setCategory(comment.getCategory());
		c.setContentId(comment.getContentId());
		c.setDelFlag(Comment.DEL_FLAG_NORMAL);
		page = commentService.findPage(page, c);
		model.addAttribute("page", page);
		model.addAttribute("comment", comment);
		return "modules/cms/front/themes/" + theme + "/frontComment";
	}

	/**
	 * 内容评论保存
	 */
	@ResponseBody
	@RequestMapping(value = "comment", method = RequestMethod.POST)
	public String commentSave(Comment comment, String validateCode, @RequestParam(required = false) String replyId,
			HttpServletRequest request) {
		if (StringUtils.isNotBlank(validateCode)) {
			if (ValidateCodeServlet.validate(request, validateCode)) {
				if (StringUtils.isNotBlank(replyId)) {
					Comment replyComment = commentService.get(replyId);
					if (replyComment != null) {
						comment.setContent("<div class=\"reply\">" + replyComment.getName() + ":<br/>"
								+ replyComment.getContent() + "</div>" + comment.getContent());
					}
				}
				comment.setIp(request.getRemoteAddr());
				comment.setCreateDate(new Date());
				comment.setDelFlag(Comment.DEL_FLAG_AUDIT);
				commentService.save(comment);
				return "{result:1, message:'提交成功。'}";
			} else {
				return "{result:2, message:'验证码不正确。'}";
			}
		} else {
			return "{result:2, message:'验证码不能为空。'}";
		}
	}

	/**
	 * 站点地图
	 */
	@RequestMapping(value = "map-{siteId}${urlSuffix}")
	public String map(@PathVariable String siteId, Model model) {
		Site site = CmsUtils.getSite(siteId != null ? siteId : Site.defaultSiteId());
		model.addAttribute("site", site);
		return "modules/cms/front/themes/" + site.getTheme() + "/frontMap";
	}

	/**
	 * 获取文章页模版
	 * 
	 * @param article
	 * @return by sa
	 */
	private String getTpl(Article article) {
		if (StringUtils.isBlank(article.getCustomContentView())) {
			String view = null;
			Category c = article.getCategory();
			boolean goon = true;
			do {
				if (StringUtils.isNotBlank(c.getCustomContentView())) {
					view = c.getCustomContentView();
					goon = false;
				} else if (c.getParent() == null || c.getParent().isRoot()) {
					goon = false;
				} else {
					c = c.getParent();
				}
			} while (goon);
			return StringUtils.isBlank(view) ? Article.DEFAULT_TEMPLATE : view;
		} else {
			return article.getCustomContentView();
		}
	}

	/**
	 * 获取视频页模版
	 * 
	 * @param video
	 * @return by sa
	 */
	private String getVideoTpl(Video video) {
		if (StringUtils.isBlank(video.getCustomContentView())) {
			String view = null;
			Category c = video.getCategory();
			boolean goon = true;
			do {
				if (StringUtils.isNotBlank(c.getCustomContentView())) {
					view = c.getCustomContentView();
					goon = false;
				} else if (c.getParent() == null || c.getParent().isRoot()) {
					goon = false;
				} else {
					c = c.getParent();
				}
			} while (goon);
			System.out.println(StringUtils.isBlank(view) ? Video.DEFAULT_TEMPLATE : view);
			return StringUtils.isBlank(view) ? Video.DEFAULT_TEMPLATE : view;
		} else {
			return video.getCustomContentView();
		}
	}

	/**
	 * 根据IP 获取 访客地址（国家/地区）并记录用户访问信息 判断用户session是否存在，不存在则累加
	 * 
	 * @param ip
	 * @return uuid
	 */
	public String doVisit(HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		// 获取访问者IP
		String ip = request.getRemoteAddr();
		try {
			if (!session_id_set.contains(sessionId)) {
				IPSeeker is = new IPSeeker();
				visit = new CmsVisit();
				// IP归属地
				String area = is.getIPLocation(ip).getCountry() + ":" + is.getIPLocation(ip).getArea();
				visit.setIp(ip);
				visit.setArea(area);
				visit.setTime(DateUtils.getDateTime());
				// 默认匿名
				visit.setMemberName("Anonymous");
				// 保存对象
				visitService.add(visit);
				// 将sessionId放入set
				session_id_set.add(sessionId);
				String id = visit.getId();
				map.put(sessionId, id);
				// 返回刚保存对象的ID
				return id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
