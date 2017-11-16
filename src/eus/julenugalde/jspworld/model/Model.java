package eus.julenugalde.jspworld.model;

import java.util.Hashtable;
import java.util.LinkedHashMap;

/** Abstract model for the application. Contains methods to retreive countries and cities data from
 * the source.<br /><br /> 
 * Information on the 'world' database used:
<br /><br /><code>CREATE TABLE city (<br />
&nbsp;  ID int NOT NULL,<br />
&nbsp;  Name char(35) NOT NULL DEFAULT '',<br />
&nbsp;  CountryCode char(3) NOT NULL DEFAULT '',<br />
&nbsp;  District char(20) NOT NULL DEFAULT '',<br />
&nbsp;  Population int NOT NULL DEFAULT '0',<br />
&nbsp;  PRIMARY KEY (ID)<br />
)<br />
<br />
CREATE TABLE country (<br />
&nbsp;  Code char(3) NOT NULL DEFAULT '',<br />
&nbsp;  Name char(52) NOT NULL DEFAULT '',<br />
&nbsp;  Continent varchar(20) CHECK(Continent in ('Asia','Europe','North America','Africa',
&nbsp;  'Oceania','Antarctica','South America')) NOT NULL DEFAULT 'Asia',<br />
&nbsp;  Region char(26) NOT NULL DEFAULT '',<br />
&nbsp;  SurfaceArea decimal(10,2) NOT NULL DEFAULT '0.00',<br />
&nbsp;  IndepYear smallint DEFAULT NULL,<br />
&nbsp;  Population int NOT NULL DEFAULT '0',<br />
&nbsp;  LifeExpectancy decimal(3,1) DEFAULT NULL,<br />
&nbsp;  GNP decimal(10,2) DEFAULT NULL,<br />
&nbsp;  GNPOld decimal(10,2) DEFAULT NULL,<br />
&nbsp;  LocalName char(45) NOT NULL DEFAULT '',<br />
&nbsp;  GovernmentForm char(45) NOT NULL DEFAULT '',<br />
&nbsp;  HeadOfState char(60) DEFAULT NULL,<br />
&nbsp;  Capital int DEFAULT NULL,<br />
&nbsp;  Code2 char(2) NOT NULL DEFAULT '',<br />
&nbsp;  PRIMARY KEY (Code)<br />
)<br />
<br />
CREATE TABLE countrylanguage (<br />
&nbsp;  CountryCode char(3) NOT NULL DEFAULT '',<br />
&nbsp;  Language char(30) NOT NULL DEFAULT '',<br />
&nbsp;  IsOfficial char(1) CHECK(IsOfficial IN ('T','F')) NOT NULL DEFAULT 'F',<br />
&nbsp;  Percentage decimal(4,1) NOT NULL DEFAULT '0.0',<br />
&nbsp;  PRIMARY KEY (CountryCode,Language)<br />
)<br />
</code>
 */
public interface Model {
	/** Establish a connection to the database.
	 * 
	 * @param connectionData Information of the server address and port, user name and password.
	 * @param schemaName Name of the DB schema where the world tables are included.
	 * @return <code>true</code> if the connection was opened correctly, <code>false</code> otherwise.
	 */
	public boolean openDBConnection(ConnectionData connectionData, String schemaName);
	
	/** Retreives the information of all the world countries
	 * 
	 * @return {@link LinkedHashMap} with the information of all the countries. The key used is the 
	 * 3-character country code
	 */
	public LinkedHashMap<String,Country> getCountryList();
	
	/** Retreives the information of the countries from a continent
	 * 
	 * @param continent Element in the {@link Continent} enumeration
	 * @return {@link LinkedHashMap} with the information of the countries in the specified continent.
	 * The key used is the 3-character country code
	 */
	public LinkedHashMap<String,Country> getCountryList(Continent continent);
	
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
	 * @return {@link LinkedHashMap} with the information of all the cities in the country. The key
	 * used is the city identifier (<code>city.getId()</code>).
	 */
	public LinkedHashMap<Integer, City> getCityListByCountry(String countryCode);
	
	/** Retreives the information of all the cities which are part of a country.
	 * 
	 * @param country Instance of {@link Country} with the country's information.
	 * @return {@link LinkedHashMap} with the information of all the cities in the country. The key
	 * used is the city identifier (<code>city.getId()</code>).
	 */
	public LinkedHashMap<Integer, City> getCityListByCountry(Country country);
	
	/** Add new city to the database. The id value in the {@link City} object will not be taken
	 * into account and the database will establish one. It can happen that there are duplicate
	 * values (e.g. cities with the same name)
	 * @param city City to be inserted
	 * @return <code>true</code> if the insertion was successful, <code>false</code> otherwise
	 */
	public boolean addCity(City city);
	
	/** Attempts to close the connection with the database.
	 */
	public void closeDBConnection();
}
