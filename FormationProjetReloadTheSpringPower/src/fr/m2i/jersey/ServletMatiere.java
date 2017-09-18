package fr.m2i.jersey;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.acceis.jpa.jpa.CursusDao;
import fr.acceis.jpa.jpa.Dao;
import fr.acceis.jpa.jpa.ICursusDao;
import fr.acceis.jpa.jpa.MatiereDao;
import model.Matiere;

/**
 * Servlet implementation class ServletMatiere
 */
@WebServlet("/ServletMatiere")
public class ServletMatiere extends HttpServlet {
	private ICursusDao daoCur = new CursusDao();
	private Dao<Matiere,Long> daoMat = new MatiereDao();

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMatiere() {
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
		long idCursus = Long.valueOf(request.getParameter("cursusId"));
		Matiere matiere = new Matiere();
		String matiereNom=request.getParameter("matiereNom");
		matiere.setNom(matiereNom);
		
		matiere = daoMat.add(matiere);
		daoCur.addMatiere(idCursus, matiere);
		
	}

}
