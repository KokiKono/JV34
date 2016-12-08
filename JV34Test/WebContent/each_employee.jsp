<!-- /***************************
 * 学籍番号:40292
 * 作成者　:T.Kanz
 * 作成日　:2016/11/17
 * 内容　　:各社員の給与明細を表示するページ。
 * *************************/
 -->
<%@page import="beans.SalaryDetail"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="beans.DBManager.PreparedStatementByKoki"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.ArrayList" %>
<%@ page import= "java.util.HashMap" %>
<%@ page import= "beans.DBManager" %>
<%@ page import= "beans.SalaryDetail" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
DBManager db = new DBManager("jv34team");
db.closeDB();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>各社員給料一覧</title>
</head>
<body>

<h1>各社員給料一覧</h1>
<a href="each_employee.jsp">各社員給料一覧</a>
<a href="included_salary.jsp">付加給与</a>
<a href="dept_registration.jsp">部門登録</a>
<hr/>

<%//out.println(result2);%>


<!-- 各社員をテーブル表示 -->
<hr/>
<%
String strEmployeeName = request.getParameter("employee_master");
String strEmployeeID = request.getParameter("");

//if(strEmployeeName != null){

	out.println("<h1>給与明細</h1>");

	out.println("社員番号<font color='red'>"+strEmployeeName+"</font>の当月の給与明細を出力します。<br/><br/>");


	DBManager db2 = new DBManager("jv34team");

	String strSQL2 = "";
	String strSQL3 = ("SELECT lowest_working_days FROM lowest_working_days_master");
//	ArrayList<ArrayList<String>> result3 = db2.runSelect(strSQL2);
	ArrayList<ArrayList<String>> result4 = db2.runSelect(strSQL3);

//	String licence = result3.get(0).get(5);

	out.println("当月の最低出勤日数は" + result4.get(0) + "日<br/><br/>");

	db2.closeDB();
//	if(!result3.equals("")){

/* 		int licenceLV = Integer.parseInt(licence);
		licenceLV = SalaryDetail.SalaryByLicence(licenceLV, 0);
		String licenceSalary = String.valueOf(licenceLV);
		System.out.println(result3);
		ArrayList<String> al = result3.get(0);
		al.set(5, "￥" + licenceSalary);
 */
%>
		<!-- 給与明細をテーブル表示 -->
		<table border = 1>
		<thead>
		<tr>
		<th>社員番号</th><th>社員名</th><th>給料</th><th>基本給</th><th>所持資格</th><th>資格手当</th><th>役職</th><th>当月実働出勤日数</th><th>勤続年数</th>
		</tr>
		</thead>
		<tr><td>0000001</td><td>なまえ</td><td>300000</td><td>250000</td><td>ITパスポート</td><td>10000</td><td>1</td><td>26</td><td>5</td></tr>
<%-- 		<% for(ArrayList<String> rec :result3){ %>
			<tr>
			<% for(String data :rec){ %>
			<td>
			<% out.println(data);%>
			</td>
			<% } %>
			<td>20日</td><td>5年</td>
			</tr>
		<%}%>
 --%>		</table>
<%// } //} %>
</body>
</html>