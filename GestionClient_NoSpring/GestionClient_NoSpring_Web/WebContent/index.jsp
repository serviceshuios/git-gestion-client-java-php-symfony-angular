<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G&eacute;stion Client Web Interface (NoSpring)</title>
</head>
<body>
<h1>INDEX</h1>

<p>Cr&eacute;er un client : </p>
<form action="client/create.do" method="post">
	<input type="number" name="age" placeholder="age">
	<input type="text" name="nom" placeholder="nom">
	<input type="text" name="prenom" placeholder="prenom">
	<input type="submit" value="cr&eacute;er">
</form>
</body>
</html>