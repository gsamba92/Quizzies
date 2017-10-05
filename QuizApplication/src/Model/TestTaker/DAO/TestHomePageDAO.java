package Model.TestTaker.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Connection.Connector;

public class TestHomePageDAO {
	Connector connObj = new Connector();
	private Statement statement = null;
	
public ResultSet getTestHomeResultSet(String catName) throws Exception{
		
	ResultSet testResultSet = null;
	
		try {
			statement = connObj.getConnectionObject().createStatement();
			String selectSummary = "select c.categoryId,l.quizLevelId,q.quizTopicId, q.topicTitle,q.passCriteria,l.levelType,q.noOfQuestions,q.totalPoints,q.duration from gsamba_quiztopic q inner join gsamba_category c on q.categoryId = c.categoryId inner join gsamba_quizlevel l on q.quizLevelId = l.quizLevelId where c.categoryName="+"'"+catName+"'";
			testResultSet = statement.executeQuery(selectSummary);				
			
		//	statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return testResultSet;	
}

}
