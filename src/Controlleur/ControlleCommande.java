/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Model.ModelCommande;
import Model.ModeleRecette;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Yossra
 */
public class ControlleCommande {
    public static void insertion(Connection conn, java.util.Date d,int qte,int codeProd,int codePV){
		boolean b=true;

		if(qte==0||codeProd==0||codePV==0)
		{
			b=false;
			JOptionPane.showMessageDialog(null, "Saisir tout les Champs !!");

		}
	
if(b){
		ModelCommande p=new ModelCommande(d, qte, codeProd, codePV,qte);
		ModelCommande.enregistrer(conn, p);

}
	}
    
}
