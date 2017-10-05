package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Admin.questionnaireSummary;
import Model.Connection.Connector;

public class QuestionnaireSummaryDAO {

	Connector connObj = new Connector();
	private Statement statement = null;
	
public ResultSet getSummaryResultSet() throws Exception{
		
	ResultSet summaryResultSet = null;
	
		try {
			statement = connObj.getConnectionObject().createStatement();
			String selectSummary = "select q.quizTopicId, c.categoryName,q.topicTitle,q.passCriteria,l.levelType,q.noOfQuestions,q.totalPoints,q.duration from gsamba_quiztopic q inner join gsamba_category c on q.categoryId = c.categoryId inner join gsamba_quizlevel l on q.quizLevelId = l.quizLevelId";
	        summaryResultSet = statement.executeQuery(selectSummary);				
			
		//	statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return summaryResultSet;	
}

public void editQuestionnaireRecord(questionnaireSummary quiz) throws Exception{
	
	try (Connection myConn = (Connection) connObj.getConnectionObject()) {
	    myConn.setAutoCommit(false);
	    String query = "update gsamba_quiztopic set  topicTitle = ?,duration= ?,passCriteria= ?,totalPoints=? where quizTopicId=?";
	    try (PreparedStatement myPs = myConn.prepareStatement(query))
			   {
			        myPs.setString(1,quiz.getTopicName());
			        myPs.setDouble(2, quiz.getDuration());
			        myPs.setDouble(3, quiz.getPassCrit());
			        myPs.setInt(4, quiz.getTotPoints());
			        myPs.setInt(5, quiz.getId());
			        myPs.executeUpdate();
			        myConn.commit();
			      
			    } 

	}
		
}
public void deleteQuestionnaireRecord(int quizId) throws Exception{
	try {
		statement = connObj.getConnectionObject().createStatement();
		String query =  null;
		query = "delete from gsamba_quiztopic where quizTopicId="+quizId;
		statement.executeUpdate(query);			
	
		System.out.println("Record deleted");
		statement.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

}
