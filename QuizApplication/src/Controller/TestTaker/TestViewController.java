package Controller.TestTaker;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.TestTaker.TestHomePage;
import Model.TestTaker.TestTaker;
import Model.TestTaker.UserQuestionTest;
import Model.TestTaker.DAO.TestViewDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestViewController implements Initializable {
@FXML
private Button submit;

@FXML
private Button next;
@FXML
private Label question;
@FXML
private Label topicName;
@FXML
private RadioButton option1;
@FXML
private RadioButton option2;
@FXML
private RadioButton option3;
@FXML
private RadioButton option4;

@FXML
private Button beginTest;
int totCount=0,i=0,userId;
ArrayList<String> userAnswerList = new ArrayList<>();
ArrayList<String> answerCode = new ArrayList<>();
String userAns;
public TestTaker questionnaire ; 
ObservableList<UserQuestionTest> uq = FXCollections.observableArrayList();
public void beginTestClick(){
	
	
	TestViewDAO obj = new TestViewDAO();
	int id = questionnaire.getId();
	try {
		
		uq= obj.getTestQuestionResultSet(id);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	option1.setDisable(false);
	option2.setDisable(false);
	option3.setDisable(false);
	option4.setDisable(false);
	
	totCount = uq.size()-1;
	if (totCount!=0) {
		next.setDisable(false);		
	}else submit.setDisable(false);
	System.out.println(totCount);
 question.setText(uq.get(0).getQuestionTitle());
 option1.setText(uq.get(0).getOption1Title());
 option2.setText(uq.get(0).getOption2Title());
 option3.setText(uq.get(0).getOption3Title());
 option4.setText(uq.get(0).getOption4Title()); 
	beginTest.setDisable(true);
}

public void nextClick(){
	
	if(option1.isSelected()){userAns="A";}if(option2.isSelected()){userAns="B";}if(option3.isSelected()){userAns="C";}if(option4.isSelected()){userAns="D";}
	userAnswerList.add(userAns);
	if (totCount>=1) {
		i++;
		question.setText(uq.get(i).getQuestionTitle());
		option1.setText(uq.get(i).getOption1Title());
		option2.setText(uq.get(i).getOption2Title());
		option3.setText(uq.get(i).getOption3Title());
		option4.setText(uq.get(i).getOption4Title());
	}
	if(totCount==1){
		next.setDisable(true);	
		submit.setDisable(false);
	}	
	 totCount--;		
}

public void setTestInfo(TestTaker info,int userId){
	questionnaire = new TestTaker();
	questionnaire = info;
	this.userId = userId;
	topicName.setText(questionnaire.getTopicName());
}

public void submitClick() throws Exception{
	double score = 0;
	double totPoints = questionnaire.getTotPoints();
	double totQues = questionnaire.getTotQues();
	double scorePerQues = totPoints/totQues;
	String status = "";
	if(option1.isSelected()){userAns="A";}if(option2.isSelected()){userAns="B";}if(option3.isSelected()){userAns="C";}if(option4.isSelected()){userAns="D";}
	userAnswerList.add(userAns);
	for(String u : userAnswerList){System.out.println("User"+u);}
	for(UserQuestionTest u : uq)
	{
		answerCode.add(u.getCorrectAns());System.out.println("Actual"+u.getCorrectAns());
	}
	
	for(int i=0;i<uq.size();i++){
		if(answerCode.get(i).equals(userAnswerList.get(i))){
		score += scorePerQues;	
		}		
	}
	if(score >= questionnaire.getPassCrit() ){
		status = "Pass";
	}
	else status ="Fail";
	System.out.println(score+" "+status);	
	submit.setDisable(true);
		FXMLLoader loader = new FXMLLoader();
	Stage primaryStage = new Stage();	
	Pane root = loader.load(getClass().getResource("/View/TestTaker/ResultSummary.fxml").openStream());
	ResultSummaryController resultSummaryController = (ResultSummaryController)loader.getController();
	resultSummaryController.setResultInfo(questionnaire,status,score,userId);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		Stage stage = (Stage) submit.getScene().getWindow();
		stage.close();

}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub


}

}
