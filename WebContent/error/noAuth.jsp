<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- BootstrapのCSS読み込み -->
	<link href="../css/bootstrap.min.css" rel="stylesheet">
	<!-- jQuery読み込み -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- BootstrapのJS読み込み -->
	<script src="../js/bootstrap.min.js"></script>
	<!-- 調整用css読み込み -->
	<link href="../css/common.css" rel="stylesheet">
	<%
	 //出力用の部署名と機能名を設定
	 String deptName = "各社員";
	 String pageName = "エラー";
	 request.setAttribute("DeptName", deptName);
	 request.setAttribute("PageName", pageName);
	%>
	<title><%=pageName %></title>
</head>
<body>
<div class="wrapper">
	<!-- 共通ヘッダー -->
	<%@ include file="../header.jsp" %>
	<!-- 共通ナビゲーション -->
	<%@ include file="../nav.jsp" %>

	<div class="contents">
		<div class="error">
		<p>権限がありません</p>
		</div>
	</div>
</div>
</body>
</html>