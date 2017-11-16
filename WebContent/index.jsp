<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<title>JSP World</title>
</head>
<body>
<h3>Select a continent</h3>
<form action="JSPWorld" method="post">
	<label>Continent: </label>
	<select name="continent">
		<option value="All">All continents</option>
		<option value="Europe">Europe</option>
		<option value="Asia">Asia</option>
		<option value="Africa">Africa</option>
		<option value="North America">North America</option>
		<option value="South America">South America</option>
		<option value="Oceania">Oceania</option>
		<option value="Antarctica">Antarctica</option>
	</select>
	<input type="submit" value="Show countries" />
</form>
</body>
</html>