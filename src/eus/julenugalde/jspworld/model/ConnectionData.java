package eus.julenugalde.jspworld.model;

public class ConnectionData {
	private String user;
	private String password;
	private String address;
	private int port;
	
	public ConnectionData(String userName, String password, String dbServerAddress, int dbServerPort) {
		this.setUser(userName);
		this.setPassword(password);
		this.setAddress(dbServerAddress);
		this.setPort(dbServerPort);
	}
	
	public ConnectionData() {
		this("", "", "", -1);
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	
}
