<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>ProfTeX</title>
	<link rel="stylesheet" type="text/css" href="../css/login.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>

<body>
	<nav>
        <div class="logo">ProfTeX2</div>
        <div class="threebars"><div></div></div>
    </nav>



<!--     <div class="container">
    <div class="heador"><h1>Login</h1></div>
	<form action="LoginServlet">
		<input type="text" name="un" placeholder="j_username" /><br>
		<input type="password" name="pw" placeholder="j_password" />
		<input type="submit" class="submit" value="submit">
	</form> -->
	
	<div class="container">
    <div class="heador"><h1>Login</h1></div>
	<form method="POST" action="j_security_check" >
		<p><input type="text" name="j_username"></p>
		<p><input type="password" name="j_password"></p>
		<p><input type="submit" value="go"></p>
	</form>
	<a href="...">Register</a>
    </div>
    
</body>

</html>