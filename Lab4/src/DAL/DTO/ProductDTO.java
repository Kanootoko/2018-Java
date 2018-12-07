package DAL.DTO;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ProductDTO implements DTO {
	private int id;
	private String name;
	private boolean isComplete;
	public ProductDTO(int productID, String productName) throws SQLException {
		if (productID < 0 || productName == null || productName.equals("null"))
			throw new SQLException("ProductName must not be null, ID must be >= 0");
		id = productID;
		name = productName;
		isComplete = true;
	}
	public ProductDTO(String productName) throws SQLException {
		if (productName == null || productName.equals("null"))
			throw new SQLException("ProductName must not be null, ID must be >= 0");
		id = -1;
		name = productName;
		isComplete = false;
	}
	public ProductDTO(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		if (rsmd.getColumnCount() != 2 || !rsmd.getColumnName(1).equals("ProductID") ||
		    !rsmd.getColumnName(2).equals("ProductName"))
			throw new SQLException("Error at Shop constructor from ResultSet");
		id = new Integer(rs.getString(1));
		name = rs.getString(2);
		isComplete = true;
	}
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public boolean isComplete() {
		return this.isComplete;
	}
	@Override
	public String toString() {
		return String.format("(Product DTO) %d: %s", id, name);
	}
}

