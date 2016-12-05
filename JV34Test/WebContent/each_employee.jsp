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
//ArrayListのHashMap　ここに取得したテーブルがそのまま入る
//ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
//ループ内でレコードごとに取り出すためのHashMap
//HashMap<String, String> map = new HashMap<String,String>();

//PreparedStatement result = db.getPreparedStatement("SELECT * FROM t_product");

StringBuilder sb = new StringBuilder();

//sb.append("SELECT ");
//sb.append("employee_master.employee_master, employee_name, salary, base_salary ");
//sb.append("FROM employee_master INNER JOIN salary_master ON employee_master.employee_master =  salary_master.employee_master");

sb.append("SELECT employee_master, employee_name FROM employee_master");

String strSQL = sb.toString();

System.out.println("SQL文は"+strSQL);

ArrayList<ArrayList<String>> result2 = db.runSelect(strSQL);

double salary = SalaryDetail.SalaryByDays(20,30,0);

//System.out.println("結果は" + result);

System.out.println("給料は"+ salary);
System.out.println("SQL結果は" + result2);
db.closeDB();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>各社員給料一覧</title>
</head>
<body>

<h1>各社員給料一覧</h1><a href="#">ホーム（空リンク）</a>＞各社員給料一覧<hr/>
<%//out.println(result2);%>


<!-- 各社員をテーブル表示 -->
<table border = 1>
<thead><tr><th>社員番号</th><th>社員名</th><th>明細閲覧</th></tr></thead>
<%
int i = 0;//配列内のカラムを回すために必要な数字。1レコード毎に増えていく。
for(ArrayList<String> rec :result2){
	String hoge = "aaaaaa";
%>
<tr>
<% for(String data :rec){%>
<td>
<% out.println(data);%>
</td>
<% } %>
<td>
<form method='get' action ='each_employee.jsp'>
<input type = "hidden" name = "employee_master" value = "<%out.println(result2.get(i).get(0)); %>">
<input type = 'submit' name =<% out.println(result2.get(i).get(0)); %> value = "<% out.println(result2.get(i).get(0));
i++;//←スコープの関係上、ここでしか加算できない？
%>"/>
</form></td></tr>
<%
}%>
</table>

<hr/>

<%

String strEmployeeName = request.getParameter("employee_master");

if(strEmployeeName != null){

	out.println("<h1>給与明細</h1>");

	out.println("社員番号<font color='red'>"+strEmployeeName+"</font>の当月の給与明細を出力します。<br/><br/>");

	//給与明細SQL
	StringBuilder sb2 = new StringBuilder();
	sb2.append("SELECT em.employee_master, em.employee_name, sm.salary, sm.base_salary, lm.licence_name, opm.official_position_name");
	sb2.append(" FROM (");
	sb2.append(" ((");
	sb2.append(" employee_master em");
	sb2.append(" JOIN salary_master sm ON em.employee_master = sm.employee_master");
	sb2.append(" )");
	sb2.append(" JOIN salary_licence_table slt ON sm.payroll_id = slt.payroll_id");
	sb2.append(" )");
	sb2.append(" JOIN licence_master lm ON lm.licence_id = slt.licence_id");
	sb2.append(" )");
	sb2.append(" JOIN official_position_master opm ON opm.official_position_id = em.official_position_id ");
	sb2.append(" WHERE em.employee_master = " +strEmployeeName+ "GROUP BY em.employee_master");

	DBManager db2 = new DBManager("jv34team");

	String strSQL2 = sb2.toString();
	String strSQL3 = ("SELECT lowest_working_days FROM lowest_working_days_master");
	ArrayList<ArrayList<String>> result3 = db2.runSelect(strSQL2);
	ArrayList<ArrayList<String>> result4 = db2.runSelect(strSQL3);

	out.println("当月の最低出勤日数は" + result4.get(0) + "日");

	db2.closeDB();
	if(!result3.equals("")){
%>
		<!-- 給与明細をテーブル表示 -->
		<table border = 1>
		<thead>
		<tr>
		<th>社員番号</th><th>社員名</th><th>給料</th><th>基本給</th><th>所持資格（手当）</th><th>役職（手当）</th><th>当月実働出勤日数</th><th>勤続年数</th>
		</tr>
		</thead>
		<% for(ArrayList<String> rec :result3){ %>
			<tr>
			<% for(String data :rec){ %>
			<td>
			<% out.println(data);%>
			</td>
			<% } %>
			<td>20日</td><td>5年</td>
			</tr>
		<%}%>
		</table>
<%}}%>
<%

//String strTest = result2.get(0).get(0);
//String strTest1 = result2.get(1).get(0);

//out.println(result2.get(1).get(1));
//out.println("ID一つ目は" + strTest + "ID二つ目は" +strTest1);

//int test = Integer.parseInt(strTest);
//String strSalary = result2.get(0).get(0);

//int salary2 = Integer.parseInt(strSalary);

//test = SalaryDetail.SalaryByLicence(test, 0);

//salary2 = test + salary2;

//out.println("資格手当は"+test);
//out.println("給料は"+salary2);

%>

</body>
</html>