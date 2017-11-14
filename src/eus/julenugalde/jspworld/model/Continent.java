package eus.julenugalde.jspworld.model;

/** Enumeration of the continents, as used in the 'world' database */
public enum Continent {
	ASIA ("Asia"),
	EUROPE ("Europe"),
	NORTH_AMERICA ("North America"),
	AFRICA ("Africa"),
	OCEANIA ("Oceania"),
	ANTARCTICA ("Antarctica"),
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
	
	public static Continent getByName(String name) {
		//TODO Check if there's a better way to do this. Something like: 
		//return Continent.valueOf(Continent.class, name);
		if (name.equals("Asia")) return ASIA;
		if (name.equals("Europe")) return EUROPE;
		if (name.equals("North America")) return NORTH_AMERICA;
		if (name.equals("Africa")) return AFRICA;
		if (name.equals("Oceania")) return OCEANIA;
		if (name.equals("Antarctica")) return ANTARCTICA;
		if (name.equals("South America")) return SOUTH_AMERICA;
		return null;
	}
	
}
