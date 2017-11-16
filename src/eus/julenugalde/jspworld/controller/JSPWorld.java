package eus.julenugalde.jspworld.controller;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd; 		

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
				//System.out.println("DEBUG: country " + country.getName() + " found");
				rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cities.jsp");
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
