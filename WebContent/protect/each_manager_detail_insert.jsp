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
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>勤怠登録画面</title>
</head>
<body>
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
</body>
</html>