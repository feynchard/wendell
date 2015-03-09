<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create New User</title>
<link rel="stylesheet" href="stylesheet/bootstrap/bootstrap.css">
<link rel="stylesheet" href="stylesheet/logout.css">
</head>
<body>
	<div id="logo">
		<img src="image/LifeNote_logo.png"> <label>Life Note</label>
	</div>
	<form id="logoutForm" method="post" action="/CreateUser.do">
		<div class="form-group">
			<label for="name">name</label> <input type="text"
				class="form-control" id="name" name="name" placeholder="Enter name"
				required value="${param.name}">
		</div>
		<div class="form-group">
			<label for="email">Email address</label> <input type="email"
				class="form-control" id="email" name="email"
				placeholder="Enter email" required value="${param.email}">
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input type="password"
				class="form-control" id="password" name="password"
				placeholder="Password" required>
		</div>
		<div class="form-group">
			<label for="re-password">Confirm password</label> <input
				type="password" class="form-control" id="re-password"
				name="re-password" placeholder="Password" required> <label
				style="color: #b92219;">${requestScope.createUserWarning}</label>
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
	</form>
</body>
</html>