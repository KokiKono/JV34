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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!--Resultは入力ミスまたデータベースに登録できたかどうかのメッセージを表示する-->
<%=result %>
<p>勤怠日数登録</p>
<form action="./AttendancePutServlet" method="post">
<p><input type="text" name="year" value="<%=year %>">年<input type="text" name="month" value="<%=month %>">月</p>
※半角数字
<p>最低勤務日数<input type="text" name="days" value="<%=days %>"></p>
※半角数字
<p><input type="submit" name="put" value="登録"></p>
</form>

</body>
</html>