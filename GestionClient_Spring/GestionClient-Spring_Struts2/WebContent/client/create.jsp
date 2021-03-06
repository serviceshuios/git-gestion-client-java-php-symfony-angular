<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="msg.app.title"/> - <s:text name="msg.create.title"/> <s:text name="msg.client"/></title>
<s:include value="/WEB-INF/templating/head.jsp"></s:include>
</head>
<body>
	<s:include value="/WEB-INF/templating/header.jsp"></s:include>
	<div id="content">
		<h1 id="pageTitle"><s:text name="msg.create.title"/> <s:text name="msg.client"/></h1>
		<s:form action="create" namespace="/client">
			<s:textfield key="msg.client.nom" name="nom" />
			<s:textfield key="msg.client.prenom" name="prenom" />
			<s:submit key="msg.create" name="submitText" />
		</s:form>
	</div>
	<s:include value="/WEB-INF/templating/footer.jsp"></s:include>
</body>
</html>