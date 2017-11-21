package eus.julenugalde.jspworld.model;

import java.io.Serializable;

/** Enumeration of the continents, as used in the 'world' database */
public enum Continent implements Serializable {
	/** Continent "Asia" */
	ASIA ("Asia"),
	/** Continent "Europe" */
	EUROPE ("Europe"),
	/** Continent "North America" (including Central America) */
	NORTH_AMERICA ("North America"),
	/** Continent "Africa" */
	AFRICA ("Africa"),
	/** Continent "Oceania" */
	OCEANIA ("Oceania"),
	/** Continent "Antarctica" */
	ANTARCTICA ("Antarctica"),
	/** Continent "South America" */
	SOUTH_AMERICA ("South America");
	
	private final String name;
	
	private Continent(String name) {
		this.name = name;
	}
	
	/** returns the name used for the Continent enum in the <b>Country</b> database
	 * 
	 * @return {@link String} with the continent's name
	 */
	public String getName() {return name;}
	
	/** Return the enumeration value based on its name *
	 * 
	 * @param name Continent name in English (case insensitive)
	 * @return Corresponding {@link Continent} enumeration value
	 */
	public static Continent getByName(String name) {
		if (name.toLowerCase().equals("asia")) return ASIA;
		if (name.toLowerCase().equals("europe")) return EUROPE;
		if (name.toLowerCase().equals("north america")) return NORTH_AMERICA;
		if (name.toLowerCase().equals("northamerica")) return NORTH_AMERICA;
		if (name.toLowerCase().equals("africa")) return AFRICA;
		if (name.toLowerCase().equals("oceania")) return OCEANIA;
		if (name.toLowerCase().equals("antarctica")) return ANTARCTICA;
		if (name.toLowerCase().equals("south america")) return SOUTH_AMERICA;
		if (name.toLowerCase().equals("southamerica")) return SOUTH_AMERICA;
		return null;
	}
	
}
