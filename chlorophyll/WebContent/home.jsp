<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chlorophyll</title>
<link rel = "stylesheet" type = "text/css" href = "chlorophyll.css">
</head>
<body>

<div id = "page_header" class = "text">
	<p>
	Chlorophyll Data: Bay of Bengal
	<p>
</div>
<div id ="app_container" class ="container">
<img alt = "MAP" src = "cl.jpg">
</div>

<div id = "controls" class = "container">
	<form action = "changed" method = "post">
		<select name = "dates">
			<option value = "1">
				January
			</option>
			<option value = "2">
				February
			</option>
			<option value = "3">
				March
			</option>
			<option value = "4">
				April
			</option>
			<option value = "5">
				May
			</option>
			<option value = "6">
				June
			</option>
		</select>
		<input type = "submit" name = "Submit" value = "Change">
	</form>
</div>
</body>
</html>