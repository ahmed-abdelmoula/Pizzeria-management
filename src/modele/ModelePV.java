package Modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;

public class ModelePV {

	int idPv;
	String adrPv,telPv,email;
	public ModelePV(int idPv, String adrPv, String telPv, String email) {
		this.idPv = idPv;
		this.adrPv = adrPv;
		this.telPv = telPv;
		this.email = email;
	}

	public static DefaultComboBoxModel remplirCat(Connection conn,DefaultComboBoxModel<String> mCat){

		mCat=new DefaultComboBoxModel<String>();
		try{
		String sql="Select * From pointvente";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next()){	
			String nom=rs.getString(2);
			mCat.addElement(nom);
	}		
		rs.close();
		st.close();		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return mCat;
	}
	
	public static ResultSet afficherEmail(Connection conn,String nom ){

		ResultSet rs = null;
		String sql="select * from pointvente where adrPV like'"+nom+"%'";
		try{
			Statement st=conn.createStatement();
			rs=st.executeQuery(sql);
			
			//rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
}
