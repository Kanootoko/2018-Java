package DAL.DBDriver;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DBDriver {
	public ResultSet execute(String queryString) throws SQLException;
}