package eus.julenugalde.jspworld.model;

import java.util.Hashtable;

/** Test class for checking the model, using the standard output */
public class TestModel {

	public static void main(String[] args) {
		// Default values for DB connection
		ConnectionData connectionData = new ConnectionData ("", "", "localhost", 3306);
		
		try {
			// Ask for DB credentials
			/*System.out.print("User name at '" + connectionData.getAddress() + ":" + 
					connectionData.getPort() + "' --> ");
			java.io.BufferedReader br = new java.io.BufferedReader(
					new java.io.InputStreamReader(System.in));
			connectionData.setUser(br.readLine());
			System.out.print("Password --> ");
			connectionData.setPassword(br.readLine());
			*/
			connectionData.setUser("jspworld");
			connectionData.setPassword("jspworld");
			
			//Instance the model and retreive the country data
			Model model = new WorldModel();
			model.openDBConnection(connectionData);
						
			//Tests on model methods
			/*Hashtable<String, Country> countries = model.getCountryList();
			java.io.File file = new File("c:/temp/countries.csv");
			System.out.println("File name: " + file.getAbsolutePath());
			DataManager.exportCountriesToCSV(countries, file);
			*/
			/*System.out.println("Full list of countries:");
			System.out.println(DataManager.getCountriesString(countries));
			*/
			Hashtable<Integer, City> citiesCanada = model.getCityListByCountry("CAN");
			System.out.println("Major cities in Canada:");
			System.out.println(DataManager.getCitiesString(citiesCanada));
			
			/*Country cGermany = countries.get("DEU");
			//Country cGermany = model.getCountryByCode("DEU");
			System.out.println(cGermany.toString());
			System.out.println("Major cities in Germany:");
			System.out.println(DataManager.getCitiesString(
					model.getCityListByCountry(cGermany)));
			*/
			/*System.out.println("List of countries in Europe:");
			Hashtable<String, Country> countriesEurope = model.getCountryList(Continent.EUROPE);
			System.out.println(DataManager.getCountriesString(countriesEurope));
			*/
			
			model.closeDBConnection();
			
		} catch (Exception e) {
			System.err.println("Error loading data from DB: " + e.getLocalizedMessage());
		}
	}
}
