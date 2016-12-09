<%@page import="dtd.EachManagerDetail.DayBox"%>
<%@page import="dtd.EachManagerDetail"%>
<%@page import="dtd.EachManagerDetail.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	EachManagerDetail detail=(EachManagerDetail)request.getAttribute("detail");
	if(detail==null)detail=new EachManagerDetail();

	String msg=(String)request.getAttribute("msg");
	if(msg==null)msg=new String();
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
<%=msg %>
<form action="EachManagerDetailServlet" method="post">
	<input type="hidden" value="<%=detail.empNo%>" name="empNo">
	<table>
		<tr>
			<th>従業員ID</th>
			<td><%=detail.empNo %></td>
		</tr>
		<tr>
			<th>従業員名</th>
			<td><%=detail.empName %></td>
		</tr>
		<tr>
			<th>日にち</th>
			<th>勤怠</th>
		</tr>
		<%for(DayBox box:detail.dayBoxs){ %>
		<tr>
			<td><input type="hidden" name="attend" value="<%=box.date.outSQLDate()%>"><%=box.date.outOfJP() %></td>
			<td>
				<select name="attendFlg">
				<%for(String row:box.getSelectOption()){ %>
					<%=row %>
				<%} %>
				</select>
			</td>
		</tr>
		<%} %>
	</table>
	<input type="submit" value="更新">
</form>
<a href="EachManagerServlet">戻る</a>
</div>
</div>
</body>
</html>