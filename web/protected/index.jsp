<html>


<head>
	<title>ProfTeX</title>
	<link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
    <nav>
        <div class="logo">ProfTeX</div>
        <a href ="logout.jsp" class="logout"><span></span></a>
        <a href ="user_config.jsp"  class="settings"><span></span></span></a>
    </nav>
    <div class="container">
    	<div class="loginfo">
 		<h1>Login Successful!
 		</div>
    </div>
    <%
/*        session = pageContext.getSession();
	   Context context = (Context) host.findChild("/protected/*");
	   return context.getRealm(); */
	   
	   // New location to be redirected
	   String site = new String("../Rooms");
	   response.setStatus(response.SC_MOVED_TEMPORARILY);
	   response.setHeader("Location", site); 
	%>
</body>
</html>
