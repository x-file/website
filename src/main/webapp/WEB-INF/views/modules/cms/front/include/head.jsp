<%@ page contentType="text/html;charset=UTF-8" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" /><meta http-equiv="Pragma" content="no-cache" /><meta http-equiv="Expires" content="0" />
<meta name="author" content=""/><meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10" />
<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico">

<!-- Web Fonts -->
<link rel='stylesheet' type='text/css' href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

<!-- CSS Global Compulsory -->
<link rel="stylesheet" href="${ctxStatic}/unify/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctxStatic}/unify/css/style.css">

<!-- CSS Header and Footer -->
<link rel="stylesheet" href="${ctxStatic}/unify/css/headers/header-default.css">
<link rel="stylesheet" href="${ctxStatic}/unify/css/footers/footer-v1.css">

<c:if test="${site.theme eq 'basic'}">
    <link rel="stylesheet" href="${ctxStatic}/unify/plugins/animate.css">
    <link rel="stylesheet" href="${ctxStatic}/unify/plugins/line-icons/line-icons.css">
    <link rel="stylesheet" href="${ctxStatic}/unify/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctxStatic}/unify/plugins/fancybox/source/jquery.fancybox.css">
    <link rel="stylesheet" href="${ctxStatic}/unify/plugins/owl-carousel/owl-carousel/owl.carousel.css">
    <link rel="stylesheet" href="${ctxStatic}/unify/plugins/revolution-slider/rs-plugin/css/settings.css" type="text/css" media="screen">

	<link rel="stylesheet" href="${ctxStatic}/unify/plugins/flexslider/flexslider.css">     
    <link rel="stylesheet" href="${ctxStatic}/unify/plugins/parallax-slider/css/parallax-slider.css">

	<link rel="stylesheet" href="${ctxStatic}/unify/plugins/image-hover/css/img-hover.css">

    <!--[if lt IE 9]><link rel="stylesheet" href="${ctxStatic}/unify/plugins/revolution-slider/rs-plugin/css/settings-ie8.css" type="text/css" media="screen"><![endif]-->

    <!-- CSS Customization -->
    <link rel="stylesheet" href="${ctxStatic}/unify/css/custom.css">

    <!-- CSS Page Style -->    
    <link rel="stylesheet" href="${ctxStatic}/unify/css/pages/portfolio-v2.css">
	<link rel="stylesheet" href="${ctxStatic}/unify/css/pages/page_job.css">
</c:if>