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
import fr.acceis.jpa.jpa.EtudiantDao;
import fr.acceis.jpa.jpa.IEtudiantDao;
import model.Cursus;

/**
 * Servlet implementation class ServletEtudiantCursus
 */
@WebServlet("/ServletEtudiantCursus")
public class ServletEtudiantCursus extends HttpServlet {
	private IEtudiantDao daoEtud = new EtudiantDao();
	private Dao<Cursus, Long> daoCurs = new CursusDao();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEtudiantCursus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String numeroToListCursus = request.getParameter("etudiantNumero");
		
		Cursus cursus = daoEtud.getCursus(numeroToListCursus);
		request.setAttribute("cursus", cursus);
		request.setAttribute("etudiantNumero", numeroToListCursus);


		RequestDispatcher rd =request.getRequestDispatcher("jsp/showEtudiantCursus.jsp");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String numeroToAddCursus = request.getParameter("etudiantNumero");

		if(request.getParameter("chooseCursus")!=null) {
		
		request.setAttribute("etudiantNumero", numeroToAddCursus);
		List<Cursus> cursuss = daoCurs.getAll();
		request.setAttribute("cursuss", cursuss);
		
		RequestDispatcher rd =request.getRequestDispatcher("jsp/chooseCursus.jsp");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		rd.forward(request, response);
		
		}
		
		else if(request.getParameter("addCursus")!=null){
			Long cursusId = Long.valueOf(request.getParameter("cursusId"));
			Cursus cursus = daoCurs.get(cursusId);
			daoEtud.addCursus(numeroToAddCursus, cursus);
			
			RequestDispatcher rd =request.getRequestDispatcher("/ServletEtudiant");
			rd.forward(request, response);
			
		}

		
		

}
}
