package eus.julenugalde.jspworld.model;

import java.util.Hashtable;

public interface Model {
	public void openDBConnection();
	public Hashtable<String,Country> getCountryList();
	//public Hashtable<String, City> getCityList();
	//public Hashtable<String, City> getCityByCountry(String countryCode);
	public void closeDBConnection();
}
