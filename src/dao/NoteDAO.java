package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Matiere;
import modele.NoteCour;

public class NoteDAO {

	public static void ajouter(Matiere m) {
		DataAccess da =DataAccess.getInstance();

		String sql = "insert into notecour(code,noteds,noteExamin,autrenote) values(?,?,?,?)";
		try {
			PreparedStatement pst = da.connecter().prepareStatement(sql);

			pst.setString(1, m.getCode());
			
			pst.setString(2 , null);
			pst.setString(3,  null);
			pst.setString(4,  null);
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static int ajouterN(String col,float note,String mat ) {
		DataAccess da =DataAccess.getInstance();
		int res=0;
		//System.out.println(mat);
		String updateTableSQL = "UPDATE notecour SET "+col+" = ? WHERE code = ?";

		
		try {
			PreparedStatement pst = da.connecter().prepareStatement(updateTableSQL);

pst.setFloat(1, note);
pst.setString(2, mat);	

 res=pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;

	}
	
	public static List<NoteCour> ListerTout()
	{			DataAccess da =DataAccess.getInstance();

		ResultSet rs = da.consulter("select * from NoteCour");
	ArrayList notes = null;
	if (rs != null) {
		notes = new ArrayList();
		try {
			while (rs.next()) {
				String code= rs.getString("code");
				float autre = rs.getFloat("autrenote");
				float noteds = rs.getFloat("noteds");
				float notex = rs.getFloat("noteExamin");
				float notetp = rs.getFloat("notetp");


								
              NoteCour E=new NoteCour(code,notex,noteds,autre,notetp);
              notes.add(E);
			}
		} catch (SQLException e) {
			System.out.println("Erreur");
			e.printStackTrace();

		}
	}
	return notes;

}
}
