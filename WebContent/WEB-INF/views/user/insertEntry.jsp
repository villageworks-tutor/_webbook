<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../parts/layout.jsp">
<jsp:param name="title" value="利用者管理：登録情報更新" />
<jsp:param name="content">
	<jsp:attribute name="value">
		<article id="user">
		<h2>利用者登録情報追加</h2>
		<section id="updateEntry">
			<h3>新規情報入力</h3>
			<p class="note">末尾に「✞」がついている項目は必須入力の項目です。</p>
			<c:if test="${not empty requestScope.errors}">
				<ol class="error">
					<c:forEach items="${requestScope.errors}" var="error">
					<li>${pageScope.error}</li>
					</c:forEach>
				</ol>
			</c:if>
			<form action="UserServlet" method="post">
			<table>
				<tr>
					<th class="required">利用者名</th>
					<td>
						<input type="text" name="name" value="${requestScope.member.name}" />
					</td>
				</tr>
				<tr>
					<th>住所</th>
					<td>
						〒<input type="text" name="zipcode" value="${requestScope.member.zipcode}" /><br />
						<textarea name="address">${requestScope.member.address}</textarea>
					</td>
				</tr>
				<tr>
					<th class="required">電話番号</th>
					<td>
						<input type="text" name="phone" value="${requestScope.member.phone}" />
					</td>
				</tr>
				<tr>
					<th class="required">電子メール</th>
					<td>
						<input type="text" name="email" value="${requestScope.member.email}" />
					</td>
				</tr>
				<tr>
					<th>生年月日</th>
					<td>
						<input type="date" name="birthday" value="${requestScope.member.birthday}" />
					</td>
				</tr>
				<c:if test="${sessionScope.auth.privilege == 1}">
				<tr>
					<th>権限</th>
					<td>
						<c:choose>
						<c:when test="${requestScope.member.privilege == 1}">
						<input type="radio" name="privilege" id="privilege_1" value="1" checked /><label for="privilege_1">システム管理者</label>
						<input type="radio" name="privilege" id="privilege_2" value="2" /><label for="privilege_2">一般利用者</label>
						</c:when>
						<c:otherwise>
						<input type="radio" name="privilege" id="privilege_1" value="1" /><label for="privilege_1">システム管理者</label>
						<input type="radio" name="privilege" id="privilege_2" value="2" checked /><label for="privilege_2">一般利用者</label>
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:if>
				<tr>
					<td colspan="2">
						<button type="submit" name="action" value="insertConfirm">確認画面に進む</button>
					</td>
				</tr>
			</table>
			</form>
		</section>
		</article>
	</jsp:attribute>
</jsp:param>
</jsp:include>
