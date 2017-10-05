package Controller.Admin;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Model.Admin.QuestionTest;
import Model.Admin.Admin;
import Model.DAO.QuestionnaireDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class QuestionViewController implements Initializable {

	
	@FXML 
	private Label test;
	@FXML 
	private Pagination pagination;
	@FXML
	private TextField questionTitle;
	@FXML
	private TextField opt1Title;
	@FXML
	private TextField opt2Title;
	@FXML
	private TextField opt3Title;
	@FXML
	private TextField opt4Title;
	@FXML
	private ChoiceBox<String> correctAns;
    @FXML
    private Button addNext;
    @FXML
    private Button publish;    
    
	int totalQuestions,count=1;
	ObservableList<QuestionTest> questionSet = FXCollections.observableArrayList();
	ArrayList<String> questionnaireDetails;
	
	QuestionTest q;
	 public void publishClicked(){
	    QuestionnaireDAO daoObj = new QuestionnaireDAO();
	    int catId = 0,levelId = 0;	    
	    try {
			ResultSet categoryResultSet = daoObj.getCategoryID(questionnaireDetails.get(0));
			ResultSet levelResultSet = daoObj.getLevelID( questionnaireDetails.get(6));
			while(categoryResultSet.next()){
			catId = categoryResultSet.getInt("categoryId");						
			}
			while(levelResultSet.next()){
			levelId = levelResultSet.getInt("quizLevelId");
			}
			Admin questionnaire = new Admin();
			questionnaire.setCatID(catId);
			questionnaire.setLevelID(levelId);
			questionnaire.setTopicName(questionnaireDetails.get(1));
			questionnaire.setPassCrit(Double.parseDouble( questionnaireDetails.get(2)));
			questionnaire.setTotQues(Integer.parseInt(questionnaireDetails.get(3)));
			questionnaire.setDuration(Double.parseDouble(questionnaireDetails.get(4)));
			questionnaire.setTotPoints(Integer.parseInt(questionnaireDetails.get(5)));
			daoObj.insertQuestionnaireRecords(questionnaire, questionSet);
			Alert a = new Alert(AlertType.INFORMATION); 
			a.setTitle("Published");		
			a.setContentText("Questionnaire is published you can view/edit it from home tab ");
			a.showAndWait();
			Stage stage = (Stage) publish.getScene().getWindow();
			stage.close();
			
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	 }
	  public void addNextClicked(){
		 
		 q = new QuestionTest();
  		 q.setOption1Title(opt1Title.getText());
  		 q.setOption2Title(opt2Title.getText());
  		 q.setOption3Title(opt3Title.getText());
  	     q.setOption4Title(opt4Title.getText());
		 q.setQuestionTitle(questionTitle.getText());
		 q.setCorrectAns(correctAns.getSelectionModel().getSelectedItem()); 
         questionSet.add(q);
    	 questionTitle.setText("");
    	 opt1Title.setText("");
   		 opt2Title.setText("");
   		 opt3Title.setText("");
   		 opt4Title.setText("");
   		 correctAns.getSelectionModel().clearSelection();
    	 test.setText("Question "+count+" Added");
  		 count++;  		
	    	    
	     if(totalQuestions==1){
	    	 
	       	addNext.setDisable(true);
	       	questionTitle.setDisable(true);
	       	opt1Title.setDisable(true);
	       	opt2Title.setDisable(true);
	       	opt3Title.setDisable(true);
	       	opt4Title.setDisable(true);
	       	correctAns.setDisable(true);
		    }	    		
	        totalQuestions--;			    	    	
	    }
		
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		populateCorrectAnswer();		 
	}
	
	public void populateCorrectAnswer(){
		ObservableList<String> optionsDropdown = FXCollections.observableArrayList();
		optionsDropdown.add("A");
		optionsDropdown.add("B");
		optionsDropdown.add("C");
		optionsDropdown.add("D");
		correctAns.setTooltip(new Tooltip("Select Option"));
		correctAns.setItems(optionsDropdown);
	
	}
	 public void setTotQuestions(ArrayList<String> name) {
		  questionnaireDetails = new ArrayList<>();
		  questionnaireDetails = name;
		  totalQuestions = Integer.parseInt(name.get(3));
				 
     }
	 


}
