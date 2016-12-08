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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=deptName %></title>
</head>
<body>
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
</body>
</html>