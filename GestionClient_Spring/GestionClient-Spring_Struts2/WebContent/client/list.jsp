<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="msg.app.title"/> - <s:text name="msg.show.title"/> <s:text name="msg.clients"/></title>
<s:include value="/WEB-INF/templating/head.jsp"></s:include>
</head>
<body>
	<s:include value="/WEB-INF/templating/header.jsp"></s:include>
	<div id="content">
		<h1 id="pageTitle"><s:text name="msg.show.title"/> <s:text name="msg.clients"/></h1>
		<table class="beanlist">
		<tr>
			<th><s:text name="msg.id"/></th>
			<th><s:text name="msg.client.nom"/></th>
			<th><s:text name="msg.client.prenom"/></th>
		</tr>
		<s:iterator value="clientList">
			<tr>
				<td><s:property value="id" /></td>
				<td><s:property value="nom" /></td>
				<td><s:property value="prenom" /></td>
			</tr>
		</s:iterator>
	</table>
	</div>
	<s:include value="/WEB-INF/templating/footer.jsp"></s:include>
</body>
</html>