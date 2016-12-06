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
DBManager db = new DBManager("jv34team");

//付加給与SQL 出力
ArrayList<ArrayList<String>> result = db.runSelect("SELECT * FROM advantage_salary_master");

//DBクローズ
db.closeDB();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>付加給与</title>
</head>
<body>

<h1>付加給与の登録</h1>
	<!-- 楽にページ移動するための突貫工事フレームもどき。削除していい。 -->
	<a href="included_salary.jsp">付加給与</a>
	<a href="each_employee.jsp">給与明細</a>
	<a href="dept_registration.jsp">部門登録</a><hr/>
	<hr/>

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

</body>
</html>