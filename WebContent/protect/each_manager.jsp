<%@page import="dtd.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 各部長が部内社員の当日勤怠をつける画面 -->
<%
	//部署ごとの項目を宣言
	String deptName=(String)request.getParameter("deptName");
	//String deptName="経理部";
	ArrayList<Employment> employments=(ArrayList<Employment>) request.getAttribute("empList");
	if(employments==null)employments=new ArrayList<Employment>();
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
	 String hdeptName = "各部長";
	 String hpageName = "勤怠管理";
	 request.setAttribute("DeptName", hdeptName);
	 request.setAttribute("PageName", hpageName);
	%>
	<title><%=hpageName %></title>
</head>
<body>
<div class="wrapper">
	<!-- 共通ヘッダー -->
	<%@ include file="../header.jsp" %>
	<!-- 共通ナビゲーション -->
	<%@ include file="../nav.jsp" %>

	<div class="contents">
<table>
	<thead>
		<tr>
			<th>#</th>
			<th>部署名</th>
			<th>役職名</th>
			<th>社員ID</th>
			<th>社員名</th>
			<th>勤怠</th>
		</tr>
	</thead>
	<tbody>
		<%int count=1; for(Employment employment:employments){ %>
		<tr>
			<td><%=count++ %></td>
			<td><%=employment.department.name %></td>
			<td><%=employment.officailPosition.name %></td>
			<td><%=employment.employmentId %></td>
			<td><a href="EachManagerDetailServlet?depNo=<%=employment.department.departmentId %>&empNo=<%=employment.employmentId%>">勤怠変更</a></td>
			<td><a href="each_manager_detail_insert.jsp?empName=<%=employment.name %>&empNo=<%=employment.employmentId%>">勤怠登録</a></td>
		</tr>
		<%} %>
	</tbody>
</table>
</div>
</div>
</body>
</html>