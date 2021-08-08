package Modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;

public class ModeleFournir {
	String date;
	int qte,codeProd,codePV;
	
	public ModeleFournir(String date, int qte, int codeProd, int codePV) {
		super();
		this.date = date;
		this.qte = qte;
		this.codeProd = codeProd;
		this.codePV = codePV;
	}
	
	public static void enregistrer(Connection conn,ModeleFournir p) {

		String sql="insert into vente (date,qteProd,codeProd,codePV) values(?,?,?,?)";
		try{
			PreparedStatement pst=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			pst.setString(1,p.date );
			pst.setInt(2, p.qte);
			pst.setInt(3, p.codeProd);
			pst.setInt(4, p.codePV);
			pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			rs.getInt(1);
		}catch(SQLException e1){
			e1.getStackTrace();
		}
	}
	
	
	public static DefaultComboBoxModel remplirCat(Connection conn,DefaultComboBoxModel<String> mId){

		mId=new DefaultComboBoxModel<String>();
		try{
		String sql="Select * From produit";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next()){	
			String nom=rs.getString(1);
			mId.addElement(nom);
	}		
		rs.close();
		st.close();		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return mId;
	}
	
	public static DefaultComboBoxModel remplirPV(Connection conn,DefaultComboBoxModel<String> mPV){

		mPV=new DefaultComboBoxModel<String>();
		try{
		String sql="Select * From pointvente";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next()){	
			String nom=rs.getString(1);
			mPV.addElement(nom);
	}		
		rs.close();
		st.close();		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return mPV;
	}
	
	

}
