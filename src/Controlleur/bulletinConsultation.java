package Controlleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MatiereDAO;
import dao.NoteDAO;
import modele.Matiere;
import modele.NoteCour;

/**
 * Servlet implementation class bulletinConsultation
 */
@WebServlet("/bulletinConsultation")
public class bulletinConsultation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bulletinConsultation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jspCible = request.getParameter("mode");
		HttpSession session = request.getSession();
		ArrayList<NoteCour> lnote = (ArrayList<NoteCour>) session.getAttribute("listOfnote");
		ArrayList<Matiere> lu = (ArrayList<Matiere>) session.getAttribute("listOfMatiere");
		lu = (ArrayList<Matiere>) MatiereDAO.ListerTout();
		session.setAttribute("listOfMatiere", lu);
		lnote = (ArrayList<NoteCour>) NoteDAO.ListerTout();
		session.setAttribute("listOfnote", lnote);
		
		

		request.getRequestDispatcher(jspCible+".jsp").forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
