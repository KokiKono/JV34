<!--
/***************************
 * 作成者　:dyf
 * 作成日　:2016/11/20
 * 内容　　:経理が各月の最低勤怠日数を登録
 * *************************/
 -->
 <%@ page import="beans.ErrorCheck" %>

 <%
 ErrorCheck errorCheck = new ErrorCheck();
 String result = errorCheck.nullReturnEmpty((String) request.getAttribute("mes"));
 String year = errorCheck.nullReturnEmpty((String) request.getAttribute("year"));
 String month = errorCheck.nullReturnEmpty((String) request.getAttribute("month"));
 String days = errorCheck.nullReturnEmpty((String) request.getAttribute("days"));
%>
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
<title>Insert title here</title>
</head>
<body>
<div class="wrapper">
	<!-- 共通ヘッダー -->
	<div class="header">
		<h3>経理部</h3><!-- 部署名 -->
		<h4>勤怠日数登録</h4><!-- 機能名 -->
	</div>
	<!-- 共通ナビゲーション -->
	<%@ include file="../nav.jsp" %>

	<div class="contents">
		<!--Resultは入力ミスまたデータベースに登録できたかどうかのメッセージを表示する-->
		<%=result %>

		<div class="">
			<form action="./AttendancePutServlet" method="post">
			<p><input type="text" name="year" value="<%=year %>">年<input type="text" name="month" value="<%=month %>">月</p>
			※半角数字
			<p>最低勤務日数<input type="text" name="days" value="<%=days %>"></p>
			※半角数字
			<p><input type="submit" name="put" value="登録"></p>
			</form>
		</div>

		<div class="datasection">



		</div>

	</div>
</div>
</body>
</html>