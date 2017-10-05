package Controller.Admin;

import Controller.TestTaker.WelcomeTestTakerController;
import Model.Admin.User;
import Model.DAO.UserDAO;
import Model.TestTaker.TestTaker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML 
	private TextField userName;
	@FXML
	private PasswordField passWord;
	@FXML
	private Button login;
	@FXML
	private Button register;
	
	public void loginClick() throws Exception{
		
		
		Alert alert = new Alert(AlertType.WARNING);
		if(userName.getText().isEmpty() || passWord.getText().isEmpty()){
			   alert.setTitle("Login Failed");
			   alert.setHeaderText(null);
			   alert.setContentText("Please dont leave any fields empty");
			   alert.showAndWait();
		}
		else
		{
			User user = new User();
			user.setUserName(userName.getText());
			user.setUserPassword(passWord.getText());
			UserDAO u = new UserDAO();
			user = u.login(user);
			
			if(user.getRoleId() == 1){
				
				FXMLLoader loader = new FXMLLoader();
				Stage primaryStage = new Stage();
				Pane root;
				root = loader.load(getClass().getResource("/View/Admin/WelcomeAdmin.fxml").openStream());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}else if(user.getRoleId() == 2){
				TestTaker t =  new TestTaker();
					t.setRoleId(user.getRoleId());t.setUserId(user.getUserId()); t.setFirstName(user.getFirstName());
				FXMLLoader loader = new FXMLLoader();
				Stage primaryStage = new Stage();
				Pane root;
				root = loader.load(getClass().getResource("/View/TestTaker/WelcomeTestTaker.fxml").openStream());
				WelcomeTestTakerController testTakerViewController = (WelcomeTestTakerController)loader.getController();
				testTakerViewController.setTestTakerId(t);
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			else{
				
				
				   alert.setTitle("Login Failed");
				   alert.setHeaderText(null);
				   alert.setContentText("Invalid User");
				   alert.showAndWait();
			}
			
		}
		userName.setText("");passWord.setText("");
	}
	
	
	public void registerClick() throws Exception{
		FXMLLoader loader = new FXMLLoader();
		Stage primaryStage = new Stage();
		Pane root;
		root = loader.load(getClass().getResource("/View/Admin/Register.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}


}
