/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Model.ModelPointDeVente;
import Model.ModeleCategorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Yossra
 */
public class ControlleurPointDeVente {
    public static  void insertion(Connection conn,String adresse){
		boolean b=true;

		if(adresse.equals(""))
		{
			b=false;
			JOptionPane.showMessageDialog(null, "Champs Vide  !!");

		}
	
if(b){
		ModelPointDeVente p=new ModelPointDeVente(adresse);
                ModelPointDeVente.ajouter(conn, p);
  
		}
	}

    
}
