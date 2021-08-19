package Controlleur;

import java.sql.Connection;
import java.sql.Date;

import javax.swing.JOptionPane;


public class ControlleRecette {
	public static void insertion(Connection conn, java.util.Date d,int qte,int codeProd,int codePV){
		boolean b=true;

		if(qte==0||codeProd==0||codePV==0)
		{
			b=false;
			JOptionPane.showMessageDialog(null, "Saisir tout les Champs !!");

		}
	
if(b){
		//ModeleRecette p=new ModeleRecette(d, qte, codeProd, codePV);
		//ModeleRecette.enregistrer(conn,p);
}
	}

}
