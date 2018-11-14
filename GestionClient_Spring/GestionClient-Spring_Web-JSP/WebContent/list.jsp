<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ page import="com.noelmace.gestionclient.spring.business.dto.ClientTO"  %>	
	<%@ page import="java.util.List" %>

	<table border="1">
		<tr>
			<th>Id</th>
			<th>Nom</th>
			<th>Prenom</th>
		</tr>
		<%--
		List<ClientTO> clientList = (List<ClientTO>) request.getAttribute("clientList");
		--%>
		<c:forEach var="entry" items="${requestScope['clientList']}">
			<tr>
				<td>${entry.getId()}</td>
				<td>${entry.getNom()}</td>
				<td>${entry.getPrenom()}</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>