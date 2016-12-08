<!-- 
/***************************
 * 作成者　:　dyf
 * 作成日　:　2016/11/29
 * 内容　　:　従業員一覧情報、部署、役職、資格ランクの値を取得表示
 * *************************/
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*"%>
<%@ page import="beans.ErrorCheck"%>
<%
	ErrorCheck check = new ErrorCheck();

	//社員一覧情報を格納したlist
	ArrayList<ArrayList<String>> employeeInfo = check.listIsNullReturnEmpty((ArrayList<ArrayList<String>>) session
			.getAttribute("employeeInfo"));

	//部署
	ArrayList<ArrayList<String>> department = check.listIsNullReturnEmpty((ArrayList<ArrayList<String>>) session.getAttribute("department"));

	//役職
	ArrayList<ArrayList<String>> officialPosition = check.listIsNullReturnEmpty((ArrayList<ArrayList<String>>) session
			.getAttribute("officialPosition"));

	//資格ランク
	ArrayList<ArrayList<String>> rank = (ArrayList<ArrayList<String>>) session.getAttribute("rank");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>社員情報検索・一覧</h2>
	<form action="EmployeeRegistrationServlet" method="get">
		<p>
			<input type="submit" name="registration" value="社員登録">
		</p>
	</form>
	<form action="ShowEmployeeServlet" method="post">

		<p>
			部署<select name="department">
				<option value="">ーーーー</option>
				<%
					for (int i = 0; i < department.size(); i++)
					{
						ArrayList<String> result = new ArrayList<String>();
						result = department.get(i);
				%>

				<option value="<%=result.get(0)%>"><%=result.get(1)%></option>
				<%
					}
				%>

			</select>
		</p>
		<p>役職 <select name="officialPosition">
			<option value="">ーーーー</option>
			<%
				for (int i = 0; i < officialPosition.size(); i++)
				{
					ArrayList<String> result = new ArrayList<String>();
					result = officialPosition.get(i);
			%>

			<option value="<%=result.get(0)%>"><%=result.get(1)%></option>
			<%
				}
			%>

		</select></p>
		<p>
			勤務年数 <input type="text" name="minYear" value="">年目 から <input
				type="text" name="maxYear" value="">年目まで
				例)2年から4年まで
		</p>
		<p>
			取得情報 <select name="rank">
				<option value="">ーーーー</option>
				<%
					for (int i = 0; i < rank.size(); i++)
					{
						ArrayList<String> result = new ArrayList<String>();
						result = rank.get(i);
				%>

				<option value="<%=result.get(0)%>"><%=result.get(0)%></option>
				<%
					}
				%>

			</select>ランク以上
		</p>

		<input type="submit" name="registration" value="検索">
	</form>

	<!-- 検索する際に全情報を一覧表示画面 10件ずつページングする　oo件/oo件 -->
	<p>社員番号 氏名 氏名（カナ） 性別 生年月日 入社日 変更</p>
	<form action="EmployeeInfoChangeServlet" method="get">
	<%
		for (int i = 0; i < employeeInfo.size(); i++)
		{
			ArrayList<String> result = new ArrayList<String>();
			result = employeeInfo.get(i);
	%>

		<p>
			<%=result.get(0)%>
			<%=result.get(1)%>
			<%=result.get(2)%>
			<%=result.get(3)%>
			<%=result.get(4)%>
			<%=result.get(5)%>

			<input type="hidden" name="changeEmployeeID" value="<%=result.get(0)%>">
			 <input type="submit" name="change" value="変更">

		</p>
	
	<%
		}
	%>
</form>

<input type="date" name="example" value="">

</body>
</html>