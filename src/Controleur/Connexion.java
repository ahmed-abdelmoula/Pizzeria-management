package Controleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String URL_BD = "jdbc:mysql://localhost/patisserie";
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
			System.out.println("Non Connecté");
		}
		return conn;
	}

}
