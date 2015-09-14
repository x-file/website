<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>全站搜索</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="${site.description}" />
	<meta name="keywords" content="${site.keywords}" />
	<c:if test="${not empty message}"><script type="text/javascript">alert("${message}");</script></c:if>
</head>
<body>
<div class="wrapper">
    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs breadcrumbs-dark">
        <div class="container">
            <h1 class="pull-left">全站检索</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">Page</a></li>
                <li class="active">Search results</li>
            </ul>
        </div>
    </div>
    <!--=== End Breadcrumbs ===-->
    
    <!--=== Search Block Version 2 ===-->
    <div class="search-block-v2">
        <div class="container">
            <div class="col-md-6 col-md-offset-3">
                <h2>Search again</h2>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search again...">
                    <span class="input-group-btn">
                        <button class="btn-u" type="button"><i class="fa fa-search"></i></button>
                    </span>
                </div>
            </div>
        </div>    
    </div><!--/container-->     
    <!--=== End Search Block Version 2 ===-->

    <!--=== Search Results ===-->
    <div class="container s-results margin-bottom-50">
        <span class="results-number">About 384,907 results</span>
        <!-- Begin Inner Results -->
        <div class="inner-results">
            <h3><a href="#">Web design</a></h3>
            <ul class="list-inline up-ul">
                <li>en.wikipedia.org/wiki/Web_design‎</li>
                <li class="btn-group">
                    <button data-toggle="dropdown" class="btn btn-default dropdown-toggle" type="button">
                        More<i class="fa fa-caret-down margin-left-5"></i>
                        <span class="sr-only">Toggle Dropdown</span>                            
                    </button>
                    <ul role="menu" class="dropdown-menu">
                        <li><a href="#">Share</a></li>
                        <li><a href="#">Similar</a></li>
                        <li><a href="#">Advanced search</a></li>
                    </ul>
                </li>
                <li><a href="#">Wrapbootstrap</a></li>
                <li><a href="#">Dribbble</a></li>
            </ul>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ut orci urna. Morbi blandit enim eget risus posuere dapibus. Vestibulum velit nisi, tempus in placerat non, auctor eu purus. Morbi suscipit porta libero, ac tempus tellus consectetur non. Praesent eget consectetur nunc. Aliquam erat volutpat. Suspendisse ultrices eros eros, consectetur facilisis urna posuere id.</p>
            <ul class="list-inline down-ul">
                <li>
                    <ul class="list-inline star-vote">
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star-half-o"></i></li>
                    </ul>
                </li>
                <li>3 years ago - By Anthon Brandley</li>
                <li>234,034 views</li>
                <li><a href="#">Web designer</a></li>
            </ul>    
        </div>
        <!-- Begin Inner Results -->

        <hr>

        <!-- Begin Inner Results -->
        <div class="inner-results">
            <h3><a href="#">WrapBootstrap - Bootstrap Themes &amp; Templates</a></h3>
            <ul class="list-inline up-ul">
                <li>https://wrapbootstrap.com/‎</li>
                <li class="btn-group">
                    <button data-toggle="dropdown" class="btn btn-default dropdown-toggle" type="button">
                        More<i class="fa fa-caret-down margin-left-5"></i>
                        <span class="sr-only">Toggle Dropdown</span>                            
                    </button>
                    <ul role="menu" class="dropdown-menu">
                        <li><a href="#">Share</a></li>
                        <li><a href="#">Similar</a></li>
                        <li><a href="#">Advanced search</a></li>
                    </ul>
                </li>
                <li><a href="#">Admin</a></li>
                <li><a href="#">Template</a></li>
                <li><a href="#">OnePage Template</a></li>
                <li><a href="#">Joomla</a></li>
            </ul>
            <div class="overflow-h">
                <img src="assets/img/testimonials/img1.jpg" alt="">
                <div class="overflow-a">
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ut orci urna. Morbi blandit enim eget risus posuere dapibus. Vestibulum velit nisi, tempus in placerat non, auctor eu purus. Morbi suscipit porta libero, ac tempus tellus consectetur non. Praesent eget consectetur nunc. Aliquam erat volutpat. Suspendisse ultrices eros eros, consectetur facilisis urna posuere id.</p>
                    <ul class="list-inline down-ul">
                        <li>
                            <ul class="list-inline star-vote">
                                <li><i class="color-green fa fa-star"></i></li>
                                <li><i class="color-green fa fa-star"></i></li>
                                <li><i class="color-green fa fa-star"></i></li>
                                <li><i class="color-green fa fa-star"></i></li>
                                <li><i class="color-green fa fa-star-half-o"></i></li>
                            </ul>
                        </li>
                        <li>11 months ago - By WrapBootstrap</li>
                        <li>2,092,675 views</li>
                    </ul>
                </div>       
            </div>    
        </div>
        <!-- Begin Inner Results -->
        
        <div class="margin-bottom-30"></div>

        <div class="text-left">
            <ul class="pagination">
                <li><a href="#">«</a></li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">...</a></li>
                <li><a href="#">157</a></li>
                <li><a href="#">158</a></li>
                <li><a href="#">»</a></li>
            </ul>                                                            
        </div>
    </div><!--/container-->     
    <!--=== End Search Results ===-->

    
