<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<header>
		<h1>某図書館　図書管理システム</h1>
		<c:if test="${not empty sessionScope.auth}">
		<div><a href="AuthServlet?action=logout">ログアウトする</a></div>
		</c:if>
	</header>
