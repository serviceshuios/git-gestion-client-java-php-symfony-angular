<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<head>
	<jsp:invoke fragment="head" />
</head>
<html>
  <body>
    <div id="header">
      <jsp:invoke fragment="header"/>
    </div>
    <div id="content">
      <jsp:doBody/>
    </div>
    <div id="footer">
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>