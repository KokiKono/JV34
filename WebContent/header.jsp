<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.UserInfoGetter" %>
<%
 //ユーザーIDを取得
 String userId = (String)request.getSession().getAttribute("employeeId");

 //ユーザー名を取得。
 UserInfoGetter uig = new UserInfoGetter(userId);
 String strUserName = uig.getUserName();

 String strDeptName = (String)request.getAttribute("DeptName");
 String strPageName = (String)request.getAttribute("PageName");
%>
<div class="header">
	<h3><%=strDeptName %></h3><!-- 部署名 -->
	<h4><%=strPageName %></h4><!-- 機能名 -->

	<p>ログインユーザー：<%=strUserName %></p>
</div>