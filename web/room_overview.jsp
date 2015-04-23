<%@ page import="model.*" %>
<%
  User user = (User) request.getAttribute("user");
%>
<html>
	<head>
		<title>ProfTeX - Raeume</title>
		<link rel="stylesheet" type="text/css" href="css/room_overview.css">
	
	</head>
	
	<nav>
		<div class="logo">ProfTeX</div>
		<a href ="user_config.jsp"><span class="threebars"><div></div></span></a>
	</nav>
	
	<br><br><br><br><br>
	<c:forEach items="${user.rooms}" var="room">
		<a class="button" href="doument_create.jsp">${room}</a>
	</c:forEach>
	<div class="button" id="new_room">Erstelle einen neuen Raum!</div>	
</html>