</div><!--/End Wrapepr-->


	<form:form id="searchForm" method="get" class="search">
		<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}"/>
		<input type="hidden" id="t" name="t" value="${not empty t?t:'article'}"/>
		<input type="hidden" id="cid" name="cid" value="${cid}"/>
		<input type="hidden" id="a" name="a" value="${not empty t?t:'0'}"/>
		<div class="sel">
			<a href="javascript:" onclick="$('#t').val('article');$('.sel a').removeClass('act');$(this).addClass('act')" class="${empty t || t eq 'article'?'act':''}">文章搜索</a> &nbsp;
			<a href="javascript:" onclick="$('#t').val('guestbook');$('.sel a').removeClass('act');$(this).addClass('act')" class="${t eq 'guestbook'?'act':''}">留言搜索</a>
		</div>
		<c:choose>
			<c:when test="${param.a eq '1'}">
				<table>
					<tr><td>包含以下<strong>任意一个</strong>关键词</td><td><input type="text" name="q" value="${q}" class="txt"/>
						<input type="submit" value="搜  索" class="btn" onclick="$('#a').val('1')"/>
						<input type="submit" value="简单搜索" class="btn" onclick="$('#a').val('0')"/></td></tr>
				</table>
			</c:when><c:otherwise>
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}"/>
				<input type="text" name="q" value="${q}" class="txt"/>
				<input type="submit" value="搜  索" class="btn" onclick="$('#a').val('0')"/>
				<input type="submit" value="高级搜索" class="btn" onclick="$('#a').val('1')"/>
			</c:otherwise>
		</c:choose>
	</form:form>
	<dl class="search">
		<c:if test="${empty t || t eq 'article'}">
			<c:forEach items="${page.list}" var="article">
				<dt><a href="${ctx}/view-${article.category.id}-${article.id}${urlSuffix}" class="title" target="_blank">${article.title}</a></dt>
				<dd>${article.description}<span class="info"><br/>发布者：${article.createBy.name} &nbsp; 点击数：${article.hits} &nbsp; 发布时间：<fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp; 更新时间：<fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					&nbsp;&nbsp;<a href="${ctx}/view-${article.category.id}-${article.id}${urlSuffix}" target="_blank">查看全文</a><br/></dd>
			</c:forEach>
		</c:if>
		<c:if test="${t eq 'guestbook'}">
			<c:forEach items="${page.list}" var="guestbook"><dt>${fns:getDictLabel(guestbook.type,'cms_guestbook','')}</dt>
				<dd>${guestbook.content}<span class="info"><br/>姓名：${guestbook.name} &nbsp; 留言时间：<fmt:formatDate value="${guestbook.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></dd>
				<dd>回复：${guestbook.reContent}<span class="info"><br/>回复人：${guestbook.reUser.name} &nbsp; 回复时间：<fmt:formatDate value="${guestbook.reDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></dd>
			</c:forEach>
		</c:if>
		<c:if test="${fn:length(page.list) eq 0}">
			<dt><c:if test="${empty q}">请键入要查找的关键字。</c:if><c:if test="${not empty q}">抱歉，没有找到与“${q}”相关内容。</c:if><br/><br/>建议：</dt>
			<dd><ul><li>检查输入是否正确；</li><li>简化输入词；</li><li>尝试其他相关词，如同义、近义词等。</li></ul></dd>
		</c:if>
	</dl>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</body>
</html>