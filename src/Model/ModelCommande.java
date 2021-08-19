/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author Yossra
 */
public class ModelCommande {

  
    java.util.Date date;
	int qte,codeProd,codePV;
        float som;
	public ModelCommande(java.util.Date d, int qte, int codeProd, int codePV,float s) {
		super();
		this.date = d;
		this.qte = qte;
		this.codeProd = codeProd;
		this.codePV = codePV;
                som=s;
	}
	
	public static void enregistrer(Connection conn,ModelCommande p) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		String sql="insert into commande (pointdeVente,date,produit,quantite,somme) values(?,?,?,?)";
                float t=0.0f;
		try{
			PreparedStatement pst=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                        pst.setInt(1, p.codePV);
			pst.setObject(2,sdf.format(p.date) );
                        pst.setInt(3, p.codeProd);
			pst.setFloat(4, p.qte);
                        pst.setFloat(5, p.som);

                        
                       // pst.setFloat(5,t );
                       
			pst.executeUpdate();
                        System.out.println("ccccc");
            ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			rs.getInt(1);
		}catch(SQLException e1){
			System.out.println(e1.getMessage());
		}
	}
	
	 public static ResultSet afficher(Connection conn ){

		ResultSet rs = null;
		String sql="select * from commande";
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
