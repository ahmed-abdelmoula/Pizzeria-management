
package Controlleur;

import Model.ModeleAdmin;
import Model.ModeleProduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Yossra
 */
public class ControlleAdmin {
    
     public static void insertion(Connection conn,String  login,String motdepasse){
		boolean b=true;
		//liste de controles
		if(login.equals("")||motdepasse.equals(""))
			{
				b=false;
				JOptionPane.showMessageDialog(null, "Saisir tout les Champs !!");

			}
		
	if(b){	
		ModeleAdmin p=new ModeleAdmin(login,motdepasse);
			String sql="insert into admin(login,pass) values(?,?)";
			try {
				
				PreparedStatement pst=conn.prepareStatement(sql);
			        pst.setString(1,p.getMotdepasse());
				pst.setString(2,p.getLogin());
				
				pst.executeUpdate();
			} 
			catch (SQLException e) {
System.out.println(e.getMessage());
			}	
             
	
	}
     }
     
    
}
