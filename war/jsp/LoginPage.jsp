<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf8">
<title>Hello! Life Note</title>
<link rel="stylesheet" href="stylesheet/bootstrap/bootstrap.css">
<link rel="stylesheet" href="stylesheet/login.css">

</head>
<body>
	<div id="logo">
		<img src="image/LifeNote_logo.png"> <label>Life Note</label>
	</div>
	<form id="loginForm" method="post" action="/wendell">
		<div class="form-group">
			<label for="email">Email address</label> <input type="email"
				class="form-control" id="email" name="email" value="${param.email}"
				placeholder="Enter email" required>
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input type="password"
				class="form-control" id="password" name="password"
				placeholder="Password" required> <label
				style="color: #b92219;">${requestScope.loginWarning}</label>
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
	</form>
	<br>
	<a id="createUserLink" href="/CreateUser.do">建立帳戶</a>
	<div align="center">可用測試email: guest@gmail.com, password: guest
		登入</div>
</body>
</html>