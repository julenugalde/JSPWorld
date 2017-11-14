package eus.julenugalde.jspworld.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

/** Test class for checking the model, using the standard output */
public class TestModel {

	public static void main(String[] args) {
		// Default values for DB connection
		String user = "";
		String password = "";
		String dbServer = "localhost";
		int dbPort = 3306;
		
		try {
			// Ask for DB credentials
			System.out.print("User name at '" + dbServer + ":" + dbPort + "' --> ");
			BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
			user = br.readLine();
			System.out.print("Password --> ");
			password = br.readLine();
					
			//Instance the model and retreive the country data
			Model model = new WorldModel();
			model.openDBConnection(user, password, dbServer, dbPort);
			Hashtable<String, Country> countries = model.getCountryList();
			//Hashtable<Integer, City> citiesCanada = model.getCityListByCountry("CAN");
			Country cGermany = countries.get("DEU");
			//Country cGermany = model.getCountryByCode("DEU");
			model.closeDBConnection();
			
			//Tests on model methods
			/*java.io.File file = new File("c:/temp/countries.csv");
			System.out.println("File name: " + file.getAbsolutePath());
			DataManager.exportCountriesToCSV(countries, file);
			*/
			/*System.out.println("Full list of countries:");
			System.out.println(DataManager.getCountriesString(countries));
			*/
			/*System.out.println("Major cities in Canada:");
			System.out.println(DataManager.getCitiesString(citiesCanada));
			*/
			System.out.println(cGermany.toString());
			System.out.println("Major cities in Germany:");
			System.out.println(DataManager.getCitiesString(
					model.getCityListByCountry(cGermany)));
			
		} catch (IOException ioex) {
			System.err.println("Error loading data from DB: " + ioex.getLocalizedMessage());
		}
	}
}
