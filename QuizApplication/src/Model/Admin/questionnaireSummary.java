package Model.Admin;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class questionnaireSummary {
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty catName;
	private final SimpleStringProperty topicName;
	private final SimpleDoubleProperty  passCrit;
	private final SimpleStringProperty level;
	private final SimpleIntegerProperty totQues;
	private final SimpleIntegerProperty totPoints;
	private final SimpleDoubleProperty duration;
	
	public questionnaireSummary(int id,String catName, String topicName, double passCrit, String level, int totQues,
		int totPoints, double duration) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.catName =  new SimpleStringProperty(catName);
		this.topicName = new SimpleStringProperty(topicName);
		this.passCrit = new SimpleDoubleProperty(passCrit);
		this.level = new SimpleStringProperty(level);
		this.totQues = new SimpleIntegerProperty(totQues);
		this.totPoints = new SimpleIntegerProperty(totPoints);
		this.duration = new SimpleDoubleProperty(duration);
	}

public int getId(){
	return id.get();
	}
	
	public String getCatName() {
		return catName.get();
	}

	public String getTopicName() {
		return topicName.get();
	}

	public double getPassCrit() {
		return passCrit.get();
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

	
		
}
