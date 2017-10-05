package Model.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	public Connection getConnectionObject(){
		
		Connection connObj = null; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connObj = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/fp","dbfp","510");
		} 
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connObj;
		
	}

}
