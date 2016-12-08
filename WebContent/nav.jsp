<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="beans.UserInfoGetter" %>
<%
 //ユーザーIDを取得
 String userId = (String)request.getSession().getAttribute("employeeId");

 //部署IDを取得。
 UserInfoGetter uig = new UserInfoGetter(userId);
 String depNo = uig.getDeptId();
%>

<div class="nav">

		<div class="navsection">
	        <h5 class="text-info">各社員メニュー</h5>
	        <div class="list-group">
	          <a href="http://localhost:8080/JV34_team/index.jsp" class="list-group-item">トップページ</a>
	          <a href="http://localhost:8080/JV34_team/protect/each_employee.jsp" class="list-group-item">給与明細</a>
	        </div>
	    </div>

    	<div class="navsection">
            <h5 class="text-info">人事部メニュー</h5>
            <div class="list-group">
              <a href="#" class="list-group-item">社員管理</a>
              <a href="http://localhost:8080/JV34_team/protect/dept_registration.jsp" class="list-group-item">部署管理</a>
              <a href="#" class="list-group-item">役職管理</a>
              <a href="#" class="list-group-item">資格管理</a>
              <a href="http://localhost:8080/JV34_team/protect/included_salary.jsp" class="list-group-item">付加給与管理</a>
            </div>
         </div>

        <div class="navsection">
            <h5 class="text-info">経理部メニュー</h5>
            <div class="list-group">
              <a href="http://localhost:8080/JV34_team/protect/AttendancePut.jsp" class="list-group-item">最低勤怠日数管理</a>
            </div>
        </div>

        <h5 class="text-info">各部長メニュー</h5>
        <div class="list-group">
          <a href="http://localhost:8080/JV34_team/protect/EachManagerServlet?depNo=<%=depNo %> class="list-group-item">勤怠登録</a>
        </div>

</div>