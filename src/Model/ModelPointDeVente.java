/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Controlleur.Connexion;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Yossra
 */
public class ModelPointDeVente {
    private String adresse;
    public ModelPointDeVente(String adresse)
    {this.adresse=adresse;
        
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }
    
     public static void ajouter(Connection con ,ModelPointDeVente p){
        // System.out.print(p.getAdresse());
		String sql="INSERT INTO pointdevente(adresse) VALUES(?)";
               
                //System.out.println("La requette est "+sql+" eeeeeeeeeeeeeeeeeeee");
		try {
			PreparedStatement pst=con.prepareStatement(sql);
                        //  System.out.println("s");
			 pst.setString(1,p.getAdresse() );
                    
			  pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Le Message est "+e.getMessage());
		}	
		
	}
     public static DefaultComboBoxModel remplirPV(Connection conn,DefaultComboBoxModel<String> mPV){

		mPV=new DefaultComboBoxModel<String>();
		try{
		String sql="Select * From pointdevente";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next()){	
			String nom=rs.getString(2);
			mPV.addElement(nom);
	}		
		rs.close();
		st.close();		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return mPV;
	}
     public static DefaultComboBoxModel remplirPV2(Connection conn,DefaultComboBoxModel<String> mPV){

		mPV=new DefaultComboBoxModel<String>();
		try{
		String sql="Select * From pointdevente";
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
     
       public static ResultSet afficher(Connection conn ){

		ResultSet rs = null;
		String sql="select * from pointdevente";
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
