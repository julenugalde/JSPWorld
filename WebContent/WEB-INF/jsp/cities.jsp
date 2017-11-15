<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="eus.julenugalde.jspworld.model.*" %>
<%@ page import="java.util.Hashtable, java.util.Set, java.util.Iterator, java.util.Locale" %>
<%@ page import="java.text.DecimalFormat, java.text.DecimalFormatSymbols" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of cities</title>
</head>
<body>
<h3>Major cities in <%= ((Country)request.getAttribute("country")).getName() %></h3>
<%
Hashtable<Integer,City> tableCities = 
		(Hashtable<Integer,City>)(request.getAttribute("tableCities"));

if (tableCities != null) {	
	if (tableCities.size() > 0) {
		Set<Integer> keys = tableCities.keySet();
		Iterator<Integer> iterator = keys.iterator();
		DecimalFormat df = new DecimalFormat ("##,##0", DecimalFormatSymbols.getInstance());
		
		out.append("<table>");
		out.append("<tr><th>Name</th>");
		out.append("<th>District</th>");
		out.append("<th>Population</th></tr>");
		
		City city;
		while (iterator.hasNext()){
			city = tableCities.get(iterator.next());
			
			out.append("<tr><td><a href='https://en.wikipedia.org/wiki/"+ city.getName() + "'>" +
				city.getName() +"</a></td>");
			out.append("<td>" + city.getDistrict() + "</td>");
			out.append("<td>" + df.format(city.getPopulation()) + "</td>");
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

</body>
</html>