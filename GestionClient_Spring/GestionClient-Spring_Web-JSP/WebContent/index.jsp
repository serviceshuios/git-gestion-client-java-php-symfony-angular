<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion clients - page d'acceuil</title>
</head>
<body>
	<h1 align="center">Gestion clients</h1>

	<form method="POST" action="./createClient.do">
		<table id="ajouterClient" align="center">
			<tr>
				<th colspan="2" align="center">Ajouter un client</th>
			</tr>
			<tr>
				<td><label>Nom :</label></td>
				<td><input type="text" title="nom" name="nom" id="nom" /></td>
			</tr>
			<tr>
				<td><label>Prenom :</label></td>
				<td><input type="text" title="prenom" name="prenom" id="prenom" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Ajouter"></td>
			</tr>
		</table>
	</form>

	<%-- Long id = (Long) request.getAttribute("clientId");
		if (id != null && id.longValue() > 0) {
	--%>
	<c:choose>
		<c:when test="${not empty requestScope.clientId and requestScope.clientId gt 0}">
			<p>Le client d'id ${requestScope.clientId} a bien été créé !</p>
		</c:when>
		<c:otherwise>
			<p>Aucun client créé pour l'instant !</p>
		</c:otherwise>
	</c:choose>

	<p>
		<a href="./ListClients.do">Afficher la liste des clients.</a>
	</p>
</body>
</html>