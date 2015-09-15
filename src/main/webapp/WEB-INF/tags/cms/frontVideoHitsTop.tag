<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ attribute name="category" type="com.thinkgem.jeesite.modules.cms.entity.Category" required="true" description="栏目对象"%>
<%@ attribute name="pageSize" type="java.lang.Integer" required="false" description="页面大小"%>
<c:forEach items="${fnc:getVideoList(category.site.id, category.id, not empty pageSize?pageSize:8, 'posid:2, orderBy: \"hits desc\"')}" var="video">
	<li><a href="${ctx}/view-${video.category.id}-${video.id}${urlSuffix}" style="color:${video.color}" title="${video.title}">${fns:abbr(video.title,16)}</a></li>
</c:forEach>