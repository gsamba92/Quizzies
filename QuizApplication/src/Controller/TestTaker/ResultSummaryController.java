package Controller.TestTaker;

import java.net.URL;
import java.util.ResourceBundle;

import Model.TestTaker.ResultSummary;
import Model.TestTaker.TestTaker;
import Model.TestTaker.DAO.ResultSummaryDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ResultSummaryController implements Initializable {
  private int userId;
  TestTaker testTaker;
	@FXML
	private Label TopicName;
	@FXML
	private Label TotQues;
	@FXML
	private Label TotPoints;
	@FXML
	private Label Score;
	@FXML
	private Label Status;
	@FXML
	private Label passCrit;
	@FXML
	private Button ok;
	
	public void okClick(){
		ResultSummaryDAO rObj = new ResultSummaryDAO();
		ResultSummary r = new ResultSummary();
		r.setCatId(testTaker.getCatId());
		r.setLevelId(testTaker.getLevelId());
		r.setQuizId(testTaker.getId());
		r.setScore(Double.parseDouble(Score.getText()));
		r.setStatus(Status.getText());
		r.setUserId(userId);
		rObj.insertUserHistory(r);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Saved");
		alert.setHeaderText(null);		
		alert.setContentText("Test Results recorded successfully");
		alert.showAndWait();
		Stage stage = (Stage) ok.getScene().getWindow();
		stage.close();
	}
	
	public void setResultInfo(TestTaker info,String status,double score,int userId){
		TopicName.setText(info.getTopicName());
		TotQues.setText(String.valueOf(info.getTotQues()));
		TotPoints.setText(String.valueOf(info.getTotPoints()));
		Score.setText(String.valueOf(score));
		Status.setText(status);
		passCrit.setText(String.valueOf(info.getPassCrit()));
		testTaker = new TestTaker();
		testTaker = info;
		this.userId = userId;
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
