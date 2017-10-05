package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Admin.QuestionTest;
import Model.Admin.Admin;
import Model.Connection.Connector;
import javafx.collections.ObservableList;

public class QuestionnaireDAO {
	Connector connObj = new Connector();
	private Statement statement = null;
	
	public void insertQuestionnaireRecords(Admin questionnaire,ObservableList<QuestionTest> questionSet) throws Exception{
	int quizID,quesID;
		/*	String insertQuestionnaire = "INSERT INTO quiztopic(topicTitle,categoryId,duration,noOfQuestions,passCriteria,quizLevelId,totalPoints)VALUES('"
										  +questionnaire.getTopicName()+"','"+questionnaire.getCatID()+"','"+questionnaire.getDuration()+"','"+questionnaire.getTotQues()+"','"+questionnaire.getPassCrit()
										  +"','"+questionnaire.getLevelID()+"','"+questionnaire.getTotPoints()+"')";*/
			
			String insertQuestionnaireDets ="INSERT INTO gsamba_quiztopic(topicTitle,categoryId,duration,noOfQuestions,passCriteria,quizLevelId,totalPoints)VALUES(?,?,?,?,?,?,?);";
			
			String insertQuestions = "Insert into gsamba_quizquestion(quizTopicId,questionText) Values(?,?)";
			String insertOptions = "Insert into gsamba_quizoption(questionId,optionText,isCorrect) values(?,?,?)";
			ResultSet generatedKeys = null;
			try (Connection myConn = (Connection) connObj.getConnectionObject()) {
			    myConn.setAutoCommit(false);
			    try (PreparedStatement myPs = myConn.prepareStatement(insertQuestionnaireDets,
			      Statement.RETURN_GENERATED_KEYS)) {
			        myPs.setString(1,questionnaire.getTopicName());
			        myPs.setInt(2, questionnaire.getCatID());
			        myPs.setDouble(3, questionnaire.getDuration());
			        myPs.setInt(4, questionnaire.getTotQues());
			        myPs.setDouble(5, questionnaire.getPassCrit());
			        myPs.setInt(6, questionnaire.getLevelID());
			        myPs.setInt(7, questionnaire.getTotPoints());
			        myPs.executeUpdate();

			        myConn.commit();
			        generatedKeys = myPs.getGeneratedKeys();
			        if (generatedKeys.next()) {
			            quizID = generatedKeys.getInt(1);
			        } else {
			            throw new SQLException("No generated quiz ID returned");
			        }
			    } finally {
			        if (generatedKeys != null) generatedKeys.close();
			    }
			    for(int i=0; i<questionSet.size();i++){
			    	 try (PreparedStatement myPs = myConn.prepareStatement(insertQuestions,
				    	      Statement.RETURN_GENERATED_KEYS)) {
				    	        myPs.setInt(1,quizID);
				    	        myPs.setString(2, questionSet.get(i).getQuestionTitle());
				    	        myPs.executeUpdate();

				    	        myConn.commit();
				    	        generatedKeys = myPs.getGeneratedKeys();
				    	        if (generatedKeys.next()) {
				    	        	quesID = generatedKeys.getInt(1);
				    	        } else {
				    	            throw new SQLException("No generated question ID returned");
				    	        }
				    	    } finally {
				    	        if (generatedKeys != null) generatedKeys.close();
				    	    }
			    	 String correctAns = questionSet.get(i).getCorrectAns();
			    	 
			    	 ArrayList<String> options = new ArrayList<>();
			    	 boolean isRight[];
			    	 if(correctAns == "A"){isRight= new boolean[]{true,false,false,false};}
			    	 else if(correctAns == "B"){isRight= new boolean[]{false,true,false,false};}
			    	 else if(correctAns == "C"){isRight= new boolean[]{false,false,true,false};}
			    	 else{isRight= new boolean[]{false,false,false,true};}
			    	 options.add(questionSet.get(i).getOption1Title());
			    	 options.add(questionSet.get(i).getOption2Title());
			    	 options.add(questionSet.get(i).getOption3Title());
			    	 options.add(questionSet.get(i).getOption4Title());
			    	 
			    	 	for(int j=0;j<4;j++){
			    	 		 try (PreparedStatement myPs = myConn.prepareStatement(insertOptions)) {
						    	        myPs.setInt(1,quesID);
						    	        myPs.setString(2, options.get(j));
						    	        myPs.setBoolean(3, isRight[j]);
						    	        myPs.executeUpdate();
						    	        myConn.commit();						    	        
						    	    } 
			    	 	}

			    }
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
public ResultSet getCategoryID(String categoryName) throws Exception{
		
		ResultSet resultSet = null;		
		try {
			statement = connObj.getConnectionObject().createStatement();
	        String query = "SELECT categoryId FROM gsamba_Category Where categoryName="+"'"+categoryName+"';";
			resultSet = statement.executeQuery(query);		
			
			
		//	statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}


public ResultSet getLevelID(String levelName) throws Exception{
	
	ResultSet resultSet = null;
	
	try {
		statement = connObj.getConnectionObject().createStatement();
        String query = "SELECT quizLevelId FROM gsamba_QuizLevel Where levelType="+"'"+levelName+"';"; 
		resultSet = statement.executeQuery(query);		
		
		
	//	statement.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return resultSet;
}
}
