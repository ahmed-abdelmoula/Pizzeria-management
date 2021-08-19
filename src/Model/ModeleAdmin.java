/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Yossra
 */
public class ModeleAdmin {
    private static Object p;
     String login;
	String motdepasse;
	public ModeleAdmin(String login,String motdepasse) {
		this.login=login;
                this.motdepasse=motdepasse; }


        
      
        
        

    public static Object getP() {
        return p;
    }

    public String getLogin() {
        return login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }
    
      public static void ajouter(Connection conn,ModeleAdmin p){
		
		
}
}