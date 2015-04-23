<html>


<head>
	<title>ProfTeX</title>
	<link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
    <nav>
        <div class="logo">ProfTeX</div>
        <div class="threebars"><div></div></div>
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
