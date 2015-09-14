<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="cms_default_${site.theme}" />
<meta name="description" content="JeeSite ${site.description}" />
<meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body>
	<!--=== Slider ===-->
	<div class="tp-banner-container">
		<div class="tp-banner">
			<ul>
				<!-- SLIDE -->
				<li class="revolution-mch-1" data-transition="fade"
					data-slotamount="5" data-masterspeed="1000" data-title="Slide 1">
					<!-- MAIN IMAGE --> <img
					src="${ctxStatic}/unify/img/sliders/shaolin.png" alt="darkblurbg"
					data-bgfit="cover" data-bgposition="left top"
					data-bgrepeat="no-repeat">

					<div class="tp-caption revolution-ch1 sft start" data-x="center"
						data-hoffset="0" data-y="100" data-speed="1500" data-start="500"
						data-easing="Back.easeInOut" data-endeasing="Power1.easeIn"
						data-endspeed="300">少林寺 天下第一名剎</div> <!-- LAYER -->
					<div class="tp-caption re-text-v1 sft" data-x="center"
						data-hoffset="0" data-y="190" data-speed="1400" data-start="2000"
						data-easing="Power4.easeOut" data-endspeed="300"
						data-endeasing="Power1.easeIn" data-captionhidden="off"
						style="z-index: 6">
						少林寺位于河南省登封市嵩山五乳峰下</br> 由于其坐落于嵩山腹地少室山的茂密丛林之中，故名“少林寺”
					</div> <!-- LAYER -->
					<div class="tp-caption sft" data-x="center" data-hoffset="0"
						data-y="310" data-speed="1600" data-start="2800"
						data-easing="Power4.easeOut" data-endspeed="300"
						data-endeasing="Power1.easeIn" data-captionhidden="off"
						style="z-index: 6">
						<a href="http://218.28.9.82:3333/stkweb/"
							class="btn-u btn-brd btn-brd-hover btn-u-light">了解更多...</a> <a
							href="http://218.28.9.82:3333/stkweb/ss/600.htm" class="btn-u">在线观景</a>
					</div>
				</li>
				<!-- END SLIDE -->

				<!-- SLIDE -->
				<li class="revolution-mch-1" data-transition="fade"
					data-slotamount="5" data-masterspeed="1000" data-title="Slide 2">
					<!-- MAIN IMAGE --> <img
					src="${ctxStatic}/unify/img/sliders/qm.png" alt="darkblurbg"
					data-bgfit="cover" data-bgposition="left top"
					data-bgrepeat="no-repeat">

					<div class="tp-caption revolution-ch1 sft start" data-x="center"
						data-hoffset="0" data-y="100" data-speed="1500" data-start="500"
						data-easing="Back.easeInOut" data-endeasing="Power1.easeIn"
						data-endspeed="300">清明上河园</div> <!-- LAYER -->
					<div class="tp-caption revolution-ch2 sft" data-x="center"
						data-hoffset="0" data-y="190" data-speed="1400" data-start="2000"
						data-easing="Power4.easeOut" data-endspeed="300"
						data-endeasing="Power1.easeIn" data-captionhidden="off"
						style="z-index: 6">
						以画家张择端的写实画作《清明上河图》为蓝本 <br /> 再现了古都汴京千年繁华的胜景。
					</div> <!-- LAYER -->
					<div class="tp-caption sft" data-x="center" data-hoffset="0"
						data-y="310" data-speed="1600" data-start="2800"
						data-easing="Power4.easeOut" data-endspeed="300"
						data-endeasing="Power1.easeIn" data-captionhidden="off"
						style="z-index: 6">
						<a href="#" class="btn-u btn-brd btn-brd-hover btn-u-light">了解更多...</a>
						<a href="#" class="btn-u btn-brd btn-brd-hover btn-u-light">在线观景</a>
					</div>
				</li>
				<!-- END SLIDE -->

				<!-- SLIDE -->
				<li class="revolution-mch-1" data-transition="fade"
					data-slotamount="5" data-masterspeed="1000" data-title="Slide 3">
					<!-- MAIN IMAGE --> <img
					src="${ctxStatic}/unify/img/sliders/yuntaishan.png"
					alt="darkblurbg" data-bgfit="cover" data-bgposition="left top"
					data-bgrepeat="no-repeat">

					<div class="tp-caption revolution-ch1 sft start" data-x="center"
						data-hoffset="0" data-y="100" data-speed="1500" data-start="500"
						data-easing="Back.easeInOut" data-endeasing="Power1.easeIn"
						data-endspeed="300">云台山---全球首批世界地质公园</div> <!-- LAYER -->
					<div class="tp-caption revolution-ch2 sft" data-x="center"
						data-hoffset="0" data-y="190" data-speed="1400" data-start="2000"
						data-easing="Power4.easeOut" data-endspeed="300"
						data-endeasing="Power1.easeIn" data-captionhidden="off"
						style="z-index: 6">
						难得淸嘉，梦里仙源能几处？<br /> 堪称上善，人间好水数云台。
					</div> <!-- LAYER -->
					<div class="tp-caption sft" data-x="center" data-hoffset="0"
						data-y="310" data-speed="1600" data-start="2800"
						data-easing="Power4.easeOut" data-endspeed="300"
						data-endeasing="Power1.easeIn" data-captionhidden="off"
						style="z-index: 6">
						<a href="#" class="btn-u btn-brd btn-brd-hover btn-u-light">了解更多...</a>
						<a href="#" class="btn-u btn-brd btn-brd-hover btn-u-light">在线观景</a>
					</div>
				</li>
				<!-- END SLIDE -->
			</ul>
			<div class="tp-bannertimer tp-bottom"></div>
		</div>
	</div>
	<!--=== End Slider ===-->
	
	<!--=== Container Part ===-->
    <div class="container content-md">
        <ul class="row block-grid-v2">        
            <li class="col-md-4 col-sm-6 md-margin-bottom-30">
                <div class="easy-block-v1">
                    <a href="http://218.28.9.82:3333/stkweb/"><img class="img-responsive" src="${ctxStatic}/unify/img/mockup/shaolin.png" alt=""></a>
                    <div class="easy-block-v1-badge rgba-purple">在线观景</div>
                </div>
                <div class="block-grid-v2-info rounded-bottom">
                    <h3><a href="http://218.28.9.82:3333/stkweb/">少林寺</a></h3>
                    <p>少林寺位于河南省登封市嵩山五乳峰下，由于其坐落于嵩山腹地少室山的茂密丛林之中，故名“少林寺”。</p>
                    <ul class="list-inline star-vote">
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star-half-o"></i></li>
                    </ul>
                </div>
            </li>
            <li class="col-md-4 col-sm-6 md-margin-bottom-30">
                <div class="easy-block-v1">
                    <img class="img-responsive" src="${ctxStatic}/unify/img/mockup/qm.png" alt="">
                    <div class="easy-block-v1-badge rgba-blue">攻略</div>                    
                </div>
                <div class="block-grid-v2-info rounded-bottom">
                    <h3><a href="#">清明上河园</a></h3>
                    <p>	以画家张择端的写实画作《清明上河图》为蓝本，再现了古都汴京千年繁华的胜景。</p>
                    <ul class="list-inline star-vote">
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star-half-o"></i></li>
                        <li><i class="color-green fa fa-star-o"></i></li>
                    </ul>
                </div>
            </li>
            <li class="col-md-4 col-sm-12">
                <div class="easy-block-v1">
                    <img class="img-responsive" src="${ctxStatic}/unify/img/mockup/yuntai.png" alt="">
                    <div class="easy-block-v1-badge rgba-red">实时</div>
                </div>
                <div class="block-grid-v2-info rounded-bottom">
                    <h3><a href="#">云台山</a></h3>
                    <p>云台山在远古时代乃是一片汪洋，随着世纪的流逝，地壳的变动，逐渐升起、抬高形成平原。</p>
                    <ul class="list-inline star-vote">
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star-o"></i></li>
                    </ul>
                </div>
            </li>

			<div class="clearfix margin-bottom-20"></div>

			<li class="col-md-4 col-sm-6 md-margin-bottom-30">
                <div class="easy-block-v1">
                    <img class="img-responsive" src="${ctxStatic}/unify/img/mockup/longmen.png" alt="">
                    <div class="easy-block-v1-badge rgba-purple">在线观景</div>
                </div>
                <div class="block-grid-v2-info rounded-bottom">
                    <h3><a href="#">龙门石窟</a></h3>
                    <p>龙门石窟始开凿于公元493年前后，历经东西魏、北齐、北周、隋唐等朝代又连续大规模营造达400余年之久。</p>
                    <ul class="list-inline star-vote">
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star-half-o"></i></li>
                    </ul>
                </div>
            </li>
            <li class="col-md-4 col-sm-6 md-margin-bottom-30">
                <div class="easy-block-v1">
                    <img class="img-responsive" src="${ctxStatic}/unify/img/mockup/shennongshan.png" alt="">
                    <div class="easy-block-v1-badge rgba-blue">攻略</div>                    
                </div>
                <div class="block-grid-v2-info rounded-bottom">
                    <h3><a href="#">沁阳神农山</a></h3>
                    <p>远古时期，炎帝神农在此设坛祭天；西晋女道士魏华存在此修道42年，著述了被称为“四大天书”之一的《黄庭经》</p>
                    <ul class="list-inline star-vote">
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star-half-o"></i></li>
                        <li><i class="color-green fa fa-star-o"></i></li>
                    </ul>
                </div>
            </li>
            <li class="col-md-4 col-sm-12">
                <div class="easy-block-v1">
                    <img class="img-responsive" src="${ctxStatic}/unify/img/mockup/longtan.png" alt="">
                    <div class="easy-block-v1-badge rgba-red">实时</div>
                </div>
                <div class="block-grid-v2-info rounded-bottom">
                    <h3><a href="#">龙潭大峡谷</a></h3>
                    <p>享有“中国嶂谷第一峡”、“古海洋天然博物馆”、“峡谷绝品”和“黄河水画廊”等美名。</p>
                    <ul class="list-inline star-vote">
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star"></i></li>
                        <li><i class="color-green fa fa-star-o"></i></li>
                    </ul>
                </div>
            </li>
        </ul>
    </div><!--/container-->
    <!--=== Container Part ===-->

	<div class="hero-unit" style="padding-bottom: 35px; margin: 10px 0;">
		<c:set var="article" value="${fnc:getArticle('2')}" />
		<h1>${fns:abbr(article.title,28)}</h1>
		<p></p>
		<p>${fns:abbr(fns:replaceHtml(article.articleData.content),260)}</p>
		<p>
			<a href="${article.url}" class="btn btn-primary btn-large">&nbsp;&nbsp;&nbsp;查看详情
				&raquo;&nbsp;&nbsp;&nbsp;</a>
		</p>
	</div>
	<div class="row">
		<div class="span4">
			<h4>
				<small><a href="${ctx}/list-2${urlSuffix}"
					class="pull-right">更多&gt;&gt;</a></small>组织机构
			</h4>
			<ul>
				<c:forEach items="${fnc:getArticleList(site.id, 2, 8, '')}"
					var="article">
					<li><span class="pull-right"><fmt:formatDate
								value="${article.updateDate}" pattern="yyyy.MM.dd" /></span><a
						href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,28)}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="span4">
			<h4>
				<small><a href="${ctx}/list-6${urlSuffix}"
					class="pull-right">更多&gt;&gt;</a></small>质量监督
			</h4>
			<ul>
				<c:forEach items="${fnc:getArticleList(site.id, 6, 8, '')}"
					var="article">
					<li><span class="pull-right"><fmt:formatDate
								value="${article.updateDate}" pattern="yyyy.MM.dd" /></span><a
						href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,28)}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="span4">
			<h4>
				<small><a href="${ctx}/list-10${urlSuffix}"
					class="pull-right">更多&gt;&gt;</a></small>政策法规
			</h4>
			<ul>
				<c:forEach items="${fnc:getArticleList(site.id, 10, 8, '')}"
					var="article">
					<li><span class="pull-right"><fmt:formatDate
								value="${article.updateDate}" pattern="yyyy.MM.dd" /></span><a
						href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,28)}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>