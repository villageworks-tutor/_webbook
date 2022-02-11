<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./parts/layout.jsp">
<jsp:param name="title" value="システム・エラー" />
<jsp:param name="content">
	<jsp:attribute name="value">
		<article id="systemerror">
			<section id="error">
				<h2>システム・エラー</h2>
				<p class="error">${requestScope.message}</p>
				<p>システム管理者に問い合わせてください。</p>
				<dl>
					<dt>問い合わせ先</dt>
					<dd>info@x-city-library.org</dd>
				</dl>
				<p><a href="AuthServlet">ログインページへ</a></p>
			</section>
		</article>
	</jsp:attribute>
</jsp:param>
</jsp:include>