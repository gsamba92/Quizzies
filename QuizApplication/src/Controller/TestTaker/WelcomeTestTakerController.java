package Controller.TestTaker;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Model.Admin.Category;
import Model.DAO.CategoryDAO;
import Model.TestTaker.TestHomePage;
import Model.TestTaker.TestTaker;
import Model.TestTaker.UserHistory;
import Model.TestTaker.DAO.TestHomePageDAO;
import Model.TestTaker.DAO.UserHistoryDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WelcomeTestTakerController implements Initializable {
	@FXML 
	private Label firstName;
	@FXML
	private Button takeTest;
	@FXML
	private Button logOut;
	@FXML
	private ChoiceBox<String> CatDropDown;
	@FXML
	private TableView<UserHistory> userHistory;
	@FXML
	private TableColumn<UserHistory,String> date;
	@FXML
	private TableColumn<UserHistory,String> topicTitle;
	
	@FXML
	private TableColumn<UserHistory,String> category;
	
	@FXML
	private TableColumn<UserHistory,String> difficulty;
	
	@FXML
	private TableColumn<UserHistory,String> status;
	
	@FXML
	private TableColumn<UserHistory,Double> score;
	
	@FXML
	private TableColumn<UserHistory,Integer> totalPoints;
	
	@FXML
	private TableColumn<UserHistory,Double> passCriteria;
	
	@FXML
    private TableView<TestHomePage> summary; 
	@FXML
	private TableColumn<TestHomePage,String> topic;
	@FXML
	private TableColumn<TestHomePage,String> level;
	@FXML
	private TableColumn<TestHomePage,Integer> totQuestions;
	@FXML
	private TableColumn<TestHomePage,Integer> totPoints;
	@FXML
	private TableColumn<TestHomePage,Double> duration;
	@FXML
	private TableColumn<TestHomePage,Double> passCrit;
	@FXML
	private Button viewHistory;
	
	TestTaker user;
	
	public void viewHistoryClick(){
		
		ObservableList<UserHistory> historyView = FXCollections.observableArrayList();
		date.setCellValueFactory(new PropertyValueFactory<UserHistory,String>("date"));
		topicTitle.setCellValueFactory(new PropertyValueFactory<UserHistory,String>("topicName"));
		category.setCellValueFactory(new PropertyValueFactory<UserHistory,String>("catName"));
		difficulty.setCellValueFactory(new PropertyValueFactory<UserHistory,String>("levelName"));
		status.setCellValueFactory(new PropertyValueFactory<UserHistory,String>("status"));
		score.setCellValueFactory(new PropertyValueFactory<UserHistory,Double>("score"));
		totalPoints.setCellValueFactory(new PropertyValueFactory<UserHistory,Integer>("totPoints"));
		passCriteria.setCellValueFactory(new PropertyValueFactory<UserHistory,Double>("passCrit"));
		UserHistoryDAO uh = new UserHistoryDAO();
		try {
			ResultSet rs=uh.getHistoryResultSet(user.getUserId());
			UserHistory u;
			while(rs.next()){				
				u=  new UserHistory(rs.getDouble("score"), rs.getString("status"), rs.getString("testDate"), rs.getString("categoryName"), rs.getString("topicTitle"), rs.getString("levelType"), rs.getInt("totalPoints"), rs.getDouble("passCriteria"));
				historyView.add(u);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		userHistory.setItems(historyView);
		
	}
	
	public void setTestTakerId(TestTaker user){
		this.user = new TestTaker();
		this.user = user;
		firstName.setText(user.getFirstName());
		 
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		populateCategoryNames();
		
		CatDropDown.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override 
			public void changed(ObservableValue<? extends String> selected, String oldobj, String newobj) {
				
				ObservableList<TestHomePage> testView = FXCollections.observableArrayList();
				topic.setCellValueFactory(new PropertyValueFactory<TestHomePage,String>("topicName"));
				passCrit.setCellValueFactory(new PropertyValueFactory<TestHomePage,Double>("passCrit"));
				level.setCellValueFactory(new PropertyValueFactory<TestHomePage,String>("level"));
				totQuestions.setCellValueFactory(new PropertyValueFactory<TestHomePage,Integer>("totQues"));
				totPoints.setCellValueFactory(new PropertyValueFactory<TestHomePage,Integer>("totPoints"));
				duration.setCellValueFactory(new PropertyValueFactory<TestHomePage,Double>("duration"));
				TestHomePageDAO t = new TestHomePageDAO();
				try {
					ResultSet rs = t.getTestHomeResultSet(CatDropDown.getSelectionModel().getSelectedItem());
					TestHomePage test;
					while(rs.next()){
						test = new  TestHomePage(rs.getInt("quizTopicId"),rs.getString("topicTitle"),rs.getString("levelType"),rs.getInt("noOfQuestions"),rs.getInt("totalPoints"),rs.getDouble("duration"),rs.getDouble("passCriteria"),rs.getInt("quizLevelId"),rs.getInt("categoryId"));
						testView.add(test);
						
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			summary.setItems(testView);	
			}
		});
		
	}
	
	public void logOutClick(){
		Stage stage = (Stage) logOut.getScene().getWindow();
		stage.close();
		
	}
	public void takeTestClick() throws Exception{
		
		ObservableList<TestHomePage> selected;
		selected = summary.getSelectionModel().getSelectedItems();
		TestTaker sendInfo = new  TestTaker();
		sendInfo.setId(selected.get(0).getId()); 
		sendInfo.setTopicName(selected.get(0).getTopicName()); 
		sendInfo.setLevel(selected.get(0).getLevel()); 
		sendInfo.setTotQues(selected.get(0).getTotQues()); 
		sendInfo.setTotPoints(selected.get(0).getTotPoints());
		sendInfo.setDuration(selected.get(0).getDuration());
		sendInfo.setPassCrit(selected.get(0).getPassCrit());
		sendInfo.setLevelId(selected.get(0).getLevelId());
		sendInfo.setCatId(selected.get(0).getCatId());
		FXMLLoader loader = new FXMLLoader();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/View/TestTaker/TestView.fxml").openStream());
		TestViewController testViewController = (TestViewController)loader.getController();
		testViewController.setTestInfo(sendInfo,user.getUserId());					
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	public void populateCategoryNames(){
		ObservableList<Category> names = getCategoryNames();
		ObservableList<String> name =FXCollections.observableArrayList();;
		
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
		/**/
		ObservableList<Category> names = FXCollections.observableArrayList(categories);
		return names;
		
	}
}
