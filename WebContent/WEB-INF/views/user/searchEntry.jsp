<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../parts/layout.jsp">
<jsp:param name="title" value="利用者管理：検索" />
<jsp:param name="content">
	<jsp:attribute name="value">
	<article id="user">
		<h2>利用者検索</h2>
		<section id="searchEntry">
			<h3>条件入力</h3>
			<p>検索したい利用者の電子メールアドレスを入力して［検索］ボタンを押してください。<br />電子メールアドレスを入力せずに［検索］ボタンを押すと全利用者が対象となります。</p>
			<form>
				<input type="text" name="key" value="${param.key}" />
				<button formaction="UserServlet" formmethod="post" name="action" value="searchExecute">検索</button>
			</form>
		</section>
		<c:if test="${not empty requestScope.message or not empty requestScope.error}">
		<section id="searchResult">
			<h3>検索結果</h3>
			<c:choose>
				<c:when test="${not empty requestScope.error}">
					<p class="error">${requestScope.error}</p>
				</c:when>
				<c:otherwise>
					<p>${requestScope.message}</p>
					<c:if test="${not empty requestScope.members}">
					<table>
						<tr>
							<th>利用者番号</th>
							<th>利用者名</th>
							<th>住所</th>
							<th>電話番号</th>
							<th>電子メールアドレス</th>
							<th>生年月日</th>
							<th>処理選択</th>
						</tr>
						<c:forEach items="${requestScope.members}" var="member">
						<tr>
							<td>${pageScope.member.card}</td>
							<td>${pageScope.member.name}</td>
							<td>〒${pageScope.member.zipcode}<br />${pageScope.member.address}</td>
							<td>${pageScope.member.phone}</td>
							<td>${pageScope.member.email}</td>
							<td>${pageScope.member.birthday}</td>
							<td>
								<form action="UserServlet" method="post">
									<input type="hidden" name="id" value="${pageScope.member.id}" />
									<input type="hidden" name="key" value="${requestScope.key}" />
									<button type="submit" name="action" value="updateEntry">更新</button>
									<button type="submit" name="action" value="deleteConfirm">削除</button>
								</form>
							</td>
						</tr>
						</c:forEach>
					</table>
					</c:if>
				</c:otherwise>
			</c:choose>
		</section>
		</c:if>
	</article>
	</jsp:attribute>
</jsp:param>
</jsp:include>