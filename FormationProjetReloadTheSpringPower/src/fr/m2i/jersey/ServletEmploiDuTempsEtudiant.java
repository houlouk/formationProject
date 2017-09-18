package fr.m2i.jersey;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.acceis.jpa.jpa.EtudiantDao;
import fr.acceis.jpa.jpa.IEtudiantDao;
import model.Etudiant;
import nonPojoModel.EmploiDuTemps;

/**
 * Servlet implementation class EmploiDuTempsEtudiant
 */
@WebServlet("/ServletEmploiDuTempsEtudiant")
public class ServletEmploiDuTempsEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IEtudiantDao daoEtud;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEmploiDuTempsEtudiant() {
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
		// TODO Auto-generated method stub
		daoEtud = new EtudiantDao();
		
		String numeroToGetEdt = request.getParameter("etudiantNumero");
		EmploiDuTemps<Etudiant> edt = daoEtud.getEmploiDuTemps(numeroToGetEdt);
		

		request.setAttribute("etudiantNumero", numeroToGetEdt);
		request.setAttribute("creneauemploidutempss",edt.getEmploiDuTemps());
		RequestDispatcher rd =request.getRequestDispatcher("jsp/showEdtEtudiant.jsp");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		rd.forward(request, response);
	}

}
