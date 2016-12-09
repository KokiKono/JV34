<%@page import="beans.CalendarByKoki"%>
<%@page import="dtd.EachManagerDetail.DayBox"%>
<%@page import="dtd.EachManagerDetail"%>
<%@page import="dtd.EachManagerDetail.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	if(msg==null)msg=new String();

	String empNo=(String)request.getParameter("empNo");
	String empName=(String)request.getParameter("empName");

	//当月勤怠の生成
	CalendarByKoki now=new CalendarByKoki();

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
	<input type="hidden" value="<%=empNo%>" name="empNo">
	<table>
		<tr>
			<th>従業員ID</th>
			<td><%=empNo %></td>
		</tr>
		<tr>
			<th>従業員名</th>
			<td><%=empName %></td>
		</tr>
		<tr>
			<th>日にち</th>
			<th>勤怠</th>
		</tr>
		<%for(int day=1;day<=now.getMaxDay();day++){ %>
		<tr>
			<td><input type="hidden" name="attend" value="<%=now.getYear()+"-"+now.getMonth()+"-"+now.getDay()%>"><%=now.getYear()+"年"+now.getMonth()+"月"+now.getDay()+"日"%></td>
			<td>
				<select name="attendFlg">
					<%for(String row:Attend.getSelectOption(Attend.Attendance.code)){ %>
					<%=row %>
				<%} %>
				</select>
			</td>
		</tr>
		<%} %>
	</table>
	<input type="submit" value="登録">
</form>
<a href="EachManagerInsertServlet">戻る</a>
</div>
</div>
</body>
</html>