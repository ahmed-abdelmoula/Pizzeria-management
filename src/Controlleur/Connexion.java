/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Yossra
 */
public class Connexion {
   
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String URL_BD = "jdbc:mysql://localhost/miniProjetJava?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static final String UTILISATEUR = "root";
	static final String MOT_PASS = "";
	private static Connection conn;

	
	
	public static Connection connecter(){
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(URL_BD,UTILISATEUR,MOT_PASS);
			System.out.println("Connecté");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return conn;
	}
}
