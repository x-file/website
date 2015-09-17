<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html> 
<!--[if IE 7]> <html lang="en" class="ie7"> <![endif]-->  
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->  
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->  
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->  
<head>
	<title>${video.title} - ${category.name}</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="${video.description} ${category.description}" />
	<meta name="keywords" content="${video.keywords} ${category.keywords}" />
	<script type="text/javascript">
		$(document).ready(function() {
			if ("${category.allowComment}"=="1" && "${video.videoData.allowComment}"=="1"){
				$("#comment").show();
				page(1);
			}
		});
		function page(n,s){
			$.get("${ctx}/comment",{theme: '${site.theme}', 'category.id': '${category.id}',
				contentId: '${video.id}', title: '${video.title}', pageNo: n, pageSize: s, date: new Date().getTime()
			},function(data){
				$("#comment").html(data);
			});
		}
	</script>
</head>
<body>
<div class="wrapper">
    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">视频播放页</h1>
            <ul class="pull-right breadcrumb">
            	<cms:frontCurrentPosition category="${category}"/>
            </ul>
        </div>
    </div><!--/breadcrumbs-->
    <!--=== End Breadcrumbs ===-->

    <!--=== Content Part ===-->
    <div class="container content">		
    	<div class="row blog-page blog-item">
            <!-- Left Sidebar -->
        	<div class="col-md-9 md-margin-bottom-60">
                <!--Blog Post-->        
                <div class="blog margin-bottom-40">
                    <div id="web_player" style="height: 500px">
                        <script type="text/javascript" src="${ctxStatic}/sewise/sewise.player.min.js?server=vod&type=flv&videourl=http://218.28.9.82:3333/stkweb/u/cms/www/201503/142030591rnv.flv&autostart=true&buffer=3&lang=zh_CN&logo=http://218.28.9.82:3333/stkweb/res/jeecms/js/player/logo.png&poster=http://218.28.9.82:3333/stkweb/res/jeecms/js/player/poster.png&title=展播&skin=vodWhite"></script>
                    </div>
                    <h2><a href="blog_item_option1.html">${video.title}</a></h2>
                    <div class="blog-post-tags">
                        <ul class="list-unstyled list-inline blog-tags">
                            <li><i class="fa fa-calendar"></i> <fmt:formatDate value="${video.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;</li>
                            <li><i class="fa fa-pencil"></i> ${video.user.name}</li>
                            <li><i class="fa fa-comments"></i> <a href="#">${video.hits}</a>&nbsp;&nbsp;</li>
                            <li>
                                <i class="fa fa-tags"></i> 
                                <a href="#">Technology</a> 
                                <a href="#">Education</a>
                                <a href="#">Internet</a>
                                <a href="#">Media</a>
                            </li>
                        </ul>                                               
                    </div>
                    <div class="tag-box tag-box-v2">
                        <p><c:if test="${not empty video.description}">摘要：${video.description}</c:if></p>
                    </div>
                    <p>${video.videoData.content}</p>   
                </div>
                <!--End Blog Post-->        

    			<hr>

                <!-- Recent Comments -->
                <div class="media">
                	<h3>评论</h3>
                    <a class="pull-left" href="#">
                        <img class="media-object" src="${ctxStatic}/unify/img/testimonials/img1.jpg" alt="" />
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading">Media heading <span>5 hours ago / <a href="#">Reply</a></span></h4>
                        <p>Donec id elit non mi portas sats eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna..</p>

                        <hr>

                        <div class="media">
                            <a class="pull-left" href="#">
                                <img class="media-object" src="${ctxStatic}/unify/img/testimonials/img2.jpg" alt="" />
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">Media heading <span>17 hours ago / <a href="#">Reply</a></span></h4>
                                <p>Donec id elit non mi portas sats eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum anibhut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna..</p>
                            </div>
                        </div>

                        <hr>

                        <div class="media">
                            <a class="pull-left" href="#">
                                <img class="media-object" src="${ctxStatic}/unify/img/testimonials/img3.jpg" alt="" />
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">Media heading <span>2 days ago / <a href="#">Reply</a></span></h4>
                                <p>Donec id elit non mi portas sats eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum anibhut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna..</p>
                            </div>
                        </div>
                    </div>
                </div><!--/media-->

                <div class="media">
                    <a class="pull-left" href="#">
                        <img class="media-object" src="${ctxStatic}/unify/img/testimonials/img4.jpg" alt="" />
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading">Media heading <span>July 5,2013 / <a href="#">Reply</a></span></h4>
                        <p>Donec id elit non mi portas sats eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna..</p>
                    </div>
                </div><!--/media-->
                <!-- End Recent Comments -->

                <hr>

                <!-- Comment Form -->
                <div class="post-comment">
                	<h3>发表评论</h3>
                    <form>
                        <label>Name</label>
                        <div class="row margin-bottom-20">
                            <div class="col-md-7 col-md-offset-0">
                                <input type="text" class="form-control">
                            </div>                
                        </div>
                        
                        <label>Email <span class="color-red">*</span></label>
                        <div class="row margin-bottom-20">
                            <div class="col-md-7 col-md-offset-0">
                                <input type="text" class="form-control">
                            </div>                
                        </div>
                        
                        <label>Message</label>
                        <div class="row margin-bottom-20">
                            <div class="col-md-11 col-md-offset-0">
                                <textarea class="form-control" rows="8"></textarea>
                            </div>                
                        </div>
                        
                        <p><button class="btn-u" type="submit">Send Message</button></p>
                    </form>
                </div>
                <!-- End Comment Form -->
            </div>
            <!-- End Left Sidebar -->

            <!-- Right Sidebar -->
            <div class="col-md-3 magazine-page">
                <!-- Search Bar -->
                <div class="headline headline-md"><h2>全文检索</h2></div>            
                <div class="input-group margin-bottom-40">
                    <input type="text" class="form-control" placeholder="Search">
                    <span class="input-group-btn">
                        <button class="btn-u" type="button">Go</button>
                    </span>
                </div>
                <!-- End Search Bar -->

                <!-- Posts -->
                <div class="posts margin-bottom-40">
                    <div class="headline headline-md"><h2>栏目列表</h2></div>
                    <c:forEach items="${relationList}" var="relation">
	                    <dl class="dl-horizontal">
	                        <dt><a href="${ctx}/view-${relation[0]}-${relation[1]}${urlSuffix}"><img src="${ctxStatic}/unify/img/sliders/elastislide/6.jpg" alt="" /></a></dt>
	                        <dd>
	                            <p><a href="${ctx}/view-${relation[0]}-${relation[1]}${urlSuffix}">${fns:abbr(relation[2],30)}</a></p> 
	                        </dd>
	                    </dl>
					</c:forEach>
                    <dl class="dl-horizontal">
                        <dt><a href="#"><img src="${ctxStatic}/unify/img/sliders/elastislide/6.jpg" alt="" /></a></dt>
                        <dd>
                            <p><a href="#">Responsive Bootstrap 3 Template placerat idelo alac eratamet.</a></p> 
                        </dd>
                    </dl>
                </div><!--/posts-->
                <!-- End Posts -->

                <!-- Photo Stream -->
                <div class="headline headline-md"><h2>Photo Stream</h2></div>
                <ul class="list-unstyled blog-photos margin-bottom-30">
                    <li><a href="#"><img class="hover-effect" alt="" src="${ctxStatic}/unify/img/sliders/elastislide/5.jpg"></a></li>
                    <li><a href="#"><img class="hover-effect" alt="" src="${ctxStatic}/unify/img/sliders/elastislide/6.jpg"></a></li>
                    <li><a href="#"><img class="hover-effect" alt="" src="${ctxStatic}/unify/img/sliders/elastislide/8.jpg"></a></li>
                    <li><a href="#"><img class="hover-effect" alt="" src="${ctxStatic}/unify/img/sliders/elastislide/10.jpg"></a></li>
                    <li><a href="#"><img class="hover-effect" alt="" src="${ctxStatic}/unify/img/sliders/elastislide/11.jpg"></a></li>
                    <li><a href="#"><img class="hover-effect" alt="" src="${ctxStatic}/unify/img/sliders/elastislide/1.jpg"></a></li>
                    <li><a href="#"><img class="hover-effect" alt="" src="${ctxStatic}/unify/img/sliders/elastislide/2.jpg"></a></li>
                    <li><a href="#"><img class="hover-effect" alt="" src="${ctxStatic}/unify/img/sliders/elastislide/7.jpg"></a></li>
                </ul>
                <!-- End Photo Stream -->

                <!-- Blog Tags -->
                <div class="headline headline-md"><h2>Blog Tags</h2></div>
                <ul class="list-unstyled blog-tags margin-bottom-30">
                    <li><a href="#"><i class="fa fa-tags"></i> Business</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> Music</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> Internet</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> Money</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> Google</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> TV Shows</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> Education</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> People</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> People</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> Math</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> Photos</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> Electronics</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> Apple</a></li>
                    <li><a href="#"><i class="fa fa-tags"></i> Canada</a></li>
                </ul>
                <!-- End Blog Tags -->

                <!-- Blog Latest Tweets -->
                <div class="blog-twitter margin-bottom-30">
                    <div class="headline headline-md"><h2>Latest Tweets</h2></div>
                    <div class="blog-twitter-inner">
                        <i class="fa fa-twitter"></i>
                        <a href="#">@htmlstream</a> 
                        At vero eos et accusamus et iusto odio dignissimos. 
                        <a href="#">http://t.co/sBav7dm</a> 
                        <span>5 hours ago</span>
                    </div>
                    <div class="blog-twitter-inner">
                        <i class="fa fa-twitter"></i>
                        <a href="#">@htmlstream</a> 
                        At vero eos et accusamus et iusto odio dignissimos. 
                        <a href="#">http://t.co/sBav7dm</a> 
                        <span>5 hours ago</span>
                    </div>
                    <c:forEach items="${fnc:getVideoList(category.site.id, category.id, not empty pageSize?pageSize:8, 'posid:2, orderBy: \"hits desc\"')}" var="video">
						<div class="blog-twitter-inner">
                        <i class="fa fa-twitter"></i>
                        <a href="#">@htmlstream</a> 
                        At vero eos et accusamus et iusto odio dignissimos. 
                        <a href="${ctx}/view-${video.category.id}-${video.id}${urlSuffix}" style="color:${video.color}" title="${video.title}">${fns:abbr(video.title,16)}</a> 
                        <span>5 hours ago</span>
                    </div>
					</c:forEach>
                    
                    <div class="blog-twitter-inner">
                    	<ul>
	                        <li>广东科士威</li>
	                        <li>广东科士威</li>
	                        <li>广东科士威</li>
                        </ul>
                    </div>
                </div>
                <!-- End Blog Latest Tweets -->
                
                <!-- Blog Posts -->
                <div class="row">
                    <div class="magazine-posts col-md-12 col-sm-6 margin-bottom-30">
                       <ul><cms:frontVideoHitsTop category="${category}"/></ul> 
                    </div>
                    <div class="magazine-posts col-md-12 col-sm-6">
                        <h3><a href="#">Parralax One Page</a></h3>
                        <span><i class="color-green">By Taylor Miller</i> / July 19, 2014</span>
                        <div class="magazine-posts-img">
                            <a href="#"><img class="img-responsive" src="${ctxStatic}/unify/img/main/img1.jpg" alt=""></a>
                            <span class="magazine-badge magazine-badge-green">Bootstrap 3</span>                                    
                        </div>
                    </div>                
                </div>            
                <!-- End Blog Posts -->
                
                
            </div>
            <!-- End Right Sidebar -->
        </div><!--/row-->        
    </div><!--/container-->		
    <!--=== End Content Part ===-->

</div><!--/wrapper-->
</body>

</html>