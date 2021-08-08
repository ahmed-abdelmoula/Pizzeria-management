package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import modele.Matiere;

public class MatiereDAO {

	public static void ajouter(Matiere m) {

		DataAccess da = DataAccess.getInstance();
		System.out.println("fsdf");
		String sql = "insert into matiere values('" + m.getCode() + "','" + m.getLibelle() + "','" + m.getCoeificient()
				+ "','" + m.getType() + "','" + 0 + "')";
		da.mettreAJour(sql);

		// public Etudiant(String cin, String nom, String prenom, Date
		// datenaissance, String adresse, String mail,String tel

	}

	public static void supprimer(Matiere m) {
		DataAccess da = DataAccess.getInstance();

		String sql = "delete from matiere where code=" + m.getCode();
		da.mettreAJour(sql);
	}

	public static void modifier(Matiere e) {
		DataAccess da = DataAccess.getInstance();

		String sql = "update matiere set libelle='" + e.getLibelle() + "' , coeifficient='" + e.getCoeificient()
				+ "' , type='" + e.getType() + "' where code=" + e.getCode();
		da.mettreAJour(sql);
	}

	public static void modifiermoyenne(float moy, String code) {
		DataAccess da = DataAccess.getInstance();

		String sql = "update matiere set moyenne='" + moy + "' where code=" + code;
		da.mettreAJour(sql);
	}

	public static float CalculMoyenne(String e) {
	
		float n = 0;
	

		DataAccess da = DataAccess.getInstance();

		ResultSet rs = da.consulter("select noteds,noteExamin,autrenote from notecour where code=" + e);
		try {

			while (rs.next()) {
				System.out.println("ba9ba9 ");

				float nd = rs.getFloat("noteds");
				float ne = rs.getFloat("noteExamin");
				float an = rs.getFloat("autrenote");
				n=(float) (nd*0.3+ne*0.5+an*0.2);


			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return n;

	}

	public static boolean Moyenne(String e) {
		Vector<Object> v = new Vector<>();
		boolean b = true;
		float n = 0;
		int i = 0;

		DataAccess da = DataAccess.getInstance();

		ResultSet rs = da.consulter("select noteds,noteExamin,autrenote from notecour where code=" + e);
		try {

			while (rs.next()) {
				System.out.println("ba9ba9 ");

				String nd = rs.getString("noteds");
				String ne = rs.getString("noteExamin");
				String an = rs.getString("autrenote");
				v.add(ne);
				v.add(nd);
				v.add(an);
				while (i < v.size() && b == true) {
					if (v.get(i) == null)
						b = false;
					else
						i++;
				}

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return b;

	}

	public static int Rechercher(Matiere e) {
		int n = 0;
		DataAccess da = DataAccess.getInstance();

		ResultSet rs = da.consulter("select count(*) from matiere where code=" + e.getCode());
		try {
			while (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return n;

	}

	public static List<Matiere> ListerTout() {
		DataAccess da = DataAccess.getInstance();

		ResultSet rs = da.consulter("select * from Matiere");
		ArrayList Matieres = null;
		if (rs != null) {
			Matieres = new ArrayList();
			try {
				while (rs.next()) {
					String code = rs.getString("code");
					String libelle = rs.getString("libelle");
					float coeificient = rs.getFloat("coeifficient");
					int type = rs.getInt("type");
					float moye = rs.getFloat("moyenne");
					Matiere E = new Matiere(code, libelle, coeificient, type, moye);
					Matieres.add(E);
				}
			} catch (SQLException e) {
				System.out.println("Erreur");
				e.printStackTrace();

			}
		}
		return Matieres;

	}

}