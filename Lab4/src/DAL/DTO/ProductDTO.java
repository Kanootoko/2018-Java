package DAL.DTO;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ProductDTO implements DTO {
	private int id;
	private String name;
	private boolean isComplete;
	/*public ProductDTO(int productID, String productName) throws SQLException {
		if (productID < 0 || productName == null || productName.equals("null"))
			throw new SQLException("ProductName must not be null, ID must be >= 0");
		if (productName.contains("'"))
			throw new SQLException("ProductName cannot contain \"'\"");
		id = productID;
		name = productName;
		isComplete = true;
	}*/
	public ProductDTO(String productName) throws SQLException {
		if (productName == null || productName.equals("null"))
			throw new SQLException("ProductName must not be null, ID must be >= 0");
		if (productName.contains("'"))
			throw new SQLException("ProductName cannot contain \"'\"");
		id = -1;
		name = productName;
		isComplete = false;
	}
	public ProductDTO(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		/*if (rsmd.getColumnCount() != 2 || !rsmd.getColumnName(1).equals("ProductID") ||
		    !rsmd.getColumnName(2).equals("ProductName"))*/
		try {
			rs.getString("ProductID");
			rs.getString("ProductName");
		} catch (Exception ex) {
			throw new SQLException("Error at ProductDTO constructor from ResultSet\n" + ex);
		}
		id = new Integer(rs.getString("ProductID"));
		name = rs.getString("ProductName");
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

