package Controller.Admin;

import Model.Admin.IDifficultyLevel;

public class DifficultyLevel implements IDifficultyLevel {
	private String levelId;
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	private String levelType;
	@Override
	public void setDifficulty(String level) {
		// TODO Auto-generated method stub
		this.levelType = level;
	}
	


}
