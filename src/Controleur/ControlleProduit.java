package Controleur;

import java.awt.Point;
import java.sql.*;

import javax.swing.JOptionPane;

import Modele.*;

public class ControlleProduit {
	public static void insertion(Connection conn,int code,int qte,double pv,String des,String  img,String cat){
		boolean b=true;
		//liste de controles
		if(code==0||qte==0||pv==0||des.equals("")||img.equals("")||cat.equals(""))
			{
				b=false;
				JOptionPane.showMessageDialog(null, "Saisir tout les Champs !!");

			}
		
	if(b){	
		ModeleProduit p=new ModeleProduit(code, qte, pv, des, img, cat);
		ModeleProduit.ajouter(conn,p);
	}
	}
	public static void modification(java.sql.Connection conn,int code,int qte,double pv,String des,String  img,String cat){
		boolean b=true;
		//liste de controles
		if(code==0||qte==0||pv==0||des.equals("")||img.equals("")||cat.equals(""))
			{
				b=false;
				JOptionPane.showMessageDialog(null, "Saisir tout les Champs !!");

			}
		
	if(b){
	ModeleProduit p=new ModeleProduit(code, qte, pv, des, img, cat);
	ModeleProduit.modifier(conn,p);}
	}
	public static void supression(com.mysql.jdbc.Connection conn, int parseInt) {
		JOptionPane jop=new JOptionPane();
		int option1 = jop.showConfirmDialog(null, "Voulez-vous supprimer le produit ?", "Suppression Produit", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(option1 == JOptionPane.YES_OPTION){
			ModeleProduit.supprimer(conn,parseInt);		
		}
	}
	
}
