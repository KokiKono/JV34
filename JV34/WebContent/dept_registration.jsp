<!-- /***************************
 * 学籍番号:40292
 * 作成者　:T.Kanz
 * 作成日　:2016/11/22
 * 内容　　:部門登録ならびに表示するページ。
 * *************************/
 -->
<%@page import="java.sql.PreparedStatement"%>
<%@page import="beans.DBManager.PreparedStatementByKoki"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "java.util.ArrayList" %>
<%@ page import= "java.util.HashMap" %>
<%@ page import= "beans.DBManager" %>
<%@ page import= "beans.SalaryDetail" %>
<%
	request.setCharacterEncoding("UTF-8");
%>

<%
DBManager db = new DBManager("jv34team");

//部門SQL 出力
ArrayList<ArrayList<String>> result = db.runSelect("SELECT * FROM department_master");

//課SQL 出力
ArrayList<ArrayList<String>> result2 = db.runSelect("SELECT sub_department_id, sub_department_name, department_name FROM sub_department_master sbd JOIN department_master dm ON sbd.department_id = dm.department_id");

//DBクローズ
db.closeDB();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部門登録</title>
</head>
<body>

<h1>部門登録</h1>
	<!-- 楽にページ移動するための突貫工事フレームもどき。削除していい。 -->
	<a href="dept_registration.jsp">部門登録</a>
	<a href="each_employee.jsp">給与明細</a>
	<a href="included_salary.jsp">付加給与</a><hr/>

<h1>部署登録</h1>
<ul>
	<li>既に存在する部署及び課は登録できません。</li>
	<li>文字が入力されていない場合登録できません。</li>
	<li>記号を含めた場合は登録できません。</li>
	<li>同時に登録することはできません。</li>
</ul>

<!-- エラー文を取得並びに出力 -->
<% String err = (String)request.getAttribute("err");
if ( err != null ){
	out.println("<font color='red'>"+err+"</font>");
}
%>

<!-- 部・課の登録フォーム -->
<form method="get" action="DeptRegistrationServlet" name="test" onSubmit="return check()">
	<input type = "text" name = "deptname">部
	<input type = 'submit' name ="" value = "部署登録">
</form><br/><br/>
<form method="get" action="DeptRegistrationServlet" name="test" onSubmit="return check()">
	<input type = "text" name = "deptname">課
	<input type = 'submit' name ="" value = "課登録">
</form>



<!-- 各部門をテーブル表示 -->
<h1>部署リスト</h1>
<table border = 1>
<thead><tr><th>部門番号</th><th>部門名</th></tr></thead>
<%
int i = 0;//配列内のカラムを回すために必要な数字。1レコード毎に増えていく。
for(ArrayList<String> rec :result){
%>
<tr>
<% for(String data :rec){%>
<td>
<% out.println(data);%>
</td>
<% } %>
</tr>
<% } %>
</table>

<!-- 各課をテーブル表示 -->
<h1>課リスト</h1>
<table border = 1>
<thead><tr><th>課番号</th><th>課名</th><th>部門名</th></tr></thead>
<%	int i2 = 0;//配列内のカラムを回すために必要な数字。1レコード毎に増えていく。
	for(ArrayList<String> rec :result2){ %>
<tr>
<% for (String data :rec) {%>
<td>
<% out.println(data);
} } %>
</table>
<hr/>
</body>
</html>