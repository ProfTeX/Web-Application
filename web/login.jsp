<html>


<head>
	<title>ProfTeX</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
    <nav>
        <div class="logo">ProfTeX</div>
        <div class="threebars"><div></div></div>
    </nav>
    <div class="container">
        <div class="login">
            <h1>Login</h1>
            <form name="loginform">
                <p><input type="text" name="username" placeholder="Username"></p>
                <p><input type="password" name="pword" placeholder="Password"></p>
                <p><input type="button" value="Submit" name="Submit" onclick= "validate()"></p>
            </form>
        </div>
    </div>
<script type = "text/javascript">

var count = 2;
function validate() {
var un = document.loginform.username.value;
var pw = document.loginform.pword.value;
var valid = false;

var unArray = ["Marco", "Penis", "Edwin"];
var pwArray = ["SuperPW", "penis", ""];

for (var i=0; i <unArray.length; i++) {
	if ((un == unArray[i]) && (pw == pwArray[i])) {
		valid = true;
		break;
	}
}

if (valid) {
	alert ("Login successful");
	window.location = "room_overview.jsp";
	return false;
}

var t = " tries";
if (count == 1) {t = " try"}

if (count >= 1) {
	alert ("Invalid username and/or password! ... "+count + t + " left.");
	document.loginform.username.value = "";
	document.loginform.pword.value = "";
	setTimeout("document.loginform.username.focus()", 25);
	setTimeout("document.loginform.username.select()", 25);
	count --;
}

else {
	alert ("Locked out...");
	document.loginform.username.value = "Locked out...";
	document.loginform.pword.value = "";
	document.loginform.username.disabled = true;
	document.loginform.pword.disabled = true;
	return false;
}

}
</script>
</body>
</html>
