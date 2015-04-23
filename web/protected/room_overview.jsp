<%@ page import="model.*" %>
<%
  User user = (User) request.getAttribute("user");
%>
<html>
	<head>
		<title>ProfTeX - Raeume</title>
		<link rel="stylesheet" type="text/css" href="../css/room_overview.css">
		<script src="js/jquery.min.js"></script>
		<script src="js/viewroom.js" defer></script>
	</head>
	<body>
		<nav>
			<div class="logo">ProfTeX</div>
			<a href ="user_config.jsp"><span class="threebars"><div></div></span></a>
		</nav>
		
		<br><br><br><br><br>
		<c:forEach items="${user.rooms}" var="room">
			<a class="button" href="doument_create.jsp">${room}</a>
		</c:forEach>
		<div class="button" id="new_room">Erstelle einen neuen Raum!</div>
		<div id="popup">
			<div class="close">schlieﬂen</div> 
			<h3>Erstelle einen neuen Raum</h3>
			<input id="newroomname" type="text" placeholder="Name"/>
			<button id="createroom">Raum Erstellen</button>
		</div>
	</body>
</html>