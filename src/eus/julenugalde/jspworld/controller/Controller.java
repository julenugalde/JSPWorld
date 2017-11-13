package eus.julenugalde.jspworld.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet(name="/Controller", 
			description = "Controller servlet",
			urlPatterns = { "/" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PrintWriter pr; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		pr = response.getWriter();
		//pr.append("<p>Kaixo, mundua!</p>");
		//pr.append("<p>Served at: "+ request.getContextPath() + "</p>");
		//pr.append("<p>Servlet path: \"" + request.getServletPath() +  "\"</p>");
		addParagraph("Servlet path: \"" + request.getServletPath() +  "\"");
		addParagraph("Kaixo, mundua!");
		addParagraph("Request Dispatcher: " + request.getRequestDispatcher(""));
		
		System.err.println("Real path: "+getServletContext().getRealPath(getServletName()));
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/JSPWorld/PruebaJSP");
		System.err.println("Reuqest dispatcher: " + rd.toString());
		rd.forward(request, response);
	}
	
	private void addParagraph(String text) {
		pr.append("<p>" + text + "</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
