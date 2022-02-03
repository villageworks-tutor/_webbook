<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="./parts/layout.jsp">
<jsp:param name="title" value="login" />
<jsp:param name="content">
	<jsp:attribute name="value">
	<article id="auth">
		<h2>ユーザ認証</h2>
		<c:if test="${not empty requestScope.message}">
		<p class="message">${requestScope.message}</p>
		</c:if>
		<p>ユーザIDおよびパスワードを入力して［ログイン］ボタンをクリックしてください。</p>
		<form action="AuthServlet" method="post">
			<dl>
				<dt>利用者カード番号</dt>
				<dd>
					<input type="text" name="card" />
				</dd>
			</dl>
			<dl>
				<dt>パスワード</dt>
				<dd>
					<input type="text" name="password" />
				</dd>
			</dl>
			<button name="action" value="login">ログイン</button>
		</form>
	</article>
	</jsp:attribute>
</jsp:param>
</jsp:include>