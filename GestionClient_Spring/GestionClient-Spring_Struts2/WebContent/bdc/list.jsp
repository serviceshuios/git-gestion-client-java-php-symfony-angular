<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="msg.app.title"/> - <s:text name="msg.show.title"/> <s:text name="msg.bdcs"/></title>
<s:include value="/WEB-INF/templating/head.jsp"></s:include>
</head>
<body>
	<s:include value="/WEB-INF/templating/header.jsp"></s:include>
	<div id="content">
		<h1 id="pageTitle"><s:text name="msg.show.title"/> <s:text name="msg.bdcs"/></h1>
		<table class="beanlist">
		<tr>
			<th><s:text name="msg.id"/></th>
			<th><s:text name="msg.bdc.ref"/></th>
			<th><s:text name="msg.bdc.price"/></th>
			<th><s:text name="msg.id"/> <s:text name="msg.ofa"/> <s:text name="msg.client"/></th>
		</tr>
		<s:iterator value="bdcList">
			<tr>
				<td><s:property value="id" /></td>
				<td><s:property value="ref" /></td>
				<td><s:property value="price" /></td>
				<td><s:property value="clientId" /></td>	
			</tr>
		</s:iterator>
	</table>
	</div>
	<s:include value="/WEB-INF/templating/footer.jsp"></s:include>
</body>
</html>