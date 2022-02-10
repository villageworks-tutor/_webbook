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
			<h3>更新完了</h3>
			<p>以下の内容で更新しました。</p>
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
				<tr>
					<th>登録日</th>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${requestScope.member.signupAt}" />
					</td>
				</tr>
				<tr>
					<th>最終更新日</th>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${requestScope.member.updatedAt}" />
					</td>
				</tr>
				<tr>
					<th>抹消日</th>
					<td>-</td>
				</tr>
			</table>
		</section>
		</article>
	</jsp:attribute>
</jsp:param>
</jsp:include>
