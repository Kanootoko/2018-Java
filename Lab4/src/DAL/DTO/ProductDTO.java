package DAL.DTO;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import DAL.CSVFile.CSVFile;
import DAL.CSVFile.CSVException;

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
		if (productName.contains("'") || productName.contains("\""))
			throw new SQLException("ProductName cannot contain quotes");
		id = -1;
		name = productName;
		isComplete = false;
	}
	public ProductDTO(ResultSet rs) throws SQLException {
		try {
			rs.getString("ProductID");
			rs.getString("ProductName");
		} catch (Exception ex) {
			if (ex.getMessage().equals("ResultSet closed"))
				throw ex;
			throw new SQLException("Error at ProductDTO constructor from ResultSet\n", ex);
		}
		id = new Integer(rs.getString("ProductID"));
		name = rs.getString("ProductName");
		isComplete = true;
	}
	public ProductDTO(CSVFile csv) throws CSVException {
		if (csv.getColumns() != 2)
			throw new CSVException("Error at ProductDTO constructor from CSVParser: number of columns != 2");
		try {
			id = new Integer(csv.getString(1));
			name = csv.getString(2);
		} catch (NumberFormatException ex) {
			throw new CSVException("Bad CSV file: one of the columns (productID) cannot be cast to Integer");
		}
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

