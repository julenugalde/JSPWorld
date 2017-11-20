package eus.julenugalde.jspworld.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/** Test class that implements the {@link HttpSessionListener} interface. Outputs the session id
 * in the console when a session is created or destroyed
 */
public class JSPWorldSessionListener implements HttpSessionListener {
	private static int totalActiveSessions;
	
	public JSPWorldSessionListener() {
		super();
		totalActiveSessions = 0;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		totalActiveSessions++;
		System.out.println("Session created. idSession='" + se.getSession().getId() + 
				"', creationTime='" + se.getSession().getCreationTime() + 
				"', remoteAddress='" + se.getSession().getAttribute("ip") + "'");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		totalActiveSessions--;
		System.out.println("Session destroyed. idSession=" + se.getSession().getId());
	}

	/** Method that returns the total number of active sessions
	 * 
	 * @return Number of active HTTP sessions
	 */
	public int getNumActiveSessions() {
		return totalActiveSessions;
	}
}
