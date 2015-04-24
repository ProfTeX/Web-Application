<%@ page import="model.*" %>
<%
  User user = (User) request.getAttribute("user");
%>
<html>
	<head>
		<title>ProfTeX - Raeume</title>
		<link rel="stylesheet" type="text/css" href="css/room_overview.css">
		<script src="protected/js/jquery.min.js"></script>
		<script src="js/viewroom.js" defer></script>
	</head>
	<body>
		<nav>
                    <div class="logo">ProfTeX</div>
                    <a class="addRoom" id ="new_room"><span></span></a>
                    <a href ="protected/logout.jsp" class="logout"><span></span></a>
                    <a href ="protected/user_config.jsp" class="settings"><span></span></a>
		</nav>
		
		<br><br><br><br><br>
		<div id="roombox">
		<% for(Room room : user.getRooms()) { %>
			<a class="button" href="protected/document_create.jsp?room=<%=room.getId() %>"><%=room.getName() %></a>
		<% } %>
		</div>
		<div id="popup">
			<div id="close" class="close">schlieﬂen</div> 
			<h3>Erstelle einen neuen Raum</h3>
			<input id="newroomname" type="text" placeholder="Name"/>
			<button id="createroom">Raum Erstellen</button>
		</div>
	</body>
</html>