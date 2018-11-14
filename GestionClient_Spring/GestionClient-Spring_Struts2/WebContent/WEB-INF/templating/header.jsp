<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="header">

	<div id="title">
		<s:text name="msg.app.title" />
	</div>

	<div id="menu">
		<ul>
			<li><a href="<s:url action='index'/>"><s:text
						name="msg.home" /></a></li>
			<li><a href="#"><s:text name="msg.clients" /></a>
				<ul>
					<li><a
						href="<s:url action='create!input' namespace="/client" />"><s:text
								name="msg.create" /></a></li>
					<li><a href="<s:url action='list' namespace="/client"/>"><s:text
								name="msg.show" /></a></li>
				</ul></li>
			<li><a href="#"><s:text name="msg.bdcs" /></a>
				<ul>
					<li><a href="<s:url action='create!input' namespace="/bdc" />"><s:text
								name="msg.create" /></a></li>
					<li><a href="<s:url action='list' namespace="/bdc" />"><s:text
								name="msg.show" /></a></li>
				</ul></li>

		</ul>
	</div>

	<s:url id="localeEN">
		<s:param name="request_locale">en</s:param>
	</s:url>

	<s:url id="localeFR">
		<s:param name="request_locale">fr</s:param>
	</s:url>

	<div id="langue">
		<s:a href="%{localeEN}"><img alt="en" src="${pageContext.request.contextPath}/resources/img/en.png"></s:a>
		<s:a href="%{localeFR}"><img alt="fr" src="${pageContext.request.contextPath}/resources/img/fr.png"></s:a>
	</div>
</div>
