<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->  
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->  
<!--[if !IE]><!--><html lang="zh"> <!--<![endif]--> 
<head>
<title><sitemesh:title/> - ${site.title} - 让旅途更有意义</title>
<sitemesh:head />
<%@include file="/WEB-INF/views/modules/cms/front/include/head.jsp"%>
</head>
<body>
	<div class="wrapper page-option-v1">
		<!--=== Header ===-->
		<div class="header">
			<div class="container">
				<!-- Logo -->
				<c:choose>
					<c:when test="${not empty site.logo}">
						<img alt="${site.title}" src="${site.logo}" class="container"
							onclick="location='${ctx}/index-${site.id}${fns:getUrlSuffix()}'">
					</c:when>
					<c:otherwise>
						<a class="logo"
							href="${ctx}/index-${site.id}${fns:getUrlSuffix()}"><img
							src="${ctxStatic}/unify/img/logo4-default.png" alt="Logo"></a>
					</c:otherwise>
				</c:choose>
				<!-- End Logo -->

				<!-- Topbar -->
				<div class="topbar">
					<ul class="loginbar pull-right">
						<li>来自:${visit.area }&nbsp;&nbsp;是第${visit.no}个访问本站的用户，当前在线:${online }</li>
						<li class="hoverSelector"><i class="fa fa-globe"></i> <a>语言</a>
							<ul class="languages hoverSelectorBlock">
								<li class="active"><a href="#">简体中文 <i
										class="fa fa-check"></i></a></li>
								<li><a href="#">繁體中文</a></li>
								<li><a href="#">English</a></li>
							</ul></li>
						<li class="topbar-devider"></li>
						<li><a href="#">帮助</a></li>
						<li class="topbar-devider"></li>
						<li><a href="#">登陆</a></li>
					</ul>
				</div>
				<!-- End Topbar -->

				<!-- Toggle get grouped for better mobile display -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-responsive-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="fa fa-bars"></span>
				</button>
				<!-- End Toggle -->
			</div>
			<!--/end container-->

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div
				class="collapse navbar-collapse mega-menu navbar-responsive-collapse">
				<div class="container">
					<ul class="nav navbar-nav">
						<c:forEach items="${fnc:getMainNavList(site.id)}" var="category"
							varStatus="status">
							<c:if test="${status.index lt 6}">
								<c:set var="menuCategoryId" value=",${category.id}," />
								<li
									class="${requestScope.category.id eq category.id||fn:indexOf(requestScope.category.parentIds,menuCategoryId) ge 2?'active':''}">
									<a href="${category.url}" target="${category.target}"><span>${category.name}</span></a>
								</li>
							</c:if>
						</c:forEach>

						<!-- Search Block -->
						<li><i class="search fa fa-search search-btn"></i>
							<div class="search-open">
								<form class="input-group animated fadeInDown" action="${ctx}/search" method="get">
									<input type="text" class="form-control" name="q" placeholder="Search" value="${q}">
									<span class="input-group-btn">
										<button class="btn-u" type="button">Go</button>
									</span>
								</form>
							</div></li>
						<!-- End Search Block -->
					</ul>
				</div>
				<!--/end container-->
			</div>
			<!--/navbar-collapse-->
		</div>
		<!--=== End Header ===-->

		<sitemesh:body />
		<!--=== End Footer Version 1 ===-->
	</div>
	<!--/wrapper-->





	<!-- JS Global Compulsory -->
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/jquery/jquery-migrate.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- JS Implementing Plugins -->
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/back-to-top.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/smoothScroll.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/jquery.parallax.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/owl-carousel/owl-carousel/owl.carousel.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/revolution-slider/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/revolution-slider/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>

	<script type="text/javascript" src="${ctxStatic}/unify/plugins/jquery.mixitup.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/parallax-slider/js/modernizr.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/plugins/parallax-slider/js/jquery.cslider.js"></script>

	<script type="text/javascript" src="${ctxStatic}/unify/plugins/image-hover/js/touch.js"></script>

	<!-- JS Customization -->
	<script type="text/javascript" src="${ctxStatic}/unify/js/custom.js"></script>
	<!-- JS Page Level -->
	<script type="text/javascript" src="${ctxStatic}/unify/js/app.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/js/plugins/fancy-box.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/js/plugins/owl-carousel.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/js/plugins/revolution-slider.js"></script>

	<script type="text/javascript" src="${ctxStatic}/unify/js/pages/page_portfolio.js"></script>
	<script type="text/javascript" src="${ctxStatic}/unify/js/plugins/parallax-slider.js"></script>

	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
			App.initParallaxBg();
			FancyBox.initFancybox();
			OwlCarousel.initOwlCarousel();
			RevolutionSlider.initRSfullWidth();
			PortfolioPage.init();
			ParallaxSlider.initParallaxSlider();
		});
	</script>
	<!--[if lt IE 9]>
    <script src="${ctxStatic}/unify/plugins/respond.js"></script>
    <script src="${ctxStatic}/unify/plugins/html5shiv.js"></script>
    <script src="${ctxStatic}/unify/plugins/placeholder-IE-fixes.js"></script>
<![endif]-->
</body>
</html>