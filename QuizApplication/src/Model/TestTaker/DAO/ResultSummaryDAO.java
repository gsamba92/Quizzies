package Model.TestTaker.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import Model.Connection.Connector;
import Model.TestTaker.ResultSummary;

public class ResultSummaryDAO {
	Connector connObj = new Connector();
	private Statement statement = null;
	public void insertUserHistory(ResultSummary obj){
		try (Connection myConn = (Connection) connObj.getConnectionObject()) {
		    myConn.setAutoCommit(false);
		    String query = "insert into gsamba_userhistory(userId,quizTopicId,testDate,score,status,quizLevelId,categoryId) values(?,?,?,?,?,?,?)";

		    String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));

		    PreparedStatement myPs = myConn.prepareStatement(query);
				
				        myPs.setInt(1,obj.getUserId());
				        myPs.setInt(2, obj.getQuizId());
				        myPs.setString(3,date);
				        myPs.setDouble(4, obj.getScore());
				        myPs.setString(5,obj.getStatus());
				        myPs.setInt(6,obj.getLevelId());
				        myPs.setInt(7,obj.getCatId());				     
				        myPs.executeUpdate();
				        myConn.commit();	
				   
		    myPs.close();

		}
		
		catch(SQLException e){
			
			
		}
	}
	
	
}
