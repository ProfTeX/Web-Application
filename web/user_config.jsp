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
        	    <p><input type="text" name="email" value="Email@email.de"></p>
        	    <p><input type="password" name="pword" value="test"></p>
        		<p><input type="button" value="Submit" name="Submit" onclick= "validate()"></p>
    		</form>
		</div>
	</div>
    
	<script type = "text/javascript">
	
		function validate() {
			var email = document.config.email.value;
			var pw = document.config.pword.value;
			var valid = false;

			var oldEmail = "Email@email.de";
			var oldPw = "test";

			if (email != oldEmail) {
				valid = true;
			}
			if (pw != oldPw) {
				valid = true;
			}

			if (valid) {
				// SQL-Update Befehl
				alert ("Einstellungen erfolgreich geändert.");
				return true;
			}
			else {
				alert ("Email-Adresse und Passwort wurden nicht geändert.");
			}
		}
	</script>
</body>
</html>