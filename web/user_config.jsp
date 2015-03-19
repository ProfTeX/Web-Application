<html>
	<head>
		<title>Benutzer Einstellungen</title>
		<link rel="stylesheet" type="text/css" href="css/user_config.css">
	</head>

<body>
	<nav>
		<div class="logo">ProfTeX</div>
        <div class="threebars"><div></div></div>
	</nav>

	<div class="container">
		<div class="user_config">
        	<h1>Benutzer Einstellungen</h1>
       		<form name="config">
        	    <p>Email Adresse <input type="text" name="email" value="Email@email.de"></p>
        	    <p>Altes Passwort <input type="password" name="altespword" value="test"></p>
        	    <p>Neues Passwort <input type="password" name="neuespword" value=""></p>
        	    <p>Neues Passwort <input type="password" name="nneuespword" value=""></p>
        		<p><input type="button" value="Submit" name="Submit" onclick= "validate()"></p>
    		</form>
		</div>
	</div>
    
	<script type = "text/javascript">
	
		function validate() {
			var email = document.config.email.value;
			var altespw = document.config.altespword.value;
			var neuespw = document.config.neuespword.value;
			var nneuespw = document.config.nneuespword.value;

			var oldEmail = "Email@email.de";
			var oldPw = "test"

			if (oldPw != altespw){
				alert ("Passwort stimmt nicht.");
				return false;
			}
			if ((email == oldEmail) && (neuespw == altespw)) {
				alert ("Keine Änderungen festgestellt.");
				return false;
			}
			if (neuespw != nneuespw){
				alert ("Die Passwörter stimmen nicht über ein.");
				return false;
			}
			
			// SQL-Update Befehl
			alert ("Einstellungen erfolgreich geändert.");
			return true;
		}
		
	</script>
</body>
</html>