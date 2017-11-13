<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prueba JSP</title>
</head>
<body>
<p>Esto es un JSP  de prueba llamado <% out.println(this.getServletName()); %>
 y con la siguiente información: 
<% out.println(getServletInfo()); %>.</p>



</body>
</html>