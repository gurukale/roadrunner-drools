<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="spring.data.jpa.example.title"/></title>
    <link rel="stylesheet" href="/static/css/styles.css" type="text/css"/>
</head>
<body>

<!-- Start: page-top-outer -->
<div id="page-top-outer">    

<!-- Start: page-top -->
<div id="page-top">

	<!-- start logo -->
	<div>
	<p class="nextgen"><spring:message code="app.logo.name"/></p>
	</div>
	<!-- end logo -->
	
	
 	<div class="clear"></div>

</div>
<!-- End: page-top -->

</div>
<!-- End: page-top-outer -->

<div class="messages">
    <c:if test="${feedbackMessage != null}">
        <div class="messageblock"><c:out value="${feedbackMessage}"/></div>
    </c:if>
    <c:if test="${errorMessage != null}">
        <div class="errorblock"><c:out value="${errorMessage}"/></div>
    </c:if>
<!--  start page-heading -->
	<div id="page-heading">
		<a href="/order/validate" class="hyperline"><spring:message code="order.create.link.validatesim"/></a>
	</div>
	<div id="page-heading">
		<h1><spring:message code="order.list.page.title"/></h1>
		<a href="/order/create" class="hyperline"><spring:message code="order.create.link.label"/></a>
	</div>
	<!-- end page-heading -->
    
</div>
<table border="1" class="listTable">
<thead>
	    
    <tr class="datacellone" >
        <td width = "20%" ><spring:message code="order.label.lastName"/></td>
        <td width = "20%" ><spring:message code="order.label.firstName"/></td>
        <td width = "15%" ><spring:message code="order.label.lastScreen"/></td>
        <td width = "15%" ><spring:message code="order.label.propositionCheck"/></td>
        <td width = "15%" ><spring:message code="order.label.extraCheck"/></td>
        <td width = "15%" ><spring:message code="order.label.giftCheck"/></td>
        <td width = "15%" ><spring:message code="order.label.insurance"/></td>
        <td width = "15%" ><spring:message code="order.label.proofsValid"/></td>
        <td width = "15%" ><spring:message code="order.label.creditCheckValid"/></td>
        <td width = "15%" ><spring:message code="order.label.fraudCheckValid"/></td>
        
        <td width = "15%" ><spring:message code="order.label.action"/></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td width = "20%" style="word-break:break-all;"><c:out value="${order.lastName}"/></td>
            <td width = "20%" style="word-break:break-all;"><c:out value="${order.firstName}"/></td>
            <td width = "15%" ><c:out value="${order.lastScreen}"/></td>
            <td width = "15%" ><c:out value="${order.propositionCheck}"/></td>
            <td width = "15%" ><c:out value="${order.extraCheck}"/></td>
            <td width = "15%" ><c:out value="${order.giftCheck}"/></td>
            <td width = "15%" ><c:out value="${order.insurance}"/></td>
            <td width = "15%" ><c:out value="${order.proofsValid}"/></td>
            <td width = "15%" ><c:out value="${order.creditCheckValid}"/></td>
            <td width = "15%" ><c:out value="${order.fraudCheckValid}"/></td>
            
            <td width = "15%" ><a href="/order/resume/<c:out value="${order.id}"/>"><spring:message code="order.edit.link.label"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>