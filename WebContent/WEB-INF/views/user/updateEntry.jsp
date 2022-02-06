<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../parts/layout.jsp">
<jsp:param name="title" value="利用者管理：登録情報更新" />
<jsp:param name="content">
	<jsp:attribute name="value">
		<article id="user">
		<h2>利用者登録情報更新</h2>
		<section id="updateEntry">
			<h3>更新情報入力</h3>
			<form action="UserServlet" method="post">
			<table>
				<tr>
					<th>利用者番号</th>
					<td>
						12056692
						<input type="hidden" name="id" value="90" />
					</td>
				</tr>
				<tr>
					<th>利用者名</th>
					<td>
						<input type="text" name="name" value="宮沢 麻理子" />
					</td>
				</tr>
				<tr>
					<th>住所</th>
					<td>
						〒<input type="text" name="zipcode" value="231-0862" /><br />
						<textarea name="address">神奈川県横浜市中区山手町2-5-9山手町タワー418</textarea>
					</td>
				</tr>
				<tr>
					<th>電話番号</th>
					<td>
						<input type="text" name="phone" value="080-6431-9202" />
					</td>
				</tr>
				<tr>
					<th>生年月日</th>
					<td>
						<input type="date" name="birthday" value="1978-07-24" />
					</td>
				</tr>
				<tr>
					<th>電子メール</th>
					<td>
						<input type="text" name="email" value="miyazawamariko@xtbysvxh.an" />
					</td>
				</tr>
				<tr>
					<th>権限</th>
					<td>
						<input type="radio" name="privilege" id="privilege_1" value="1" /><label for="privilege_1">システム管理者</label>
						<input type="radio" name="privilege" id="privilege_2" value="2" checked /><label for="privilege_2">一般利用者</label>
					</td>
				</tr>
				<tr>
					<th>登録日</th>
					<td>2010-04-28</td>
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
