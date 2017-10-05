package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Admin.User;
import Model.Connection.Connector;
import Model.TestTaker.TestTaker;

public class UserDAO {
	
	Connector connObj = new Connector();
//	private Statement statement = null;
	
	public void register(TestTaker t) throws SQLException{
		try (Connection myConn = (Connection) connObj.getConnectionObject()) {
		    myConn.setAutoCommit(false);
		    String query = "insert into gsamba_user(roleid,firstName,lastName,userEmail,userPassword,userName) values (?,?,?,?,?,?)";
		    try (PreparedStatement myPs = myConn.prepareStatement(query))
				   {
				        myPs.setInt(1,2);
				        myPs.setString(2,t.getFirstName());
				        myPs.setString(3,t.getLastName());
				        myPs.setString(4,t.getUserEmail());
				        myPs.setString(5,t.getUserPassword());
				        myPs.setString(6,t.getUserName());
				        myPs.executeUpdate();
				        myConn.commit();
				      
				      
				    } 

		}
	}	
	public User login(User user) throws SQLException{
		
ResultSet userResultSet = null;
//boolean flag = false;
int i =0,id=0;
		
try (Connection myConn = (Connection) connObj.getConnectionObject()) {
    myConn.setAutoCommit(false);
    String query = "select roleid,userid,firstName,userName,userPassword from gsamba_user where userName =? and userPassword=?";
    try (PreparedStatement myPs = myConn.prepareStatement(query))
		   {
		        myPs.setString(1,user.getUserName());
		        myPs.setString(2, user.getUserPassword());
		      
		        userResultSet = myPs.executeQuery();
		        myConn.commit();
		    	
		      				if(userResultSet.next()){
					user.setRoleId(userResultSet.getInt("roleid"));	
					user.setUserId(userResultSet.getInt("userid"));
					user.setFirstName(userResultSet.getString("firstName"));
		    } 
				

}

		}
return user;}
}
