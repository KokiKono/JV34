<!--
/***************************
 * 作成者　:dyf
 * 作成日　:2016/11/20
 * 内容　　:経理が各月の最低勤怠日数を登録
 * *************************/
 -->
 <%@page import="java.util.ArrayList"%>
 <%@ page import="java.util.Iterator" %>
<%@ page import="beans.ErrorCheck" %>

 <%
 ErrorCheck errorCheck = new ErrorCheck();
 String result = errorCheck.nullReturnEmpty((String) request.getAttribute("mes"));
 String year = errorCheck.nullReturnEmpty((String) request.getAttribute("year"));
 String month = errorCheck.nullReturnEmpty((String) request.getAttribute("month"));
 String days = errorCheck.nullReturnEmpty((String) request.getAttribute("days"));

 //仮CSS
 ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
 for(int i = 0; i < 13; i++){
	 ArrayList<String> row = new ArrayList<String>();
	 row.add("2016/"+i);
	 int day = 20 + i;
	 row.add(String.valueOf(day));
	 list.add(row);
 }

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
	<%
	 //出力用の部署名と機能名を設定
	 String deptName = "経理部";
	 String pageName = "最低勤怠日数管理";
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
		<!--Resultは入力ミスまたデータベースに登録できたかどうかのメッセージを表示する-->
		<%=result %>
		</div>

		<div class="container l1input">
			<h4  class="text-info">登録</h4>

			<form action="./AttendancePutServlet" method="post">
			<div class="row">
				<div class="col-sm-12">
			    <div class="fmtitle">年月</div>
			    <div class="kome">※半角数字</div>
			    </div>
			</div>
			<div class="row rowchild">
				<div class="col-sm-12">
				<input type="text" name="year" value="<%=year %>" placeholder="yyyy" class="form-control input-sm fmyear">
				<div class="datebar">/</div>
				<input type="text" name="month" value="<%=month %>" placeholder="MM" class="form-control input-sm fmmonth">
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
				<div class="fmtitle">最低勤務日数</div>
			    <div class="kome">※半角数字</div>
				</div>
			</div>
			<div class="row rowchild">
				<div class="col-sm-12">
				<input type="text" name="days" value="<%=days %>" class="form-control input-sm">
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
				<input type="submit" name="put" value="登録" class="btn btn-primary btn-block">
				</div>
			</div>

			</form>
		</div>

		<div class="container l1datasection">
			<h4  class="text-info">既存データ</h4>
			<table class="table table-striped table-bordered">
			<thead>
				<tr>
				<th>#</th>
				<th>年月</th>
				<th>最低勤務日数</th>
				</tr>
			</thead>
			<tbody>
				<% int rowCount = 1;
					for(ArrayList<String> row : list){ %>
					<tr>
					<td><%=rowCount %></td>
					<td><%=row.get(0) %></td>
					<td><%=row.get(1) %></td>
					</tr>
				<% rowCount++;
					} %>
			</tbody>
			</table>
		</div>

	</div>
</div>
</body>
</html>