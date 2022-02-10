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
			<h3>更新情報入力</h3>
			<p class="note">末尾に「✞」がついている項目は必須入力の項目です。</p>
			<form action="UserServlet" method="post">
			<table>
				<tr>
					<th>利用者番号</th>
					<td>
						${requestScope.member.card}
						<input type="hidden" name="id" value="${requestScope.member.id}" />
						<input type="hidden" name="card" value="${requestScope.member.card}" />
					</td>
				</tr>
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
				<tr>
					<th>登録日</th>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${requestScope.member.signupAt}" />
						<input type="hidden" name="signupAt" value="${requestScope.member.signupAt}" />
					</td>
				</tr>
				<tr>
					<th>最終更新日</th>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${requestScope.member.updatedAt}" />
						<input type="hidden" name="updatedAt" value="${requestScope.member.updatedAt}" />
					</td>
				</tr>
				<tr>
					<th>抹消日</th>
					<td>-</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" name="action" value="updateConfirm">確認画面に進む</button>
					</td>
				</tr>
			</table>
			</form>
		</section>
		</article>
	</jsp:attribute>
</jsp:param>
</jsp:include>
