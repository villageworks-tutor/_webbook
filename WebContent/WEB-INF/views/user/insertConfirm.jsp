<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../parts/layout.jsp">
<jsp:param name="title" value="利用者管理：登録情報更新" />
<jsp:param name="content">
	<jsp:attribute name="value">
		<article id="user">
		<h2>利用者登録情報更新</h2>
		<section id="updateEntry">
			<h3>登録情報確認</h3>
			<p>以下の内容で登録します。<br />登録する場合は［登録する］ボタンをクリックしてください。<br />修正する場合は［登録画面に戻る］ボタンをクリックしてください。</p>
			<form action="UserServlet" method="post">
			<table>
				<tr>
					<th>利用者名</th>
					<td>${sessionScope.member.name}</td>
				</tr>
				<tr>
					<th>住所</th>
					<td>
						〒 ${sessionScope.member.zipcode}<br />
						${sessionScope.member.address}
					</td>
				</tr>
				<tr>
					<th>電話番号</th>
					<td>${sessionScope.member.phone}</td>
				</tr>
				<tr>
					<th>電子メール</th>
					<td>${sessionScope.member.email}</td>
				</tr>
				<tr>
					<th>生年月日</th>
					<td>${sessionScope.member.birthday}</td>
				</tr>
				<tr>
					<th>権限</th>
					<td>
						<c:choose>
						<c:when test="${sessionScope.member.privilege == 1}">
							システム管理者
						</c:when>
						<c:otherwise>
							一般利用者
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" name="action" value="insertExecute">登録する</button>
						<button type="submit" name="action" value="insertEntry">登録画面に戻る</button>
						<input type="hidden" name="id" value="${sesionScope.member.id}" />
					</td>
				</tr>
			</table>
			</form>
		</section>
		</article>
	</jsp:attribute>
</jsp:param>
</jsp:include>
