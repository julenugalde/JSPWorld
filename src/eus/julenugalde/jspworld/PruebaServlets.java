package eus.julenugalde.jspworld;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PruebaServlets
 */
@WebServlet(description = "Clase para probar los HttpServlets", 
			urlPatterns = { "/PruebaServlets" })
public class PruebaServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PruebaServlets() {
        super();
        //System.out.println("Servlet creado");
    }

	public void init (ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void destroy() {
		super.destroy();
	}
    
	
	
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		StringBuilder sb = new StringBuilder("<p>Parametros en <b>doGet()</b>:<br/><ul>");
		Enumeration<String> parametros = request.getParameterNames();
		String parametroActual;
		while (parametros.hasMoreElements()) {
			parametroActual = parametros.nextElement();
			sb.append("<li>" + parametroActual + " = " + 
					request.getParameter(parametroActual) + "</li>");
		}
		sb.append("</ul></p>");
		generarRespuesta(request, response, sb.toString());
	}

	private void generarRespuesta(HttpServletRequest request, 
			HttpServletResponse response, String valor) 
			throws ServletException, IOException {
		//Cargar datos desde cookies
		String cadenaFecha = "No se había almacenado la fecha y hora";
		Cookie[] snacks = request.getCookies();
		if (!(snacks == null)) {
			for (int i=0; i<snacks.length; i++) {
				if (snacks[i].getName().equals("date")) {
					cadenaFecha = "Número de cookies almacenadas: " + snacks.length + 
						"<br />Hora del último acceso a la página: " + snacks[i].getValue();
				}
			}
		}
		
		//RequestDispatcher rd = getServletContext().getRequestDispatcher(arg0)
		//rd.include(request, response);
				
		response.setContentType("text/html");
		PrintWriter  out = response.getWriter();
		out.println("<html><head><title>Servlet de prueba</title></head>");
		out.println("<body><h3>Titulo</h3>");
		out.println("<p>Esto es una pagina HTML sencilla para probar los Servlets.</p>");
		out.println("<p>Texto en <b>negrita</b>, <i>cursiva</i> y <u>subrayado</u>.</p>");
		out.println("<p>" + cadenaFecha + "</p>");
		out.println(valor);
		out.println("</body></html>");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd_HH:mm:ss");
		Cookie fecha = new Cookie("date", sdf.format(new Date()));
		response.addCookie(fecha);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		StringBuilder sb = new StringBuilder("<p>Parametros en <b>doPost()</b>:<br/><ul>");
		Enumeration<String> parametros = request.getParameterNames();
		String parametroActual;
		while (parametros.hasMoreElements()) {
			parametroActual = parametros.nextElement();
			sb.append("<li>" + parametroActual + " = \"" + 
					request.getParameter(parametroActual) + "\"</li>");
		}
		sb.append("</ul></p>");
		generarRespuesta(request, response, sb.toString());
	}
	
	public String setServletInfo() {
		return "Servlet de prueba";
	}

}
