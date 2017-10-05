package Model.Admin;

public class Admin extends User{
	
	private int catID;
	public int getCatID() {
		return catID;
	}
	public void setCatID(int catID) {
		this.catID = catID;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public double getPassCrit() {
		return passCrit;
	}
	public void setPassCrit(double passCrit) {
		this.passCrit = passCrit;
	}
	public int getLevelID() {
		return levelID;
	}
	public void setLevelID(int levelID) {
		this.levelID = levelID;
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
	private  String topicName;
	private double passCrit;
	private int levelID;
	private int totQues;
	private int totPoints;
	private double duration;
}
