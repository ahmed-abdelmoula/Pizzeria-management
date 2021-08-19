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

/**
 *
 * @author Yossra
 */
public class ModeleCategorie {

   
    int id;
	String nom,description;
	
	
	
	public ModeleCategorie(int id, String nom, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ModeleCategorie(String nom, String description) {
		this.nom = nom;
		this.description = description;
	}

	public ModeleCategorie() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ClasseCategorie [nom=" + nom + ", description=" + description
				+ "]";
	}
	public static void ajouter(Connection conn,ModeleCategorie p){
		String sql="insert into categorie(nom,description) values(?,?)";
		try {
			PreparedStatement pst=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

			pst.setString(1,p.nom );
			pst.setString(2,p.description);
			pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	public static void modifier(Connection conn,ModeleCategorie p){
		String sql="update categorie set nom='"+p.nom+"',description='"+p.description
				+"' where idCat='"+p.id+"'";
		try {
			Statement st=conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void supprimer(Connection conn, int parseInt) {
		String sql="delete from categorie where idCat='"+parseInt+"'";
		try {
			Statement st=conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
        
       public static ResultSet afficher(Connection conn ){

		ResultSet rs = null;
		String sql="select * from categorie";
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
