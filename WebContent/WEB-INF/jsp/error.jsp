<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<title>Error</title>
</head>
<body>
<p>The page can't be displayed</p>
<%
String errorText = (String)request.getAttribute("errorText");
if (errorText != null){
	out.write(". Reason:<br /><ul><li>" + errorText + "</li></ul>");
}	
%>
</body>
</html>