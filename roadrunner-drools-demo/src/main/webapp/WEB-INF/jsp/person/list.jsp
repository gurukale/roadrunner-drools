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
	<p class="nextgen">RoadRunner</p>
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
		<a href="/person/validate" class="hyperline"><spring:message code="person.create.link.validatesim"/></a>
	</div>
	<div id="page-heading">
		<h1><spring:message code="person.list.page.title"/></h1>
		<a href="/person/create" class="hyperline"><spring:message code="person.create.link.label"/></a>
	</div>
	<!-- end page-heading -->
    
</div>
<table border="1" class="listTable">
<thead>
	    
    <tr class="datacellone" >
        <td width = "20%" ><spring:message code="person.label.lastName"/></td>
        <td width = "20%" ><spring:message code="person.label.firstName"/></td>
        <td width = "15%" ><spring:message code="person.label.lastScreen"/></td>
        <td width = "15%" ><spring:message code="person.label.valdityCheck"/></td>
        <td width = "15%" ><spring:message code="person.label.valdityCheckValue"/></td>
        <td width = "15%" ><spring:message code="person.label.action"/></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${persons}" var="person">
        <tr>
            <td width = "20%" style="word-break:break-all;"><c:out value="${person.lastName}"/></td>
            <td width = "20%" style="word-break:break-all;"><c:out value="${person.firstName}"/></td>
            <td width = "15%" ><c:out value="${person.lastScreen}"/></td>
            <td width = "15%" ><c:out value="${person.validityCheck}"/></td>
            <td width = "15%" ><c:out value="${person.validityCheckValue}"/></td>
            <td width = "15%" ><a href="/person/resume/<c:out value="${person.id}"/>"><spring:message code="person.edit.link.label"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>