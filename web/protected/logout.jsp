<html>
<head>
	<title>ProfTeX</title>
	<link rel="stylesheet" type="text/css" href="../css/login.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>

<body>
	<nav>
        <div class="logo">ProfTeX</div>
        <a href ="logout.jsp" class="logout"><span></span></a>
        <a href ="user_config.jsp"  class="settings"><span></span></span></a>
    </nav>

	<div class="container">
    <div class="header"><h1>Logout</h1></div>
    </div>
	<% session.invalidate(); %>
	<%
	   // New location to be redirected
	   String site = new String("login.jsp");
	   response.setStatus(response.SC_MOVED_TEMPORARILY);
	   response.setHeader("Location", site); 
	%>
</body></html>