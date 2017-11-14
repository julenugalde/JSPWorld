package eus.julenugalde.jspworld.model;

/** Class that encapsulates the information of the 'countrylanguage' table in the 'world' database.
 * */
public class Language {
	/** CountryCode char(3) no null, primary key - 3-char country code */
	private String countryCode;
	
	/** Language char(30) no null, primary key - language name */
	private String name;
	
	/** IsOfficia enum('T', 'F') no null default:F - officialty status of the language */
	private boolean official;
	
	/* Percentage float(4,1) no null default:0.0 - percentage of speakers in the country*/
	private float percentage;
	
	public Language() {
		this("", "", false, 0);
	}
	
	public Language(String countryCode, String name, boolean official, float percentage) {
		this.setCountryCode(countryCode);
		this.setName(name);
		this.setOfficial(official);
		this.setPercentage(percentage);
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
	 * @return the official
	 */
	public boolean isOfficial() {
		return official;
	}

	/**
	 * @param official the official to set
	 */
	public void setOfficial(boolean official) {
		this.official = official;
	}

	/**
	 * @return the percentage
	 */
	public float getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public String toString() {
		if (official) return name + ", official, " + percentage + "%";
		else return name + ", not official, " + percentage + "%";
	}
	
}
