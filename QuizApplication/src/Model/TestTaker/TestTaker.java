package Model.TestTaker;

import Model.Admin.User;

public class TestTaker extends User {
	private double passCrit;
	private int catId;
	private String topicName;
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getTotQues() {
		return totQues;
	}
	public void setTotQues(int totQues) {
		this.totQues = totQues;
	}
	public int getTotPoints() {
		return totPoints;
	}
	public void setTotPoints(int totPoints) {
		this.totPoints = totPoints;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPassCrit() {
		return passCrit;
	}
	public void setPassCrit(double passCrit) {
		this.passCrit = passCrit;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	private String level;
	private int totQues;
	private int totPoints;
	private double duration;
	private int id;
	private int levelId;
}
