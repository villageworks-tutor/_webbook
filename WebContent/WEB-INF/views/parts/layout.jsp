<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>${param.title} - 図書管理システム</title>
	<link rel="stylesheet" href="/webbook/assets/css/style.css" />
</head>
<body>
	<!-- ヘッダ領域 -->
	<jsp:include page="./header.jsp" />
	<!-- メインコンテンツ領域 -->
	<main>
		${param.content}
	</main>
	<!-- フッタ領域 -->
	<jsp:include page="./footer.jsp" />
</body>
</html>