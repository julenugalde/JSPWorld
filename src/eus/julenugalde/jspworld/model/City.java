package eus.julenugalde.jspworld.model;

import java.io.Serializable;

/** Class that encapsulates the 'city' table information in the 'world' database */
public class City implements Serializable {
	private static final long serialVersionUID = 8845342093625111033L;

	/** ID int(11) no null primary key autoincrement - City id */
	private int id;
	
	/** Name char(35)  no null - City name */
	private String name;
	
	/** CountryCode char(3) multiple key - Country code (3-character) */
	private String countryCode;
	
	/** District char(20) no null - District */
	private String district;
	
	/** Population int(11) no null default:0 - Population */
	private int population;
	
	public City() {
		this(0,  "", "", "", 0);
	}
	
	public City (int id, String name, String countryCode, String district, int population) {
		this.setId(id);
		this.setName(name);
		this.setCountryCode(countryCode);
		this.setDistrict(district);
		this.setPopulation(population);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ \"id\": ");
		if (id == 0) sb.append("null"); 
		else sb.append("\"" + id + "\""); 
		
		sb.append(", \"name\": ");
		if (name.equals("")) sb.append("null");
		else sb.append("\"" + name + "\"");
		
		sb.append(", \"countryCode\": ");
		if (countryCode.equals("")) sb.append("null");
		else sb.append("\"" + countryCode + "\"");
		
		sb.append(", \"district\": ");
		if (district.equals("")) sb.append("null");
		else sb.append("\"" + district + "\"");
		
		sb.append(", \"population\": ");
		if (population == 0) sb.append("null }");
		else sb.append("\"" + population + "\" }");

		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof City)) return false;
		City city = (City)obj;
		return (city.id == this.id);
	}
}
