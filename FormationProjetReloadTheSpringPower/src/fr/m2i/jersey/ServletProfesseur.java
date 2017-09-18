package fr.m2i.jersey;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.acceis.jpa.jpa.Dao;
import fr.acceis.jpa.jpa.ProfesseurDao;
import model.Professeur;

/**
 * Servlet implementation class ServletProfesseur
 */
@WebServlet("/ServletProfesseur")
public class ServletProfesseur extends HttpServlet {
	Dao<Professeur, Long> daoProf = new ProfesseurDao();

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfesseur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Professeur> professeurs = daoProf.getAll();
		
		
		
		request.setAttribute("professeurs", professeurs);
		
		RequestDispatcher rd =request.getRequestDispatcher("jsp/showProfesseurs.jsp");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("add")!=null){
			
			Professeur professeur = new Professeur();
			String nom = request.getParameter("professeurNom");
			String prenom = request.getParameter("professeurPrenom");

			professeur.setNom(nom);
			professeur.setPrenom(prenom);
			
			daoProf.add(professeur);
			
			}
			
			else if (request.getParameter("remove")!=null){
				Long idProf=Long.valueOf(request.getParameter("professeurId"));
				daoProf.delete(idProf);
			}
			
			doGet(request, response);

				}

}
