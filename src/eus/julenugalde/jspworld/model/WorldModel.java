package eus.julenugalde.jspworld.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.dbcp.BasicDataSource;

public class WorldModel implements Model {
	BasicDataSource dataSource;
	Connection con;
	PreparedStatement stm;
	
	String user;
	String password;
	int port;
	String dbServer;
	
	Hashtable<String, Country> tableCountries;
	
	public WorldModel(String dbServer, int port, String user, String password) {
		tableCountries = new Hashtable<String, Country>();
		this.user = user;
		this.dbServer = dbServer;
		this.port = port;
		this.password = password;
		dataSource = null;
		con = null;
		stm = null;
	}
	

	@Override
	public void openDBConnection() {
		try {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://" + dbServer + 
					":" + String.valueOf(port) + "/world" + 
					"?autoReconnect=true&useSSL=false");
			dataSource.setUsername(user);
			dataSource.setPassword(password);
			con = dataSource.getConnection();
			
		} catch (SQLException e) {
			System.err.println("SQL exception: " + e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (stm != null) {
					stm.close();
				}
			} catch (SQLException e) {
				System.err.println("Error cerrando conexiones: " + e.getSQLState());
			}
		}
	}

	@Override
	public Hashtable<String, Country> getCountryList() {
		try {
			con = dataSource.getConnection();
			stm = con.prepareStatement("SELECT * FROM country");
			ResultSet rs = stm.executeQuery();
			
			//DEBUG - View metadata
			/*java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("Type\tSize\tName");
			System.out.println("----------------------------------------------");
			for (int i=1; i<=rsmd.getColumnCount(); i++) {
				System.out.println(rsmd.getColumnTypeName(i) + "\t" + 
						rsmd.getColumnDisplaySize(i) + "\t" + rsmd.getColumnName(i));				
			}*/
			
			tableCountries.clear();
			
			Country temp = new Country();
			while (rs.next()) {
				temp.setCode(rs.getString("Code"));
				temp.setName(rs.getString("Name"));
				temp.setContinent(Continent.getByName(rs.getString("Continent")));
				temp.setRegion(rs.getString("Region"));
				temp.setSurfaceArea(rs.getFloat("SurfaceArea"));
				temp.setIndependenceYear(rs.getInt("IndepYear"));
				temp.setPopulation(rs.getInt("Population"));
				temp.setLifeExpectancy(rs.getFloat("LifeExpectancy"));
				temp.setGnp(rs.getFloat("GNP"));
				temp.setGnpOld(rs.getFloat("GNPOld"));
				temp.setLocalName(rs.getString("LocalName"));
				temp.setGovernmentForm(rs.getString("GovernmentForm"));
				temp.setHeadOfState(rs.getString("HeadOfState"));
				//temp.setCapital(rs.getInt("Capital"));	//TODO CAMBIAR PARA QUE SEA TIPO CITY
				temp.setCode2(rs.getString("Code2"));
				tableCountries.put(temp.getCode(), temp);
			}
			
			//TODO COMPLETAR
			return tableCountries;
			
		} catch (SQLException e) {
			System.err.println("SQL exception: " + e.getMessage());
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (stm != null) {
					stm.close();
				}
			} catch (SQLException e) {
				System.err.println("Error cerrando conexiones: " + e.getSQLState());
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
			if (stm != null) {
				stm.close();
			}
		} catch (SQLException e) {
			System.err.println("Error cerrando conexiones: " + e.getSQLState());
		}
	}
	
	public static void exportCountriesToCSV(Hashtable<String, Country> tableCountries, File file) {
	
		try {
			if (!file.exists()) file.createNewFile();
			FileWriter fr = new FileWriter(file);
			fr.write("Code;Name;Continent;Region;Surface area;Independence year;Population;");
			fr.write("Life expectancy;GNP;Old GNP;Local name;Government form;Head of state;");
			fr.write("Capital;2-caracter country coude\n");
			Set<String> keys = tableCountries.keySet();
			Iterator<String> iterator = keys.iterator();
			Country c;
			while (iterator.hasNext()) {
				c = (Country)tableCountries.get(iterator.next());
				System.out.println("pais: " + c.getName());
				fr.write(c.getCode() + ";" + c.getName() + ";" + c.getContinent().getName() + 
						 ";" + c.getRegion() + ";" + c.getSurfaceArea() + ";" + 
						 c.getIndependenceYear() + ";" + c.getPopulation() + ";" +
						 c.getLifeExpectancy() + ";" + c.getGnp() + ";" + c.getGnpOld() + 
						 ";" + c.getLocalName() + ";" + c.getGovernmentForm() + ";" + 
						 c.getHeadOfState() + ";" + c.getCapital() + ";" + c.getCode2());
				fr.write("\n");
			}
			
			fr.close();
		} catch (IOException ioex) {
			System.err.println("Error: " + ioex.getLocalizedMessage());
		}
	}
 }



