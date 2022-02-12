<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../parts/layout.jsp">
<jsp:param name="title" value="利用者管理：登録情報更新" />
<jsp:param name="content">
	<jsp:attribute name="value">
		<article id="user">
		<h2>利用者登録情報更新</h2>
		<section id="deleteConfirm">
			<h3>登録情報削除完了</h3>
			<p>以下の内容を削除しました。</p>
			<p>
				<a href="AuthServlet?action=main">メニュ画面に戻る</a>
				<a href="UserServlet?action=search">利用者検索画面に戻る</a>
			</p>
			<table>
				<tr>
					<th>利用者番号</th>
					<td>${requestScope.member.card}</td>
				</tr>
				<tr>
					<th>利用者名</th>
					<td>${requestScope.member.name}</td>
				</tr>
				<tr>
					<th>住所</th>
					<td>
						〒 ${requestScope.member.zipcode}<br />
						${requestScope.member.address}
					</td>
				</tr>
				<tr>
					<th>電話番号</th>
					<td>${requestScope.member.phone}</td>
				</tr>
				<tr>
					<th>電子メール</th>
					<td>${requestScope.member.email}</td>
				</tr>
				<tr>
					<th>生年月日</th>
					<td>${requestScope.member.birthday}</td>
				</tr>
				<tr>
					<th>権限</th>
					<td>
						<c:choose>
						<c:when test="${requestScope.member.privilege == 1}">
							システム管理者
						</c:when>
						<c:otherwise>
							一般利用者
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
		</section>
		</article>
	</jsp:attribute>
</jsp:param>
</jsp:include>
