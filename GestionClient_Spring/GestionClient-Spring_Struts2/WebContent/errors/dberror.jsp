<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head><title>Database Error</title></head>
<body>
    <h1>Erreur lors de l'appel à la base de donnée</h1>
    
    <img alt="Erreur DB" src="${pageContext.request.contextPath}/resources/img/db-error.png">
    
    <h2>Messages</h2>

    <s:property value="exception"/>

    <h3>Stack trace:</h3>
    <pre>
        <s:property value="exceptionStack"/>
    </pre>
</body>
</html>