package fr.m2i.jersey;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.acceis.jpa.jpa.CursusDao;
import fr.acceis.jpa.jpa.Dao;
import model.Cursus;

/**
 * Servlet implementation class ServletCursus
 */
@WebServlet("/ServletCursus")
public class ServletCursus extends HttpServlet {
	
	private Dao<Cursus,Long> daoCursus=new CursusDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCursus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cursus> cursuss = daoCursus.getAll();
		
		
		
		request.setAttribute("cursuss", cursuss);
		
		RequestDispatcher rd =request.getRequestDispatcher("jsp/showCursus.jsp");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				if(request.getParameter("add")!=null){
				
				Cursus cursus = new Cursus();
				String nom = request.getParameter("cursusNom");
				cursus.setNom(nom);
				
				daoCursus.add(cursus);
				
				}
				
				else if (request.getParameter("remove")!=null){
					Long idCursus=Long.valueOf(request.getParameter("cursusId"));
					daoCursus.delete(idCursus);			
					
				}
				
				doGet(request, response);

				
	}

}
