package eus.julenugalde.jspworld.model;

//import java.util.LinkedHashMap;

/** Test class for checking the model, using the standard output */
public class TestModel {

	public static void main(String[] args) {
		// Default values for DB connection
		ConnectionData connectionData = new ConnectionData ("", "", "localhost", 3306);
		String schemaName = "world";
		//ConnectionData connectionData = new ConnectionData ("", "", "db4free.net", 3307);
		//String schemaName = "worldprobak";
		
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
			model.openDBConnection(connectionData, schemaName);
						
			//Tests on model methods
			//System.out.println("Empty country:\n" + new Country().toString()); //empty country
			//System.out.println("Empty city:\n" + new City().toString()); //empty city
			
			//LinkedHashMap<String, Country> countries = model.getCountryList();
			/*java.io.File file = new File("c:/temp/countries.csv");
			System.out.println("File name: " + file.getAbsolutePath());
			DataManager.exportCountriesToCSV(countries, file);
			*/
			/*System.out.println("Full list of countries (" + countries.size() + " in total):");
			System.out.println(DataManager.getCountriesString(countries));
			*/
			/*LinkedHashMap<Integer, City> citiesCanada = model.getCityListByCountry("CAN");
			System.out.println("Major cities in Canada:");
			System.out.println(DataUtilities.getCitiesString(citiesCanada));
			*/
			//Country c = countries.get("SMR");	//This one has null GNPold and head of state
			/*Country c = model.getCountryByCode("SMR");
			System.out.println("Info country:\n" + c.toString());
			System.out.println("Major cities in Germany:");
			System.out.println(DataUtilities.getCitiesString(
					model.getCityListByCountry(model.getCountryByCode("DEU"))));
			*/
			/*System.out.println("List of countries in Europe:");
			Hashtable<String, Country> countriesEurope = model.getCountryList(Continent.EUROPE);
			System.out.println(DataManager.getCountriesString(countriesEurope));
			*/
			
			//Insert city in Antarctica
			City newCity = new City(0, "New City of the West", "ATA", "Countless County", 1234567);
			model.addCity(newCity);
								
			model.closeDBConnection();
			
		} catch (Exception e) {
			System.err.println("Error loading data from DB: " + e.getLocalizedMessage());
		}
	}
}
