package Controller.Admin;

import java.sql.SQLException;

import Model.DAO.UserDAO;
import Model.TestTaker.TestTaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RegisterController {
	
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField rePassword;
	@FXML
	private Button register;
	@FXML
	private TextField userName;
	
	public void RegisterClick(ActionEvent event){
		Alert alert = new Alert(AlertType.WARNING);
		if(userName.getText().isEmpty()|| firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty() ||  password.getText().isEmpty() ||  rePassword.getText().isEmpty() ){			
		
			   alert.setTitle("Registeration Failed");
			   alert.setContentText("Please dont leave any fields empty");
			 
			 
		}
		else{		
			  if(!password.getText().equals(rePassword.getText())){alert.setContentText("Password is not matching!");  alert.showAndWait();} 
			  else{
				  UserDAO u = new UserDAO();
					TestTaker t = new TestTaker();
					t.setFirstName(firstName.getText());
					t.setLastName(lastName.getText());
					t.setUserEmail(email.getText());
					t.setUserPassword(password.getText());
					t.setUserName(userName.getText());
					try {
						u.register(t);
						Alert a = new Alert(AlertType.INFORMATION);
						 a.setTitle("Registered");		
						 a.setContentText("Congrats!You have been registered");
						 a.showAndWait();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Stage stage = (Stage) register.getScene().getWindow();
					stage.close();
			  }
			
		}
	}

}
