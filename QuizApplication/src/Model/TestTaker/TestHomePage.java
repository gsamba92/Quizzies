package Model.TestTaker;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TestHomePage {

	private final SimpleStringProperty topicName;
	private final SimpleStringProperty level;
	private final SimpleIntegerProperty totQues;
	private final SimpleIntegerProperty totPoints;
	private final SimpleDoubleProperty duration;
	private final SimpleIntegerProperty id;
	private final SimpleIntegerProperty levelid;
	private final SimpleIntegerProperty catId;
	private final SimpleDoubleProperty passCrit;
	
	public TestHomePage(int id,String topicName,String level,int totQues,int totPoints, double duration,double passCrit,int levelid,int catId){
		this.id = new SimpleIntegerProperty(id);
		this.topicName = new SimpleStringProperty(topicName) ;
		this.level = new SimpleStringProperty(level);
		this.totQues = new SimpleIntegerProperty(totQues);
		this.totPoints = new SimpleIntegerProperty(totPoints);
		this.duration = new SimpleDoubleProperty(duration);	
		this.passCrit = new SimpleDoubleProperty(passCrit);
		this.levelid = new SimpleIntegerProperty(levelid);
		this.catId = new SimpleIntegerProperty(catId);
	}
	public double getPassCrit(){
		return passCrit.get();
	}
	
	public String getTopicName() {
		return topicName.get();
	}

	public String getLevel() {
		return level.get();
	}
	
	public int getTotQues() {
		return totQues.get();
	}

	public int getTotPoints() {
		return totPoints.get();
	}

	public double getDuration() {
		return duration.get();
	}

	public int getId() {
		return id.get();
	}
	public int getLevelId() {
		return levelid.get();
	}
	public int getCatId() {
		return catId.get();
	}
	
	
}
