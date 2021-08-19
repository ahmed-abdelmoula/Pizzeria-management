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
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Yossra
 */
public class ModeleProduit {

   

    
    private int codeProd,qteStock;
	private double prixVente;
	private String designation,image,categorie;
	
	public ModeleProduit(int codeProd, int qteStock,
			double prixVente, String designation, String image,
			String categorie) {
		this.codeProd = codeProd;
		this.qteStock = qteStock;
		this.prixVente = prixVente;
		this.designation = designation;
		this.image = image;
		this.categorie = categorie;
	}
	
	public int getCodeProd() {
		return codeProd;
	}
	public void setCodeProd(int codeProd) {
		this.codeProd = codeProd;
	}
	public int getQteStock() {
		return qteStock;
	}
	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
	}
	public double getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
        public static void ajouter(Connection conn,ModeleProduit p){
		String sql="insert into produit(codeProd,designation,prixVente,image,categorie,qteStock) values(?,?,?,?,?,?)";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, p.getCodeProd());
			pst.setString(2,p.getDesignation() );
			pst.setDouble(3, p.getPrixVente());
			pst.setString(4,p.getImage());
			pst.setString(5,p.getCategorie());
			pst.setInt(6, p.getQteStock());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(" ERREUR "+e.getMessage());
		}	
		
	}
	public static void modifier(Connection conn,ModeleProduit p){
		String sql="update produit set designation='"+p.designation
				+"',prixVente='"+p.prixVente+"',image='"+p.image+"',categorie='"+p.categorie+
				"',qteStock='"+p.qteStock+"' where codeProd='"+p.codeProd+"'";
		try {
			Statement st=conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
       
        public static DefaultComboBoxModel remplirCat(Connection conn,DefaultComboBoxModel<String> mCat){

		mCat=new DefaultComboBoxModel<String>();
		try{
		String sql="Select * From categorie";
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
         public static DefaultComboBoxModel remplirPr(Connection conn,DefaultComboBoxModel<String> mCat){

		mCat=new DefaultComboBoxModel<String>();
		try{
		String sql="Select * From produit";
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
        
         public static DefaultComboBoxModel remplirPr2(Connection conn,DefaultComboBoxModel<String> mCat){

		mCat=new DefaultComboBoxModel<String>();
		try{
		String sql="Select * From produit";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next()){	
			String id=rs.getString(1);
                       
			mCat.addElement(id);
	}		
		rs.close();
		st.close();		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return mCat;
	}
        
        
        
        public static ResultSet afficher(Connection conn ){

		ResultSet rs = null;
		String sql="select * from produit";
		try{
			Statement st=conn.createStatement();
			rs=st.executeQuery(sql);
			
			//rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
        
        public static ArrayList<ModeleProduit> listprod(Connection conn){
            String req="select * from produit";
		ResultSet rs=null;
		ModeleProduit p;
		ArrayList<ModeleProduit> produits = new ArrayList<ModeleProduit>();
		
		try
		{
				Statement st=conn.createStatement();
				rs=st.executeQuery(req);
			//PreparedStatement pst=ConnexionBD.connect().prepareStatement(req);
			//rs= pst.executeQuery();
                              //  (int codeProd, int qteStock,double prixVente, String designation, String image,String categorie)
			while(rs.next())
			{    
                p = new ModeleProduit(
                        rs.getInt(1), (int) rs.getFloat(6),
                        rs.getDouble(3),
                        rs.getString(2),
                         rs.getString(4),
                         rs.getString(5)
);
                
                produits.add(p);
            }
		}
		catch (SQLException e)
		{

			e.printStackTrace();
		}
		
		return produits;
            
        }
}
