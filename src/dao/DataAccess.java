package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.ResultSet;

public class DataAccess 
{
	public static DataAccess instance;

	private String pilote = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/miniprojetjee?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String user = "root";
	private String password = "";
	private Connection con=null;
	private Statement stm = null;

	public static DataAccess getInstance() {
		if (instance == null) 
		{
			instance =new DataAccess();
		}
		
		return instance;
	}

	private DataAccess() 
	{
		try 
		{
			Class.forName(pilote);
			System.out.println("pilote charger avec succes...");
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connexion avec succes...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   try {
			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									 ResultSet.CONCUR_UPDATABLE);
			System.out.println(" Statement create");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public  Connection connecter(){
		try {
			Class.forName(pilote);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, user, password);

	
	//		conn=DriverManager.getConnection(URL_BD,UTILISATEUR,MOT_PASS);
			System.out.println(con);
		//conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=");

			System.out.println("Connecté");
		} catch (SQLException ex) {
			System.out.println("Non Connecté"+ex.getMessage());
		}
		return con;
	}

	
	public ResultSet consulter (String sql)
	{
				 ResultSet rs=null;
				 try {
					 rs= stm.executeQuery(sql);
					System.out.println("passage de requete avec succes...");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
		 return rs;
		
	}

	public static void main(String[] args) 
	{
		

	}

	public void mettreAJour(String sql) {
		// TODO Auto-generated method stub
		
		try {
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
