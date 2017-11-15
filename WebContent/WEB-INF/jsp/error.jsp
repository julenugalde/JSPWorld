<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>
<p>The page can't be displayed</p>
<%
String errorText = (String)request.getAttribute("errorText");
if (errorText != null){
	out.write("<p>" + errorText + "</p>");
}
	
%>

</body>
</html>