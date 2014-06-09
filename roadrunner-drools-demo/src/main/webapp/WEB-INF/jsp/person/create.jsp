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
		<h1><spring:message code="person.create.page.title"/></h1>
		<a href="/" class="hyperline"><spring:message code="person.list.link.label"/></a>
	</div>
<!-- end page-heading -->

    <div class=createpg>
        <form:form action="/person/create" commandName="person" method="POST">
        <table border="0">
            
            <tr>
            
               <td> <form:label path="firstName"><spring:message code="person.label.firstName"/>:</form:label></td>
               <td> <form:input path="firstName" size="20"/></td>
                <td><form:errors path="firstName" cssClass="error" element="div"/></td>
                </tr>
                
            
            <tr>
              <td> <form:label path="lastName"><spring:message code="person.label.lastName"/>:</form:label></td>
                <td><form:input path="lastName" size="20"/></td>
                <td><form:errors path="lastName" cssClass="error" element="div"/></td>
            </tr>
             <tr>
              <td> <form:label path="lastScreen"><spring:message code="person.label.lastScreen"/>:</form:label></td>
                
                
                <td><select id="lastScreen" name="lastScreen">
			   <option value="NONE">--- Select ---</option> 
			   <option value="Extras">Extras</option>
			   <option value="Insurance">Insurance</option>			   
			   <option value="AddCustInfo">AddCustInfo</option>
			   <option value="CreditCheck">CreditCheck</option>
			   <option value="ReviewOrder">ReviewOrder</option>
				</select></td></tr>
            
             <tr>
               <td><form:label path="valdityCheck"><spring:message code="person.label.valdityCheck"/>:</form:label></td>
                <td><select id="valdityCheck" name="valdityCheck">
			   <option value="NONE">--- Select ---</option> 
			   <option value="PropositionCheck">Proposition Check</option>
			   <option value="Extras">Extra Check</option>
			   <option value="Insurance">Insurance</option>
			   
			   <option value="FraudCheckValid">FraudCheckValid</option>
			   <option value="CreditCheckValid">CreditCheckValid</option>
			   <option value="ProofsValid">ProofsValid</option>
			   <option value="GiftCheck">GiftCheck</option>
				</select></td>
            </tr>
          <tr>
              <td> <form:label path="valdityCheckValue"><spring:message code="person.label.valdityCheckValue"/>:</form:label></td>
               <td> <select id="valdityCheckValue" name="valdityCheckValue">
			   <option value="NONE">--- Select ---</option> 
			   <option value="true">true</option>
			   <option value="false">false</option>
				</select></td>
            </tr><tr>
            
            <td>    <input type="submit" value="<spring:message code="person.create.page.submit.label"/>"/></td>
            </tr></table>
        </form:form>
    </div>
</body>
</html>