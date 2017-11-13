package eus.julenugalde.jspworld.model;

/** Class that encapsulates the country information in the 'world' database */
public class Country {
	/** Code char(3) primary key no null - country code */
	private String code;
	
	/** Name char(52) no null - country name */
	private String name;
	
	/** Continent enum no null default:"Asia" - continent */
	private Continent continent;
	
	/** Region char(26) no null - Region */
	private String region;
	
	/** SurfaceArea float(10,2) no null default:0.00 - Surface area */	//TODO ver unidades 
	private float surfaceArea;
	
	/** IndepYear smallint(6) default:null - Independence year */
	private int independenceYear;
	
	/** Population int(11) no null - Population */
	private int population;
	
	/** LifeExpectancy float(3,1) default:null - Life Expectancy */
	private float lifeExpectancy;
	
	/** GNP float(10,2) default:null - Gross National Product in USD */
	private float gnp;
	
	/** GNPOld float(10,2) default:null - Old Gross National Product */
	private float gnpOld;
	
	/** LocalName char(45) no null - Local name */
	private String localName;
	
	/** GovernmnentForm char(45) no null - Government form */
	private String governmentForm;
	
	/** HeadOfState char(60) default:null - Head of State */
	private String headOfState;
	
	/** Capital int(11) default:null - Capital */
	private String capital;
	
	/** Code2 char(2) no null - 2-character country code */
	private String code2;
	
	public Country() {
		setCode("");
		setName("");
		setContinent(Continent.ASIA);
		setRegion("");
		setSurfaceArea((float) 0.00);
		setIndependenceYear(0);
		setPopulation(0);
		setLifeExpectancy((float) 0.00);
		setGnp((float) 0.00);
		setGnpOld((float) 0.00);
		setLocalName("");
		setGovernmentForm("");
		setHeadOfState("");
		setCapital("");
		setCode2("");
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the continent
	 */
	public Continent getContinent() {
		return continent;
	}

	/**
	 * @param continent the continent to set
	 */
	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the surfaceArea
	 */
	public float getSurfaceArea() {
		return surfaceArea;
	}

	/**
	 * @param surfaceArea the surfaceArea to set
	 */
	public void setSurfaceArea(float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	/**
	 * @return the independenceYear
	 */
	public int getIndependenceYear() {
		return independenceYear;
	}

	/**
	 * @param independenceYear the independenceYear to set
	 */
	public void setIndependenceYear(int independenceYear) {
		this.independenceYear = independenceYear;
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

	/**
	 * @return the lifeExpectancy
	 */
	public float getLifeExpectancy() {
		return lifeExpectancy;
	}

	/**
	 * @param lifeExpectancy the lifeExpectancy to set
	 */
	public void setLifeExpectancy(float lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	/**
	 * @return the gnp
	 */
	public float getGnp() {
		return gnp;
	}

	/**
	 * @param gnp the gnp to set
	 */
	public void setGnp(float gnp) {
		this.gnp = gnp;
	}

	/**
	 * @return the gnpOld
	 */
	public float getGnpOld() {
		return gnpOld;
	}

	/**
	 * @param gnpOld the gnpOld to set
	 */
	public void setGnpOld(float gnpOld) {
		this.gnpOld = gnpOld;
	}

	/**
	 * @return the localName
	 */
	public String getLocalName() {
		return localName;
	}

	/**
	 * @param localName the localName to set
	 */
	public void setLocalName(String localName) {
		this.localName = localName;
	}

	/**
	 * @return the governmentForm
	 */
	public String getGovernmentForm() {
		return governmentForm;
	}

	/**
	 * @param governmentForm the governmentForm to set
	 */
	public void setGovernmentForm(String governmentForm) {
		this.governmentForm = governmentForm;
	}

	/**
	 * @return the headOfState
	 */
	public String getHeadOfState() {
		return headOfState;
	}

	/**
	 * @param headOfState the headOfState to set
	 */
	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}

	/**
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * @param capital the capital to set
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * @return the code2
	 */
	public String getCode2() {
		return code2;
	}

	/**
	 * @param code2 the code2 to set
	 */
	public void setCode2(String code2) {
		this.code2 = code2;
	}


}
