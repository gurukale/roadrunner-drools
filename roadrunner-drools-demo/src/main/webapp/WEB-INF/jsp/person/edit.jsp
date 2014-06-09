<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<!--  start page-heading -->
	<div id="page-heading">
		<h1><spring:message code="person.edit.page.title"/></h1>
		<a href="/" class="hyperline"><spring:message code="person.list.link.label"/></a>
	</div>
<!-- end page-heading -->

<div class=createpg>
    <form:form action="/person/validate" commandName="mobilenumber" method="POST">
        <table border="0">
        <tr>
            <td><form:label path="network"><spring:message code="person.label.network"/>:</form:label></td>
            <td><form:input path="network" size="20"/></td>
            <td><form:errors path="network" cssClass="error" element="div"/></td>
        </tr>
        <tr>
            <td><form:label path="imei"><spring:message code="person.label.imei"/>:</form:label></td>
            <td><form:input path="imei" size="20"/></td>
            <td><form:errors path="imei" cssClass="error" element="div"/></td>
        </tr>
         <tr><td>
            <form:label path="prefix"><spring:message code="person.label.prefix"/>:</form:label></td>
            <td><form:input path="prefix" size="20"/></td>
            <td><form:errors path="prefix" cssClass="error" element="div"/></td>
        </tr>
        <tr><td>
            <form:label path="luhnCheckDone"><spring:message code="person.label.LuhnCheck"/>:</form:label></td>
            <td><form:checkbox path="luhnCheckDone" name ="luhnCheckDone" value ="luhnCheckDone" size="20"/></td>
            <td><form:errors path="luhnCheckDone" cssClass="error" element="div"/></td>
        </tr>  
        <tr>
            <td><input type="submit" value="<spring:message code="person.edit.page.submit.label"/>"/></td>
        </tr>
</table>

    </form:form>
</div>
</body>
</html>