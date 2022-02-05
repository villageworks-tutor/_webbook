<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./parts/layout.jsp">
<jsp:param name="title" value="メインメニュ" />
<jsp:param name="content">
	<jsp:attribute name="value">
		<article id="main">
			<section id="user">
				<h2>利用者管理</h2>
				<ul>
					<li><a href="./user/registView.html">登録</a></li>
					<li><a href="UserServlet?action=search">更新</a></li>
				</ul>
			</section>
			<section id="book">
				<h2>資料管理</h2>
				<ul>
					<li><a href="./snapshot/book/registView.html">登録</a></li>
					<li><a href="./snapshot/book/searchView.html">更新</a></li>
					<li><a href="./snapshot/archive/bookCatalogueView.html">資料目録</a></li>
					<li><a href="./snapshot/archive/bookMasterView.html">資料台帳</a></li>
				</ul>
			</section>
			<section id="counter">
				<h2>図書館業務</h2>
				<ul>
					<li><a href="./snapshot/operation/searchView.html">貸出</a></li>
					<li><a href="./snapshot/operation/searchView.html">返却</a></li>
				</ul>
			</section>
		</article>
	</jsp:attribute>
</jsp:param>
</jsp:include>