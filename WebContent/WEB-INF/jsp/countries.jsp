<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration,	java.util.Hashtable, java.util.Set, 
				java.util.Iterator, java.util.Locale" %>
<%@ page import="java.text.DecimalFormat, java.text.DecimalFormatSymbols" %>
<%@ page import="eus.julenugalde.jspworld.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Countries</title>
</head>
<body>
<h3>List of countries in <%= (String)request.getAttribute("continent") %></h3>

<form action="Controller" method="post">
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

<%
Hashtable<String,Country> tableCountries = 
		(Hashtable<String,Country>)(request.getAttribute("tableCountries"));

if (tableCountries != null) {	
	Set<String> keys = tableCountries.keySet();
	Iterator<String> iterator = keys.iterator();
	out.append("<table>");
	out.append("<tr><th>Name (Local name)</th>");
	out.append("<th>Code</th>");
	out.append("<th>Region, Continent</th>");
	out.append("<th>Surface area</th>");
	out.append("<th>Population</th>");
	out.append("<th>Gross National Product</th>");
	out.append("<th>Capital, District</th>");
	out.append("<th>Languages</th></tr>");
	
	Country country;
	Language[] languages;
	DecimalFormat df = new DecimalFormat ("##,##0", DecimalFormatSymbols.getInstance());

	while (iterator.hasNext()){
		country = tableCountries.get(iterator.next());
		languages = country.getLanguages();

		out.append("<tr><td><a href='/JSPWorld?countryCode="+ country.getCode() + "'>" + 
				country.getName() + "</a><br />(" + 
				country.getLocalName() + ")</td>");
		out.append("<td>" + country.getCode() + "</td>");
		out.append("<td>" + country.getRegion() + ", " + 
				country.getContinent().getName() + "</td>");
		out.append("<td>" + df.format(country.getSurfaceArea()) + "&nbsp;km<sup>2</sup></td>");
		out.append("<td>" + df.format(country.getPopulation()) + "</td>");
		out.append("<td>" + df.format(country.getGnp()) + "&nbsp;M$</td>");
		out.append("<td>" + country.getCapital().getName() + ", " +  
				country.getCapital().getDistrict() + "</td><td>");
		for (int i=0; i<languages.length; i++) {
			out.append(languages[i].toString() + "<br />");
		}
		out.append("</td></tr>");				

		/*out.write(country.getCode() + " - " + country.getName() + " (" + 
				country.getContinent().getName() + ") <br />");*/
	}
	out.append("</table>");			
	out.append("<p>There are " + tableCountries.size() + " countries in the database</p>");
		
}
else {
	out.append("<p><font color='RED'>Country information unavailable.</font></p>");
}
%>

</body>
</html>