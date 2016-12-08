<!-- /***************************
 * 学籍番号:40292
 * 作成者　:T.Kanz
 * 作成日　:2016/12/08
 * 内容　　:付加給与を登録・変更するフォーム。
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
String salaryid = request.getParameter("id");
String salaryid2 =(String)request.getAttribute("salaryid");
String from = request.getParameter("from");
String to = request.getParameter("to");
String salary = request.getParameter("salary");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>付加給与登録或いは変更</title>
</head>
<body>

<h1>付加給与の登録或いは変更</h1>
	<hr/>

半角数字のみが入力されている場合、開始年が終了年以上の場合は登録できません。

<!-- 変更フォーム 付加給与IDを取得できたなら変更フォームに -->

<% String err = (String)request.getAttribute("err");
if ( err != null ){
	out.println("<font color='red'>"+err+"</font>");
}
%>


<% if (salaryid != null || salaryid2 != null) { %>
<h1>付加給与の変更</h1>
<form method="get" action="IncludedSalaryServlet" name="test" onSubmit="return check()">
	<input type = "hidden" name = "id" value="<%=salaryid%>">
	<input type = "text" size="3" name = "from" value="<%=from%>">年～
	<input type = "text" size="3" name = "to" value="<%=to%>">年
	<input type = "text" size="7" name = "salary" value="<%=salary %>">円
	<input type = 'submit' name ="" value = "給与変更">
</form>

<% }else{ %>
<!-- 登録フォーム 付加給与IDを取得できなかったら登録フォームに-->
<h1>付加給与の登録</h1>

<form method="get" action="IncludedSalaryNewServlet" name="test" onSubmit="return check()">
	<input type = "text" size="3" name = "from">年～
	<input type = "text" size="3" name = "to">年
	<input type = "text" size="7" name = "salary">円
	<input type = 'submit' name ="" value = "給与登録">
</form>
<% } %>
</body>
</html>