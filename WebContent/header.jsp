<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.UserInfoGetter" %>
<%
 //ユーザーIDを取得
 //String headUserId = (String)session.getAttribute("employeeId");
 String headUserId = "0000001";

 //ユーザー名を取得。
 UserInfoGetter headUig = new UserInfoGetter(headUserId);
 String headUserName = headUig.getUserName();

 String headDeptName = (String)request.getAttribute("DeptName");
 String headPageName = (String)request.getAttribute("PageName");
%>
<div class="header">
	<h3><%=headDeptName %></h3><!-- 部署名 -->
	<h4><%=headPageName %></h4><!-- 機能名 -->

	<a class="btn btn-primary" href="http://localhost:8080/JV34_team/Login.jsp" role="button">ログアウト</a>
	<p>ログインユーザー：<%=headUserName %></p>

</div>