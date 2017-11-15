package eus.julenugalde.jspworld.controller;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eus.julenugalde.jspworld.model.*;

/**
 * Servlet implementation class Controller
 */
@WebServlet(name="/Controller", 
			description = "Controller servlet",
			urlPatterns = { "/" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Model model;
    private ConnectionData connectionData;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	model = new WorldModel();
    	connectionData = new ConnectionData("jspworld", "jspworld", "localhost", 3306);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher rd; 		
		
		/*System.out.println("DEBUG: " + (new java.util.Date()).toString());
		java.util.Enumeration<String> attributes = request.getParameterNames();
		String attribute;
		while (attributes.hasMoreElements()) {
			attribute = attributes.nextElement();
			System.out.println("Attribute " + attribute + " = " + request.getParameter(attribute));
		}*/
		

		String countryCode = request.getParameter("countryCode");
		if (countryCode == null) {	//no country specified. Show country list
			rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/countries.jsp");
			processCountriesInfo(request);
		}
		else {	//show country information
			//TODO el problema esta aquí, lanza nullpointerexception al ejecutar getcountrybyname()
			if (model == null) System.out.println("model null");
			if (countryCode.length() != 3) System.out.println("country code incorrecto");
			Country country = model.getCountryByCode(countryCode);
			if (country == null) {	//The country could not be found
				System.out.println("DEBUG: not found");
				request.setAttribute("errorText", "The code does not correspond to any country.");
				rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			}
			else {
				System.out.println("DEBUG: country " + country.getName() + " found");
				rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cities.jsp");
				processCitiesInfo(request, country);
			}
		}
		rd.forward(request, response);
	}


	private void processCitiesInfo(HttpServletRequest request, Country country) {
		model.openDBConnection(connectionData);
		Hashtable<Integer, City> tableCities = model.getCityListByCountry(country);
		request.setAttribute("tableCities", tableCities);
		request.setAttribute("country", country);
		model.closeDBConnection();
	}

	private void processCountriesInfo(HttpServletRequest request) {
		if (request.getParameter("continent") == null) {
			//System.out.println("home page");
		}
		else {
			String selectedContinent = request.getParameter("continent");
			System.out.println("Continente elegido: " + selectedContinent);
			Hashtable<String, Country> tableCountries;
			model.openDBConnection(connectionData);
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
