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
 * Servlet implementation class FicheMatiereAction
 */
@WebServlet("/FicheMatiereAction")
public class FicheMatiereAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int x = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FicheMatiereAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ArrayList<Matiere> lu = (ArrayList<Matiere>) session.getAttribute("listOfMatiere");
		ArrayList<NoteCour> lnote = (ArrayList<NoteCour>) session.getAttribute("listOfnote");

		// TODO Auto-generated method stub

		String jspCible = "";
		String action = request.getParameter("mode");
		if (action != null && action.equals("Suppression")) {
			String idP = request.getParameter("id");
			for (int i = 0; i < lu.size(); i++) {
				String idU = lu.get(i).getCode();
				System.out.println("ba9ba9" + idU);

				if (idP.equals(idU)) {
					System.out.println("ba9ba9");
					MatiereDAO.supprimer(lu.get(i));
					lu = (ArrayList<Matiere>) MatiereDAO.ListerTout();

				}
			}

			session.setAttribute("listOfMatiere", lu);
			jspCible = "ListMatieres.jsp";

			// request.getRequestDispatcher("ListUsers.jsp").forward(request,
			// response);
		}

		else if (action != null && action.equals("Edition")) {

			String idP = request.getParameter("id");
			for (int i = 0; i < lu.size(); i++) {
				String idU = lu.get(i).getCode();
				if (idP.equals(idU)) {
					Matiere e = lu.get(i);
					request.setAttribute("matiere", e);

				}
			}


			 jspCible = "FicheMatiere.jsp";
			
		

		} else if (action != null && action.equals("Nature")) {
			String idP = request.getParameter("Type");
			String codem = request.getParameter("matiere");

			// this.getServletContext().getRequestDispatcher("/ListNote.jsp").include(request,
			// response);
			jspCible = "ListNote.jsp";

		} else if (action != null && action.equals("NoteA")) {

			String codem = request.getParameter("code");
			String rdv = request.getParameter("r");
			float note = Float.parseFloat(request.getParameter("note"));
			int z = NoteDAO.ajouterN(rdv, note, codem);
			x += z;

			// update

			lnote = (ArrayList<NoteCour>) NoteDAO.ListerTout();
			lu = (ArrayList<Matiere>) MatiereDAO.ListerTout();

			for (int i = 0; i < lu.size(); i++) {
				String idU = lu.get(i).getCode();

				if (codem.equals(idU)) {
				//	if (x % 3 == 0) {
	if (MatiereDAO.Moyenne(codem)==true)
		{
		lu.get(i).setMoyenne(MatiereDAO.CalculMoyenne(codem));
		MatiereDAO.modifiermoyenne(MatiereDAO.CalculMoyenne(codem), codem);
		}
				
						//MatiereDAO.modifiermoyenne(MatiereDAO.Moyenne(codem), codem);
						lu = (ArrayList<Matiere>) MatiereDAO.ListerTout();
					//}

				}
			}
			session.setAttribute("listOfnote", lnote);
			session.setAttribute("listOfMatiere", lu);

		
			jspCible = "ListNote.jsp";

		}

		request.getRequestDispatcher(jspCible).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> erreurs = new ArrayList<String>();
		String code = request.getParameter("code");
		String libelle = request.getParameter("libelle");
		String coeff = request.getParameter("coeff");
		String type = request.getParameter("r");
		if (code == null || code.equals(""))
			erreurs.add("SVP saisir le champs Code");

		else if (libelle == null || libelle.equals(""))
			erreurs.add("SVP saisir le champs Libelle");

		else if (coeff == null || coeff.equals(""))
			erreurs.add("SVP saisir le champs Coeffecient");

		if (erreurs.size() != 0) {
			float coeffe = Float.parseFloat(coeff);
			int typ = Integer.parseInt(type);

			Matiere mat = new Matiere();
			mat.setCode(code);
			mat.setCoeificient(coeffe);
			mat.setLibelle(libelle);
			mat.setType(typ);
			request.setAttribute("matiere", mat);
			request.setAttribute("err", erreurs);
			request.getRequestDispatcher("FicheMatiere.jsp").forward(request, response);
		} else {

			HttpSession session = request.getSession(true);
			ArrayList<NoteCour> lnote = (ArrayList<NoteCour>) session.getAttribute("listOfnote");
			ArrayList<Matiere> matiereStore = (ArrayList<Matiere>) session.getAttribute("listOfMatiere");
			Matiere e = new Matiere(code, libelle, Float.parseFloat(coeff), Integer.parseInt(type), 0);
			System.out.println("dsqsqd");

			if (MatiereDAO.Rechercher(e) != 0)
				MatiereDAO.modifier(e);
			else {
				MatiereDAO.ajouter(e);
				NoteDAO.ajouter(e);
			}
			matiereStore = (ArrayList<Matiere>) MatiereDAO.ListerTout();
			lnote = (ArrayList<NoteCour>) NoteDAO.ListerTout();

			session.setAttribute("listOfMatiere", matiereStore);
			session.setAttribute("listOfnote", lnote);

			request.getRequestDispatcher("ListMatieres.jsp").forward(request, response);

		}

		// doGet(request, response);
	}

}
