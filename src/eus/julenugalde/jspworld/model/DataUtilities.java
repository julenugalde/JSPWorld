package eus.julenugalde.jspworld.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/** Utility class with static methods to visualize or store the data */
public class DataUtilities {
	/** Returns a string with the information of the countries table 
	 * 
	 * @param tableCountries {@link LinkedHashMap} with the list of countries to be displayed
	 * @return {@link String} of <code>tableCountries.size()</code> lines, each of them containing
	 * the <code>toString()</code> output of each country
	 */
	public static String getCountriesString(LinkedHashMap<String, Country> tableCountries) {
		Set<String> keys = tableCountries.keySet();
		StringBuilder sb = new StringBuilder();
		Iterator<String> iterator = keys.iterator();
		Country c;
		//int cuenta = 1;
		while (iterator.hasNext()) {
			//System.out.print("Country #" + (cuenta++) + "... ");
			c = (Country)tableCountries.get(iterator.next());
			//System.out.print(" - " + c.getName() + "...");
			sb.append(c.toString() + System.getProperty("line.separator"));
			//System.out.println("included");
		}
		return sb.toString();
	}
	
	/** Returns a string with the information of the cities table 
	 * 
	 * @param tableCities {@link LinkedHashMap} with the list of cities to be displayed
	 * @return {@link String} of <code>tableCities.size()</code> lines, each of them containing
	 * the <code>toString()</code> output of each city
	 */
	public static String getCitiesString(LinkedHashMap<Integer, City> tableCities) {
		Set<Integer> keys = tableCities.keySet();
		StringBuilder sb = new StringBuilder();
		Iterator<Integer> iterator = keys.iterator();
		City c;
		while (iterator.hasNext()) {
			c = (City)tableCities.get(iterator.next());
			sb.append(c.toString() + System.getProperty("line.separator"));
		}
		return sb.toString();
	}
	
	/** Exports the country data to a CSV file
	 * 
	 * @param tableCountries {@link LinkedHashMap} with the information of the countries to be stored.
	 * @param file File location
	 * @return <code>true</code> if the file was saved correctly, <code>false</code> otherwise.
	 */
	public static boolean exportCountriesToCSV(LinkedHashMap<String, Country> tableCountries, File file) {
		
		try {
			if (!file.exists()) {
				if (!file.createNewFile()) return false;
			}
			FileWriter fr = new FileWriter(file);
			fr.write("Code;Name;Continent;Region;Surface area;Independence year;Population;");
			fr.write("Life expectancy;GNP;Old GNP;Local name;Government form;Head of state;");
			fr.write("2-caracter country coude;Capital;Capital district;Capital population\n");
			Set<String> keys = tableCountries.keySet();
			Iterator<String> iterator = keys.iterator();
			Country c;
			while (iterator.hasNext()) {
				c = (Country)tableCountries.get(iterator.next());
				fr.write(c.getCode() + ";" + c.getName() + ";" + c.getContinent().getName() + 
						 ";" + c.getRegion() + ";" + c.getSurfaceArea() + ";" + 
						 c.getIndependenceYear() + ";" + c.getPopulation() + ";" +
						 c.getLifeExpectancy() + ";" + c.getGnp() + ";" + c.getGnpOld() + 
						 ";" + c.getLocalName() + ";" + c.getGovernmentForm() + ";" + 
						 c.getHeadOfState() + ";" + c.getCode2() + ";" + c.getCapital().getName() +
						 ";" + c.getCapital().getDistrict() + ";" + c.getCapital().getPopulation());
				fr.write("\n");
			}
			
			fr.close();
			return true;
		} catch (IOException ioex) {
			return false;
		}
	}
}
