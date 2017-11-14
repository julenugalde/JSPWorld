package eus.julenugalde.jspworld.model;

import java.util.Hashtable;

/** Abstract model for the application. Contains methods to retreive countries and cities data from
 * the source.
 */
public interface Model {
	/** Establish a connection to the database.
	 * 
	 * @param userName User name in the DB
	 * @param password Password for the DB
	 * @param dbServer DB's address
	 * @param dbPort DB port
	 */
	public void openDBConnection(String userName, String password, String dbServer, int dbPort);
	
	/** Retreives the information of all the world countries
	 * 
	 * @return {@link Hashtable} with the information of all the countries. The key used is the 
	 * 3-character country code
	 */
	public Hashtable<String,Country> getCountryList();
	
	/** Retrieves the information of a country identified by the country code.
	 * 
	 * @param code 3-character country code.
	 * @return {@link Country} object with the country's information; <code>null</code> if the code
	 * is wrong or it does not correspond to any country.
	 */
	public Country getCountryByCode(String code);
	
	/** Retreives the information of all the cities which are part of a country.
	 * 
	 * @param countryCode {@link String} with the 3-character country code.
	 * @return {@link Hashtable} with the information of all the cities in the country. The key
	 * used is the city identifier (<code>city.getId()</code>).
	 */
	public Hashtable<Integer, City> getCityListByCountry(String countryCode);
	
	/** Retreives the information of all the cities which are part of a country.
	 * 
	 * @param country Instance of {@link Country} with the country's information.
	 * @return {@link Hashtable} with the information of all the cities in the country. The key
	 * used is the city identifier (<code>city.getId()</code>).
	 */
	public Hashtable<Integer, City> getCityListByCountry(Country country);
	
	/** Attempts to close the connection with the database.
	 */
	public void closeDBConnection();
}
