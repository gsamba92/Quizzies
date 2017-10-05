package Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.Connection.Connector;

public class CategoryDAO {
	
	Connector connObj = new Connector();
	private Statement statement = null;
	
	public boolean alreadyExists(String catName){
		boolean flag = false;
		
		try {
			statement = connObj.getConnectionObject().createStatement();
			String query =  null;
			query = "select categoryName from gsamba_category";
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				if(rs.getString("categoryName").equalsIgnoreCase(catName))
					{flag = true;break;}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return flag;
	}
	public void insertCategoryRecordTable(String catName) throws Exception{
		
		
		try {
			
			statement = connObj.getConnectionObject().createStatement();
			String query =  null;	
			
				query = "INSERT INTO gsamba_Category(categoryName) VALUES('"+catName+"')";
				statement.executeUpdate(query);			
			
				System.out.println("Records inserted");
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	public ResultSet getCategoryResultSet() throws Exception{
		
		ResultSet categoryResultSet = null;
		
		try {
			statement = connObj.getConnectionObject().createStatement();
			String query = "SELECT categoryId,categoryName FROM gsamba_Category ORDER BY categoryId";
			categoryResultSet = statement.executeQuery(query);
			
			
		//	statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryResultSet;
	}
}
