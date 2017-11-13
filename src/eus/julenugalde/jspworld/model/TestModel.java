package eus.julenugalde.jspworld.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class TestModel {

	public static void main(String[] args) {
		String user = "julen";
		String dbServer = "localhost";
		int port = 3306;
		
		try {
			System.out.print("Password: ");
			BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
			String password = br.readLine();
 
		
			Model model = new WorldModel(dbServer, port, user, password);
			model.openDBConnection();
			Hashtable<String, Country> countries = model.getCountryList();
			//System.out.println("Se han recuperado " + countries.size() + " paises");
			File file = new File("c:/temp/test.csv");
			System.out.println("Archivo: " + file.getAbsolutePath());
			WorldModel.exportCountriesToCSV(countries, file);
			model.closeDBConnection();
			
		} catch (IOException ioex) {
			System.err.println("Error loading data from DB: " + ioex.getLocalizedMessage());
		}
	}

}
