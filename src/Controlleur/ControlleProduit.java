/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Model.ModeleProduit;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Yossra
 */
public class ControlleProduit {
    public static void insertion(Connection conn,int code,int qte,double pv,String des,String  img,String cat){
        System.out.print("insertion");
        
		boolean b=true;
		//liste de controles
		if(code==0||qte==0||pv==0||des.equals("")||img.equals("")||cat.equals(""))
			{
				b=false;
				JOptionPane.showMessageDialog(null, "Saisir tout les Champs !!");

			}
		
	if(b){	
		ModeleProduit p=new ModeleProduit(code,qte,pv,des,img,cat);
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
	
	}


	


