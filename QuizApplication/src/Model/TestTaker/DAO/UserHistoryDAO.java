package Model.TestTaker.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Connection.Connector;

public class UserHistoryDAO {

	Connector connObj = new Connector();
	private Statement statement = null;
	
public ResultSet getHistoryResultSet(int userid) throws Exception{
		
	ResultSet historyResultSet = null;
	
		try {
			statement = connObj.getConnectionObject().createStatement();
			String query = "select h.score,h.status,h.testDate,l.levelType,c.categoryName,q.topicTitle,q.totalPoints,q.passCriteria from gsamba_userhistory h inner join gsamba_quiztopic q on q.quizTopicId = h.quizTopicId inner join gsamba_category c on h.categoryid = c.categoryid inner join gsamba_quizlevel l on h.quizlevelid = l.quizlevelid inner join gsamba_user u on u.userid = h.userid where u.userid ="+userid;
				    
			historyResultSet = statement.executeQuery(query);				
			
		//	statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return historyResultSet;	
}
	
	
}
