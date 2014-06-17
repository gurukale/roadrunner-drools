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
				<p class="nextgen">
					<spring:message code="app.logo.name" />
				</p>
			</div>
			<!-- end logo -->


			<div class="clear"></div>

		</div>
		<!-- End: page-top -->

	</div>
	<!-- End: page-top-outer -->

	<!--  start page-heading -->
	<div id="page-heading">
		<h1>
			<spring:message code="order.create.page.title" />
		</h1>
		<a href="/" class="hyperline"><spring:message
				code="order.list.link.label" /></a>
	</div>
	<!-- end page-heading -->

	<div class=createpg>
		<form:form action="/order/create" commandName="order" method="POST">
			<table border="0">

				<tr>

					<td><form:label path="firstName">
							<spring:message code="order.label.firstName" />:</form:label></td>
					<td><form:input path="firstName" size="20" /></td>
					<td><form:errors path="firstName" cssClass="error"
							element="div" /></td>
				</tr>


				<tr>
					<td><form:label path="lastName">
							<spring:message code="order.label.lastName" />:</form:label></td>
					<td><form:input path="lastName" size="20" /></td>
					<td><form:errors path="lastName" cssClass="error"
							element="div" /></td>
				</tr>
				<tr>
					<td><form:label path="lastScreen">
							<spring:message code="order.label.lastScreen" />:</form:label></td>


					<td><select id="lastScreen" name="lastScreen">
							<option value="NONE">--- Select ---</option>
							<option value="Extras">Extras</option>
							<option value="Insurance">Insurance</option>
							<option value="AddCustInfo">AddCustInfo</option>
							<option value="CreditCheck">CreditCheck</option>
							<option value="ReviewOrder">ReviewOrder</option>
					</select></td>
				</tr>

				<tr>
					<td><form:label path="propositionCheck">
							<spring:message code="order.label.propositionCheck" />:</form:label></td>
					<td><form:checkbox path="propositionCheck"
							name="propositionCheck" value="propositionCheck" size="20" /></td>
					<td><form:errors path="propositionCheck" cssClass="error"
							element="div" /></td>
				</tr>


				<tr>
					<td><form:label path="extraCheck">
							<spring:message code="order.label.extraCheck" />:</form:label></td>
					<td><form:checkbox path="extraCheck" name="extraCheck"
							value="extraCheck" size="20" /></td>
					<td><form:errors path="extraCheck" cssClass="error"
							element="div" /></td>
				</tr>

				<tr>
					<td><form:label path="giftCheck">
							<spring:message code="order.label.giftCheck" />:</form:label></td>
					<td><form:checkbox path="giftCheck" name="giftCheck"
							value="giftCheck" size="20" /></td>
					<td><form:errors path="giftCheck" cssClass="error"
							element="div" /></td>
				</tr>


				<tr>
					<td><form:label path="insurance">
							<spring:message code="order.label.insurance" />:</form:label></td>
					<td><form:checkbox path="insurance" name="insurance"
							value="insurance" size="20" /></td>
					<td><form:errors path="insurance" cssClass="error"
							element="div" /></td>
				</tr>


				<tr>
					<td><form:label path="proofsValid">
							<spring:message code="order.label.proofsValid" />:</form:label></td>
					<td><form:checkbox path="proofsValid" name="proofsValid"
							value="proofsValid" size="20" /></td>
					<td><form:errors path="proofsValid" cssClass="error"
							element="div" /></td>
				</tr>


				<tr>
					<td><form:label path="creditCheckValid">
							<spring:message code="order.label.creditCheckValid" />:</form:label></td>
					<td><form:checkbox path="creditCheckValid"
							name="creditCheckValid" value="creditCheckValid" size="20" /></td>
					<td><form:errors path="creditCheckValid" cssClass="error"
							element="div" /></td>
				</tr>


				<tr>
					<td><form:label path="fraudCheckValid">
							<spring:message code="order.label.fraudCheckValid" />:</form:label></td>
					<td><form:checkbox path="fraudCheckValid"
							name="fraudCheckValid" value="fraudCheckValid" size="20" /></td>
					<td><form:errors path="fraudCheckValid" cssClass="error"
							element="div" /></td>
				</tr>
				<tr>
					<td><input type="submit"
						value="<spring:message code="order.create.page.submit.label"/>" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>