<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../parts/layout.jsp">
<jsp:param name="title" value="利用者サービス：検索" />
<jsp:param name="content">
	<jsp:attribute name="value">
	<article id="user">
		<h2>利用者検索</h2>
		<section id="searchEntry">
			<h3>条件入力</h3>
			<p>検索したい利用者の電子メールアドレスを入力して［検索］ボタンを押してください。<br />電子メールアドレスを入力せずに［検索］ボタンを押すと全利用者が対象となります。</p>
			<form>
				<input type="text" name="key" /><button formaction="./searchResultView.html" formmethod="get">検索</button>
			</form>
		</section>
	</article>
	</jsp:attribute>
</jsp:param>
</jsp:include>