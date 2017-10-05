package Model.TestTaker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import Model.Connection.Connector;
import Model.TestTaker.UserQuestionTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestViewDAO {

	Connector connObj = new Connector();
	
	
	
	public ObservableList<UserQuestionTest> getTestQuestionResultSet(int id) throws Exception{
		ObservableList<UserQuestionTest> list = FXCollections.observableArrayList();
		try (Connection myConn = (Connection) connObj.getConnectionObject()) {
		    myConn.setAutoCommit(false);
		    
		    ResultSet quesResultSet,optionResultSet;
			
			UserQuestionTest u;
			ArrayList<String> opts = null;
			ArrayList<Boolean> correctAns = null;
			
			String getQuestions = "select questionText,questionId from  gsamba_quizquestion where quiztopicid=?";
			String correct = "";
			String getOptions = "select optionid,optiontext,iscorrect from gsamba_quizoption where questionid=?";
			 try (PreparedStatement myPs = myConn.prepareStatement(getQuestions)) {
				  myPs.setInt(1,id);
			    quesResultSet =  myPs.executeQuery();
				while(quesResultSet.next()){
					int quesId = quesResultSet.getInt("questionId");
					
					u = new UserQuestionTest();
					u.setQuestionTitle(quesResultSet.getString("questionText"));
					try (PreparedStatement ps = myConn.prepareStatement(getOptions)) {
						ps.setInt(1,quesId);
						optionResultSet = ps.executeQuery();
						opts = new ArrayList<>();
						correctAns = new ArrayList<>();
						while(optionResultSet.next()){
							opts.add(optionResultSet.getString("optiontext"));					
							correctAns.add(optionResultSet.getBoolean("iscorrect"));
						}
						
						u.setOption1Title(opts.get(0));
						u.setOption2Title(opts.get(1));
						u.setOption3Title(opts.get(2));
						u.setOption4Title(opts.get(3));
					    if(correctAns.get(0)){ correct = "A";}else if(correctAns.get(1)){correct = "B";}else if(correctAns.get(2)){correct = "C";}else{correct = "D";}
						u.setCorrectAns(correct);
					    list.add(u);
					}
					/**/
				}
				 myConn.commit();
				       
				    }
		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return list;	
	}

	
}
