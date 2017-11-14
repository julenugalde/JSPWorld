package eus.julenugalde.jspworld.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.commons.dbcp.BasicDataSource;

//TODO Improve this class reusing the code among the methods

/** Implementation of the model using the 'world' database */
public class WorldModel implements Model {
	BasicDataSource dataSource;
	Connection con;
	Hashtable<String, Country> tableCountries;
	
	public WorldModel() {
		tableCountries = new Hashtable<String, Country>();
		dataSource = null;
		con = null;
	}	

	@Override
	public void openDBConnection(String userName, String password, String dbServer, int dbPort) {
		try {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://" + dbServer + 
					":" + String.valueOf(dbPort) + "/world" + 
					"?autoReconnect=true&useSSL=false");
			dataSource.setUsername(userName);
			dataSource.setPassword(password);
			con = dataSource.getConnection();
			
		} catch (SQLException e) {
			System.err.println("SQL exception: " + e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing the DB connection: " + e.getSQLState());
			}
		}
	}

	@Override
	public Hashtable<String, Country> getCountryList() {
		try {
			con = dataSource.getConnection();
			PreparedStatement stmCountries = con.prepareStatement("SELECT * FROM country");
			PreparedStatement stmCapital = null;
			PreparedStatement stmLanguages = null;
			ResultSet rsCountries = stmCountries.executeQuery();
			ResultSet rsCapital = null;
			ResultSet rsLanguages = null;
			
			//DEBUG - View metadata
			/*java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("Type\tSize\tName");
			System.out.println("----------------------------------------------");
			for (int i=1; i<=rsmd.getColumnCount(); i++) {
				System.out.println(rsmd.getColumnTypeName(i) + "\t" + 
						rsmd.getColumnDisplaySize(i) + "\t" + rsmd.getColumnName(i));				
			}*/
			
			tableCountries.clear();
			
			Country country;
			Language[] temp;
			ArrayList<Language> alLanguages = new ArrayList<Language>();
			boolean official;
			while (rsCountries.next()) {
				country = new Country();
				country.setCode(rsCountries.getString("Code"));
				
				//First the capital city is searched in the database
				stmCapital = con.prepareStatement(
						"SELECT * FROM city WHERE ID='" + rsCountries.getInt("Capital") + "'");
				rsCapital = stmCapital.executeQuery();
				if (rsCapital.next()) {
					country.setCapital(new City(rsCapital.getInt("ID"),	//id
							 rsCapital.getString("Name"),	//name
							 rsCapital.getString("CountryCode"),	//country code
							 rsCapital.getString("District"),	//district
							 rsCapital.getInt("Population")));	//population);
				}
				
				//The languages information is retreived from the database
				alLanguages.clear();
				stmLanguages = con.prepareStatement(
						"SELECT * FROM countrylanguage WHERE CountryCode='" + 
						country.getCode() + "'");
				rsLanguages = stmLanguages.executeQuery();
				while (rsLanguages.next()) {
					if (rsLanguages.getString("IsOfficial").equals("T"))
						official = true;
					else	//equals("F")
						official = false;
					alLanguages.add(new Language(
							rsLanguages.getString("CountryCode"), //country code
							rsLanguages.getString("Language"), //name
							official,
							rsLanguages.getFloat("Percentage")));	//percentage
				}			
				temp = new Language[alLanguages.size()]; 
				temp = (Language[])alLanguages.toArray(temp);
				if (temp != null) {
					country.setLanguages(temp);
				}
				
				// The rest of the fields are filled
				country.setName(rsCountries.getString("Name"));
				country.setContinent(Continent.getByName(rsCountries.getString("Continent")));
				country.setRegion(rsCountries.getString("Region"));
				country.setSurfaceArea(rsCountries.getFloat("SurfaceArea"));
				country.setIndependenceYear(rsCountries.getInt("IndepYear"));
				country.setPopulation(rsCountries.getInt("Population"));
				country.setLifeExpectancy(rsCountries.getFloat("LifeExpectancy"));
				country.setGnp(rsCountries.getFloat("GNP"));
				country.setGnpOld(rsCountries.getFloat("GNPOld"));
				country.setLocalName(rsCountries.getString("LocalName"));
				country.setGovernmentForm(rsCountries.getString("GovernmentForm"));
				country.setHeadOfState(rsCountries.getString("HeadOfState"));
				country.setCode2(rsCountries.getString("Code2"));
		
				//Insert the new country to the hash table
				tableCountries.put(country.getCode(), country);
			}
			
			stmCountries.close();
			if (stmCapital != null) {
				stmCapital.close();
			}
			return tableCountries;
			
		} catch (SQLException e) {
			System.err.println("SQL exception: " + e.getMessage());
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing the DB connection: " + e.getSQLState());
				return null;
			}
		}
			
	}

	@Override
	public void closeDBConnection() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.err.println("Error closing the DB connection: " + e.getSQLState());
		}
	}

	@Override
	public Hashtable<Integer, City> getCityListByCountry(String countryCode) {
		if (countryCode == null)  return null;
		if (countryCode.length() != 3) return null;
		
		try {
			Hashtable<Integer, City> tableCities = new Hashtable<Integer, City>();
			
			con = dataSource.getConnection();
			PreparedStatement stmCities = con.prepareStatement(
					"SELECT * FROM city WHERE CountryCode='" + countryCode + "'");
			ResultSet rsCities = stmCities.executeQuery();
			
			City city;
			while (rsCities.next()) {
				city = new City();
				city.setId(rsCities.getInt("ID"));
				city.setName(rsCities.getString("Name"));
				city.setCountryCode(rsCities.getString("CountryCode"));
				city.setDistrict(rsCities.getString("District"));
				city.setPopulation(rsCities.getInt("Population"));
				tableCities.put(city.getId(), city);
			}			
			stmCities.close();
			
			return tableCities;
			
		} catch (SQLException e) {
			System.err.println("SQL exception: " + e.getMessage());
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing the DB connection: " + e.getSQLState());
				return null;
			}
		}
	}

	@Override
	public Hashtable<Integer, City> getCityListByCountry(Country country) {
		if (country == null) return null;
		if (country.getCode() == null) return null;
		if (country.getCode().equals("")) return null;
		
		return getCityListByCountry(country.getCode());
	}

	@Override
	public Country getCountryByCode(String code) {
		if (code == null) return null;
		if (code.length() != 3) return null;
		
		try {
			con = dataSource.getConnection();
			PreparedStatement stmCountries = con.prepareStatement(
					"SELECT * FROM country WHERE Code='" + code + "'");
			PreparedStatement stmCapital = null;
			ResultSet rsCountries = stmCountries.executeQuery();
			ResultSet rsCapital = null;
			
			Country country = new Country();
			City cCapital;
			if (!rsCountries.next()) return null;
			
			//First the capital city is searched in the database
			stmCapital = con.prepareStatement(
					"SELECT * FROM city WHERE ID='" + rsCountries.getInt("Capital") + "'");
			rsCapital = stmCapital.executeQuery();
			if (rsCapital.next()) {
				cCapital = new City(rsCapital.getInt("ID"),	//id
						 rsCapital.getString("Name"),	//name
						 rsCapital.getString("CountryCode"),	//country code
						 rsCapital.getString("District"),	//district
						 rsCapital.getInt("Population"));	//population);
				country.setCapital(cCapital);
			}
			
			// The rest of the fields are filled
			country.setCode(rsCountries.getString("Code"));
			country.setName(rsCountries.getString("Name"));
			country.setContinent(Continent.getByName(rsCountries.getString("Continent")));
			country.setRegion(rsCountries.getString("Region"));
			country.setSurfaceArea(rsCountries.getFloat("SurfaceArea"));
			country.setIndependenceYear(rsCountries.getInt("IndepYear"));
			country.setPopulation(rsCountries.getInt("Population"));
			country.setLifeExpectancy(rsCountries.getFloat("LifeExpectancy"));
			country.setGnp(rsCountries.getFloat("GNP"));
			country.setGnpOld(rsCountries.getFloat("GNPOld"));
			country.setLocalName(rsCountries.getString("LocalName"));
			country.setGovernmentForm(rsCountries.getString("GovernmentForm"));
			country.setHeadOfState(rsCountries.getString("HeadOfState"));
			country.setCode2(rsCountries.getString("Code2"));
			
			stmCountries.close();
			stmCapital.close();
			return country;
			
		} catch (SQLException e) {
			System.err.println("SQL exception: " + e.getMessage());
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing the DB connection: " + e.getSQLState());
				return null;
			}
		}	
	}
 }



