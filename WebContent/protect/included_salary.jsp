<!-- /***************************
 * 学籍番号:40292
 * 作成者　:T.Kanz
 * 作成日　:2016/11/24
 * 内容　　:付加給与の登録・変更に関するページ。
 * *************************/
 -->
<%@page import="java.sql.PreparedStatement"%>
<%@page import="beans.DBManager.PreparedStatementByKoki"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.ArrayList" %>
<%@ page import= "java.util.HashMap" %>
<%@ page import= "beans.DBManager" %>
<%
	request.setCharacterEncoding("UTF-8");
%>

<%
DBManager db = new DBManager("jv34_team");

//付加給与SQL 出力
ArrayList<ArrayList<String>> result = db.runSelect("SELECT * FROM advantage_salary_master");

//DBクローズ
db.closeDB();
%>

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
	 String deptName = "人事部";
	 String pageName = "付加給与管理";
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

		<!-- 付加給与表をテーブル表示 -->
		<h1>付加給与表</h1>
		<table border = 1>
			<thead><tr><th>付加給与ID</th><th>年～</th><th>年まで</th><th>付加給与</th><th></th></tr></thead>
			<% for(ArrayList<String> rec :result){ %>
			<tr>
			<% 		for(String data :rec){%>
						<td>
			<% 			out.println(data);%>
						</td>
			<% } %>
				<td>
					<input type = 'submit' name ="" value = "変更">
				</td>
			</tr>
			<% } %>
		</table>

		<hr/>

		<!-- 変更フォーム -->
		<h1>付加給与の変更</h1>

		<form method="get" action="IncludedSalaryServlet" name="test" onSubmit="return check()">
			<input type = "text" size="3" name = "from">年～
			<input type = "text" size="3" name = "to">年
			<input type = "text" size="7" name = "salary">円
			<input type = 'submit' name ="" value = "給与登録">
		</form>
	</div>
</div>
</body>
</html>