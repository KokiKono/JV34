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
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>勤怠登録画面</title>
</head>
<body>
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
</body>
</html>