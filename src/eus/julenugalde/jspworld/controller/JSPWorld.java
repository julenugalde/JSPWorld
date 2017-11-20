package eus.julenugalde.jspworld.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.api.policy.PolicyResolver.ServerContext;

import eus.julenugalde.jspworld.model.*;

/**
 * Servlet implementation class JSPWorld
 */
@WebServlet(name="/JSPWorld", 
			description = "Controller servlet",
			urlPatterns = { "/JSPWorld" })
public class JSPWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Model model;
    private ConnectionData connectionData;
    private String schemaName;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSPWorld() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	model = new WorldModel();
    	schemaName = "world";
    	connectionData = new ConnectionData("jspworld", "jspworld", "localhost", 3306);
    	//Alternative: database in db4free.net with subset of information
    	//schemaName = "worldprobak";
    	//connectionData = new ConnectionData("jspworld", "jspworld", "db4free.net", 3307);
    	
    	ServletContext servletContext = getServletContext();
    	Enumeration<String> enumeration = servletContext.getInitParameterNames();
    	System.out.println("Servlet initialization parameters:");
    	String name;
    	while (enumeration.hasMoreElements()) {
    		name = enumeration.nextElement();
    		System.out.println("\t- " + name + "='" + 
    					servletContext.getInitParameter(name) + "'");
    	}
    	enumeration = servletContext.getAttributeNames();
    	System.out.println("Servlet initialization attributes:");
    	while (enumeration.hasMoreElements()) {
    		name = enumeration.nextElement();
    		System.out.println("\t- " + name + "='" + 
    					servletContext.getAttribute(name) + "'");
    	}
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd; 		
		System.out.println("request.getRemoteAddr(): " + request.getRemoteAddr());		
		
		String countryCode = request.getParameter("countryCode");
		if (countryCode == null) {	//no country specified. Show country list
			rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/countries.jsp");
			processCountriesInfo(request);
		}
		else {	//show information on a country's major cities
			//check if the country code is valid
			model.openDBConnection(connectionData, schemaName);
			Country country = model.getCountryByCode(countryCode);
			model.closeDBConnection();
			
			if (country == null) {	//country code not valid --> show error page
				//System.out.println("DEBUG: not found");
				request.setAttribute("errorText", 
						"The '" + countryCode + "' code does not correspond to any country.");
				rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			}
			else {	//country code valid --> display cities page
				//System.out.println("DEBUG: new city " + request.getParameter("name") + " found");
				String cName = request.getParameter("name");
				rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cities.jsp");
				if (cName != null) {	//Add new city 
					processAddCity(request, country);
				}
				processCitiesInfo(request, country);				
			}
		}
		rd.forward(request, response);
	}


	private void processCitiesInfo(HttpServletRequest request, Country country) {
		model.openDBConnection(connectionData, schemaName);
		LinkedHashMap<Integer, City> tableCities = model.getCityListByCountry(country);
		request.setAttribute("tableCities", tableCities);
		request.setAttribute("country", country);
		model.closeDBConnection();
	}
	
	private void processAddCity(HttpServletRequest request, Country country) {
		try {
			model.openDBConnection(connectionData, schemaName);
			City city = new City();
			
			//Get country code - char(3)
			String userInput = country.getCode().trim();
			int maxLength = (userInput.length() < 10) ? userInput.length() : 10;
			city.setCountryCode(userInput.substring(0, maxLength));
			
			//Get city name - char(35)
			userInput = request.getParameter("name").trim();
			maxLength = (userInput.length() < 35) ? userInput.length() : 35;
			city.setName(userInput.substring(0, maxLength));
			
			//Get district - char(20)
			userInput = request.getParameter("district").trim();
			maxLength = (userInput.length() < 20) ? userInput.length() : 20;
			city.setDistrict(userInput.substring(0, maxLength));
			
			//Get population
			int population = Integer.parseInt(request.getParameter("population").trim());
			city.setPopulation(population);
			
			model.addCity(city);
			model.closeDBConnection();
		} catch (NumberFormatException e) {
			
		}
		
	}

	private void processCountriesInfo(HttpServletRequest request) {
		String selectedContinent = request.getParameter("continent");
		if (selectedContinent == null) {//Show all continents by default
			selectedContinent = "All";			
		}				
		//System.out.println("Selected continent: " + selectedContinent);
		LinkedHashMap<String, Country> tableCountries;
		model.openDBConnection(connectionData, schemaName);
		if (selectedContinent.equals("All")) {
			tableCountries = model.getCountryList();
		}
		else {
			tableCountries = model.getCountryList(Continent.getByName(selectedContinent));
		}
		request.setAttribute("tableCountries", tableCountries);
		request.setAttribute("continent", selectedContinent); 
		model.closeDBConnection();		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
