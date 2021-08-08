package Controleur;

import java.sql.Connection;

import javax.swing.JOptionPane;

import Modele.ModeleFournir;

public class ControlleFournir {
	public static void insertion(Connection conn,String date,int qte,int codeProd,int codePV){
		boolean b=true;

		if(codeProd==0||qte==0||codePV==0||date.equals(""))
		{
			b=false;
			JOptionPane.showMessageDialog(null, "Saisir tout les Champs !!");

		}
	
if(b){
		ModeleFournir p=new ModeleFournir(date, qte, codeProd, codePV);
		ModeleFournir.enregistrer(conn,p);}
	}
	//public static void afficher(Connection conn,)

}
