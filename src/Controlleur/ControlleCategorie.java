package Controlleur;

import Model.ModeleCategorie;
import java.sql.Connection;

import javax.swing.JOptionPane;




public class ControlleCategorie {

	public static void insertion(Connection conn,String nom,String desc){
		boolean b=true;

		if(nom.equals("")||desc.equals(""))
		{
			b=false;
			JOptionPane.showMessageDialog(null, "Saisir tout les Champs !!");

		}
	
if(b){
		ModeleCategorie p=new ModeleCategorie(nom, desc);
		ModeleCategorie.ajouter(conn,p);}
	}
	public static void modification(java.sql.Connection conn,int code,String nom,String  desc){
		boolean b=true;

		if(nom.equals("")||desc.equals(""))
		{
			b=false;
			JOptionPane.showMessageDialog(null, "Saisir tout les Champs !!");

		}
	
if(b){
		ModeleCategorie p=new ModeleCategorie(code, nom, desc);
		ModeleCategorie.modifier(conn,p);
              JOptionPane.showMessageDialog(null, "Modification Effectué!!");
}
	}
	
public static void supression(Connection conn, int id) {
		JOptionPane jop=new JOptionPane();
		int option1 = jop.showConfirmDialog(null, "Voulez-vous supprimer la catégorie ?", "Suppression catégorie", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(option1 == JOptionPane.OK_OPTION){
			ModeleCategorie.supprimer(conn, id);
		}
	
}
}
