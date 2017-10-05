package Model.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.Connection.Connector;

public class DifficultyDAO {
	Connector connObj = new Connector();
	private Statement statement = null;
	
public ResultSet getLevelResultSet() throws Exception{
		
		ResultSet levelResultSet = null;
		
		try {
			statement = connObj.getConnectionObject().createStatement();
			String query = "SELECT quizLevelId,levelType FROM gsamba_QuizLevel ORDER BY quizLevelId";
			levelResultSet = statement.executeQuery(query);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return levelResultSet;
	}

}
