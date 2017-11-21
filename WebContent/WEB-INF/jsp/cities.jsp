<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="eus.julenugalde.jspworld.model.*" %>
<%@ page import="java.util.LinkedHashMap, java.util.Set, java.util.Iterator, java.util.Locale" %>
<%@ page import="java.text.DecimalFormat, java.text.DecimalFormatSymbols" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<title>List of cities</title>
</head>
<body>
<h3>Major cities in <%= ((Country)request.getAttribute("country")).getName() %></h3>
<%
LinkedHashMap<Integer,City> tableCities = 
		(LinkedHashMap<Integer,City>)(request.getAttribute("tableCities"));

if (tableCities != null) {	
	if (tableCities.size() > 0) {
		Set<Integer> keys = tableCities.keySet();
		Iterator<Integer> iterator = keys.iterator();
		DecimalFormat df = new DecimalFormat ("##,##0", DecimalFormatSymbols.getInstance());
		
		out.append("<table>");
		out.append("<tr><th>Id</th>");
		out.append("<th>Name</th>");
		out.append("<th>District</th>");
		out.append("<th>Population</th></tr>");
		
		City city;
		while (iterator.hasNext()){
			city = tableCities.get(iterator.next());
			
			out.append("<tr><td>" + city.getId() + "</td>");
			out.append("<td><a href='https://en.wikipedia.org/wiki/"+ city.getName() + "'>" +
				city.getName() +"</a></td>");
			out.append("<td>" + city.getDistrict() + "</td>");
			out.append("<td class='number'>" + df.format(city.getPopulation()) + "</td>");
			out.append("</td></tr>");				
		}
		out.append("</table>");
		
		int numCities = tableCities.size();
		if ((numCities) > 1) {
			out.append("<p>There are " + tableCities.size() + " cities in the database</p>");
		}
		else {
			out.append("<p>There is one city in the database</p>");
		}			
	}
	else {
		out.append("<p>No cities in the database for this country</p>");
	}
}
else {
	out.append("<p><font color='RED'>Country information unavailable.</font></p>");
}
%>
<br /><br />
<h3>Add a new city</h3>
<form action="JSPWorld?countryCode=<%=request.getParameter("countryCode")%>" method="post">
<table class="newcityform">
	<tr>
		<td><label>City name: </label></td>
		<td><input type="text" name="name" value=""></td>
	</tr><tr>
		<td><label>Country: </label></td>
		<td><input type="text" name="country" readonly="readonly" value=
			"<%= ((Country)request.getAttribute("country")).getName() %>"></td>
	</tr><tr>
		<td><label>District: </label></td>
		<td><input type="text" name="district" value=""></td>
	</tr><tr>
		<td><label>Population: </label></td>
		<td><input type="text" name="population" value=""></td>
	</tr><tr>
		<td><input type="submit" value="Submit"></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>