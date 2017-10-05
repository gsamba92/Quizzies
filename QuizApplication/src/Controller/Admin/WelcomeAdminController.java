package Controller.Admin;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Model.DAO.CategoryDAO;
import Model.DAO.DifficultyDAO;
import Model.DAO.QuestionnaireSummaryDAO;
import Model.Admin.Category;
import Model.Admin.questionnaireSummary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WelcomeAdminController implements Initializable {
@FXML
private TableView<questionnaireSummary> summary;
@FXML  TableColumn<questionnaireSummary,String> category;
@FXML
private TableColumn<questionnaireSummary,String> topic;
@FXML
private TableColumn<questionnaireSummary,Double> passCriteria;
@FXML
private TableColumn<questionnaireSummary,String> level;
@FXML
private TableColumn<questionnaireSummary,Integer> totQuestions;
@FXML
private TableColumn<questionnaireSummary,Integer> totPoints;
@FXML
private TableColumn<questionnaireSummary,Double> duration;
@FXML
private Button delButton;
@FXML
private Button editQuesButton;
@FXML
private TextField editTopic;
@FXML
private TextField editPassCrit;
@FXML
private TextField editTotPoints;
@FXML
private TextField editDur;
@FXML
private Button saveEdit;
@FXML
//Add Questionnaire tab controls

private ChoiceBox<String> CatDropDown;
@FXML
private ChoiceBox<String> LevelDropDown;
@FXML
private TextField topicName;
@FXML
private TextField passingCriteria;
@FXML
private TextField noOfQuestions;
@FXML
private TextField durationTime;
@FXML
private Button addQuestions;
@FXML
private TextField totalPoints;
//Add Category tab controls
@FXML
private TextField categoryName;
@FXML
private Button saveCategory;
@FXML
private Button refresh;
@FXML
private Button logOut;

public void logOutClick(){
	Stage stage = (Stage) logOut.getScene().getWindow();
	stage.close();
}


public void saveEditClicked(){
	Alert a = new Alert(AlertType.INFORMATION);
	QuestionnaireSummaryDAO qs = new QuestionnaireSummaryDAO();
	ObservableList<questionnaireSummary> selected;
	selected = summary.getSelectionModel().getSelectedItems();	
	questionnaireSummary q = new questionnaireSummary(selected.get(0).getId(), selected.get(0).getCatName(), editTopic.getText(), Double.parseDouble(editPassCrit.getText()), selected.get(0).getLevel(), selected.get(0).getTotQues(), Integer.parseInt(editTotPoints.getText()), Double.parseDouble(editDur.getText()));
	try {
		qs.editQuestionnaireRecord(q);
		summary.setItems(getSummary());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	 a.setTitle("Updated");		
	 a.setContentText("Changes made to the questionnaire are updated");
	 a.showAndWait();
	 editQuesButton.setDisable(false);
	 editTopic.setDisable(true);
	 editPassCrit.setDisable(true);
	 editTotPoints.setDisable(true);
	 editDur.setDisable(true);
	 saveEdit.setDisable(true);
}
public void editQuesButtonClicked() throws Exception{
	
	editTopic.setDisable(false);
	editPassCrit.setDisable(false);
	editTotPoints.setDisable(false);
	editDur.setDisable(false);
	saveEdit.setDisable(false);
	editQuesButton.setDisable(true);
}

public void delButtonClicked(){
	ObservableList<questionnaireSummary> selected;
	QuestionnaireSummaryDAO qs = new QuestionnaireSummaryDAO();
	selected = summary.getSelectionModel().getSelectedItems();
	int id = selected.get(0).getId();
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Confirm Delete");
	alert.setHeaderText(null);
	alert.setContentText("Are you sure you want to delete this Questionnaire?");
	alert.showAndWait().ifPresent(response -> {
	     if (response == ButtonType.OK) {
	    	 try {
	    			qs.deleteQuestionnaireRecord(id);	    			
	    			summary.setItems(getSummary());
	    		} catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	     }});	
}

@Override
public void initialize(URL url, ResourceBundle rb) {
	// TODO Auto-generated method stub
	category.setCellValueFactory(new PropertyValueFactory<questionnaireSummary,String>("catName"));
	topic.setCellValueFactory(new PropertyValueFactory<questionnaireSummary,String>("topicName"));
	passCriteria.setCellValueFactory(new PropertyValueFactory<questionnaireSummary,Double>("passCrit"));
	level.setCellValueFactory(new PropertyValueFactory<questionnaireSummary,String>("level"));
	totQuestions.setCellValueFactory(new PropertyValueFactory<questionnaireSummary,Integer>("totQues"));
	totPoints.setCellValueFactory(new PropertyValueFactory<questionnaireSummary,Integer>("totPoints"));
	duration.setCellValueFactory(new PropertyValueFactory<questionnaireSummary,Double>("duration"));
	summary.setItems(getSummary());
	populateCategoryNames();
	populateLevels();	
	
}

public void refreshHome(){
	
	summary.setItems(getSummary());
}
public ObservableList<questionnaireSummary> getSummary(){
	ObservableList<questionnaireSummary> qSummary = FXCollections.observableArrayList();
	QuestionnaireSummaryDAO qs = new QuestionnaireSummaryDAO();
	questionnaireSummary q;
	try {
		ResultSet summaryResultSet = qs.getSummaryResultSet();
		while(summaryResultSet.next()){
			q =  new questionnaireSummary(summaryResultSet.getInt("quizTopicId"),summaryResultSet.getString("categoryName"), summaryResultSet.getString("topicTitle"), summaryResultSet.getDouble("passCriteria"), summaryResultSet.getString("levelType"), summaryResultSet.getInt("noOfQuestions"), summaryResultSet.getInt("totalPoints"), summaryResultSet.getDouble("duration"));
			qSummary.add(q);
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return qSummary;
}

public void populateCategoryNames(){
	ObservableList<Category> names = getCategoryNames();
	ObservableList<String> name =FXCollections.observableArrayList();
	
	for(int i =0 ; i< names.size(); i++)
	{ 
	  name.add(names.get(i).getCatName());
	}
	CatDropDown.setTooltip(new Tooltip("Select Category"));
	CatDropDown.setItems(name);
	
}
public ObservableList<Category> getCategoryNames(){
	CategoryDAO daoObj = new CategoryDAO();
	ArrayList<Category> categories = new ArrayList<Category>();
	try {
		ResultSet categoryResultSet = daoObj.getCategoryResultSet();
		Category c;
		while(categoryResultSet.next()){
		c = new Category();
		c.setId(categoryResultSet.getString("categoryId"));
		c.setCatName(categoryResultSet.getString("categoryName"));
		categories.add(c);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	ObservableList<Category> names = FXCollections.observableArrayList(categories);
	return names;
	
}
public void populateLevels(){
	ObservableList<DifficultyLevel> objs = getDifficultyLevels();
	ObservableList<String> names = FXCollections.observableArrayList();
	for(int i =0 ; i< objs.size(); i++)
	{ 
	    names.add(objs.get(i).getLevelType());
		
	}
	LevelDropDown.setTooltip(new Tooltip("Select Level of Difficulty"));
	LevelDropDown.setItems(names);
}
public ObservableList<DifficultyLevel> getDifficultyLevels(){
	ArrayList<DifficultyLevel> levels = new ArrayList<DifficultyLevel>();
	DifficultyDAO daoObj = new DifficultyDAO();
	try {
		ResultSet levelResultSet = daoObj.getLevelResultSet();
		DifficultyLevel dl;
		while(levelResultSet.next()){
		dl = new DifficultyLevel();
		dl.setLevelId(levelResultSet.getString("quizLevelId"));
		dl.setLevelType(levelResultSet.getString("levelType"));
		levels.add(dl);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ObservableList<DifficultyLevel> objs =  FXCollections.observableArrayList(levels);
	
	return objs;
	
}

public void addQuestionsClicked(ActionEvent e) {

	//pass all the questionnaire info
	ArrayList<String> questionnaireInfo = new ArrayList<>(); 
	questionnaireInfo.add(CatDropDown.getSelectionModel().getSelectedItem().toString());
	questionnaireInfo.add(topicName.getText());
	questionnaireInfo.add(passingCriteria.getText());
	questionnaireInfo.add(noOfQuestions.getText());
	questionnaireInfo.add(durationTime.getText());
	questionnaireInfo.add(totalPoints.getText());
	questionnaireInfo.add(LevelDropDown.getSelectionModel().getSelectedItem());
	
	//Navigate to add question view page
	FXMLLoader loader = new FXMLLoader();
	Stage primaryStage = new Stage();
	Pane root;
	try {
		root = loader.load(getClass().getResource("/View/Admin/QuestionView.fxml").openStream());
		QuestionViewController questionViewController = (QuestionViewController)loader.getController();
		questionViewController.setTotQuestions(questionnaireInfo);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}

public void saveCategoryClicked(){
	CategoryDAO daoObj = new CategoryDAO();
	Category catObj = new Category();
	catObj.setCatName(categoryName.getText());	
	try {
	
	Alert alert = new Alert(AlertType.WARNING);
	if(daoObj.alreadyExists(catObj.getCatName())){
		alert.setTitle("Attention");	
		alert.setContentText("Category Name already exists!");
		alert.showAndWait();
	}
	else
	{
		Alert a = new Alert(AlertType.INFORMATION);
		 daoObj.insertCategoryRecordTable(catObj.getCatName());
		 a.setTitle("Saved");		
		 a.setContentText("Category Name Added");
		 a.showAndWait();
	}
	populateCategoryNames();
	categoryName.setText("");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
