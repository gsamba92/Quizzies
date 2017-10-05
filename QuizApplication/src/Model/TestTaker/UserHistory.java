package Model.TestTaker;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserHistory {
	private final SimpleDoubleProperty score;
	private final SimpleStringProperty status;
	private final SimpleStringProperty date;
	private final SimpleStringProperty catName;
	private final SimpleStringProperty topicName;
	private final SimpleStringProperty levelName;
	private final SimpleIntegerProperty totPoints;
	private final SimpleDoubleProperty passCrit;

public UserHistory(double score,String status,String date,String catName,String topicName,String levelName,int totPoints,double passCrit){
	this.score = new SimpleDoubleProperty(score);
	this.status = new SimpleStringProperty(status) ;
	this.date = new SimpleStringProperty(date) ;
	this.catName = new SimpleStringProperty(catName) ;
	this.topicName = new SimpleStringProperty(topicName) ;
	this.levelName = new SimpleStringProperty(levelName) ;
	this.totPoints = new SimpleIntegerProperty(totPoints);
	this.passCrit = new SimpleDoubleProperty(passCrit);
}
	public double getScore() {
		return score.get();
	}
	
	public String getStatus() {
		return status.get();
	}
	
	public String getDate() {
		return date.get();
	}
	
	public String getCatName() {
		return catName.get();
	}
	
	public String getTopicName() {
		return topicName.get();
	}
	
	public String getLevelName() {
		return levelName.get();
	}
	
	public int getTotPoints() {
		return totPoints.get();
	}
	
	public double getPassCrit() {
		return passCrit.get();
	}	

}
