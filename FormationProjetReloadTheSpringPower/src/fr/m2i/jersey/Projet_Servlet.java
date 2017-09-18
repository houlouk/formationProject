package fr.m2i.jersey;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Projet_Servlet
 */
@WebServlet(description = "Ajouter terrain de sport", urlPatterns = { "/AjouterTerrain" })
public class Projet_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Projet_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputText=request.getParameter("inputText");
		System.out.println(inputText);
		
		String[] sports = request.getParameterValues("selectSport");
		
		
		for(String s:sports){
			switch (s) {
			case "1":
				System.out.println("Badminton");
				break;
				
			case "2":
				System.out.println("Squash");
				break;
				
			case "3":
				System.out.println("Tennis");
				break;

			default:
				break;
			}
		}
	}

}
