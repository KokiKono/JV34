<!-- 
/***************************
 * 作成者　:　dyf
 * 作成日　:　2016/11/20
 * 内容　　:　社員登録画面
 * *************************/
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*"%>
<%@ page import="beans.ErrorCheck"%>

<%
	ErrorCheck check = new ErrorCheck();

	//部署
	ArrayList<ArrayList<String>> department = check
			.listIsNullReturnEmpty((ArrayList<ArrayList<String>>) session.getAttribute("department"));

	//役職
	ArrayList<ArrayList<String>> officialPosition = check
			.listIsNullReturnEmpty((ArrayList<ArrayList<String>>) session.getAttribute("officialPosition"));

	//資格情報
	ArrayList<ArrayList<String>> licence = check
			.listIsNullReturnEmpty((ArrayList<ArrayList<String>>) session.getAttribute("licence"));

	String minBirthday = (String) request.getAttribute("minBirthday");
	String maxBirthday = (String) request.getAttribute("maxBirthday");
	
	String joinMaxYear = (String) request.getAttribute("joinMaxYear");
	String joinMinYear = (String) request.getAttribute("joinMinYear");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./EmployeeRegistrationServlet" method="post">

		<h2>従業員登録</h2>
		<p>
			社員番号<input type="text" name="employeeMaster" value="">※半角７桁
		</p>

		<p>
			パスワード<input type="text" name="password" value="">※半角英数6 ~
			8桁まで
		</p>

		<p>
			社員名(カナ)<input type="text" name="kanaName" value="">
		</p>
		<p>
			社員名<input type="text" name="name" value="">
		</p>

		<p>
			生年月日 <input type="date" name="birthday" min="<%=minBirthday %>" max="<%=maxBirthday %>">

		<p>

			性別<input type="radio" name="sex" value="0" checked>男性<input
				type="radio" name="sex" value="1">女性
		</p>
		<p>
			郵便番号<input type="text" name="postalCode1" value="">ー<input
				type="text" name="postalCode2" value="">例）000ー0000(半角)
		</p>
		<p>
			住所<input type="text" name="address" value="">
		</p>
		<p>
			電話番号<input type="text" name="tel" value="">例) 00000000000
		</p>

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

		<p>
			役職 <select name="officialPosition">
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

			</select>
		</p>

		<!-- データベースに登録されているものを -->

		<p>
			課名<select name="subDepartmentID">
				<!--JV34 staffs_input.jsp参考 -->
				<option value=""></option>
			</select>
		</p>
		
		<p>
			入社日<input type="date" name="joined" min="<%=joinMinYear %>" max="<%=joinMaxYear%>">
		</p>
		<p>資格情報</p>
		<p>
			<select name="licence">
				<option value="">ーーーー</option>
				<%
					for (int i = 0; i < licence.size(); i++)
					{
						ArrayList<String> result = new ArrayList<String>();
						result = licence.get(i);
				%>

				<option value="<%=result.get(0)%>"><%=result.get(1)%></option>
				<%
					}
				%>

			</select>
			取得日付　<input type="date" name="getLicenseDate" >
		</p>
		<p>
			<select name="licence">
				<option value="">ーーーー</option>
				<%
					for (int i = 0; i < licence.size(); i++)
					{
						ArrayList<String> result = new ArrayList<String>();
						result = licence.get(i);
				%>

				<option value="<%=result.get(0)%>"><%=result.get(1)%></option>
				<%
					}
				%>

			</select>
			取得日付　<input type="date" name="getLicenseDate" >
		</p>
		<p>
			<select name="licence">
				<option value="">ーーーー</option>
				<%
					for (int i = 0; i < licence.size(); i++)
					{
						ArrayList<String> result = new ArrayList<String>();
						result = licence.get(i);
				%>

				<option value="<%=result.get(0)%>"><%=result.get(1)%></option>
				<%
					}
				%>

			</select>
			取得日付<input type="date" name="getLicenseDate" >
		</p>
		
		<input type="submit" name="insert" value="登録">
	</form>
</body>
</html>