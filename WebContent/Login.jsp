<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- BootstrapのCSS読み込み -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- jQuery読み込み -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- BootstrapのJS読み込み -->
	<script src="js/bootstrap.min.js"></script>
	<!-- 調整用css読み込み -->
	<link href="css/common.css" rel="stylesheet">
<title>TOP</title>
</head>
<body>
<div class="topwrapper">
	<h1>システムTOP</h1>
	<div class="error toperror">
		エラーメッセージ
	</div>
	<form action="<%=request.getContextPath() %>/LoginServlet">
		<div class="form-group">
		     <input type="text" class="form-control" placeholder="社員番号">
		</div>
		<div class="form-group">
		     <input type="password" class="form-control" placeholder="パスワード">
		</div>
		<div style="">
			<input type="submit" value="login" class="btn btn-primary btn-block">
		</div>
	</form>
</div>
</body>
</html>