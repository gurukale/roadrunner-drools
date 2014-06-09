<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title><spring:message code="spring.data.jpa.example.title" /></title>
<link rel="stylesheet" href="/static/css/styles.css" type="text/css" />
</head>
<body>
<!-- Start: page-top-outer -->
<div id="page-top-outer">    

<!-- Start: page-top -->
<div id="page-top">

	<!-- start logo -->
	<div>
	<p class="nextgen">RoadRunner</p>
	</div>
	<!-- end logo -->
	
	
 	<div class="clear"></div>

</div>
<!-- End: page-top -->

</div>
<!-- End: page-top-outer -->
<div class="clear"></div>
<!--  start page-heading -->
	<div id="page-heading">
	<a href="/" class="hyperline" accesskey=""><spring:message code="person.list.link.label" /></a>
	
	<h1><spring:message code="resume.label.result" /></h1>
	</div>
<!-- end page-heading -->


	${page}
</body>
</html>