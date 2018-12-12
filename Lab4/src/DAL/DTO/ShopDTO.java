package DAL.DTO;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import DAL.CSVFile.CSVFile;
import DAL.CSVFile.CSVException;

public class ShopDTO implements DTO {
	private int id;
	private String name, address;
	boolean isComplete;
	/* ShopDTO(int shopID, String shopName, String shopAddress) throws SQLException {
		if (shopID < 0 || shopName == null || shopAddress == null || shopName.equals("null") || shopAddress.equals("null"))
			throw new SQLException("ShopName and ShopAddress must not be null, ID must be >= 0");
		if (shopName.contains("'") || shopAddress.contains("'"))
			throw new SQLException("ShopName and ShopAddress cannot contain \"'\"");
		id = shopID;
		name = shopName;
		address = shopAddress;
		isComplete = true;
	} */
	public ShopDTO(String shopName, String shopAddress) throws SQLException {
		if (shopName == null || shopAddress == null || shopName.equals("null") || shopAddress.equals("null"))
			throw new SQLException("ShopName and ShopAddress must not be null, ID must be >= 0");
		if (shopName.contains("'") || shopAddress.contains("'") || shopName.contains("\"") || shopAddress.contains("\""))
			throw new SQLException("ShopName and ShopAddress cannot contain quotes");
		id = -1;
		name = shopName;
		address = shopAddress;
		isComplete = false;
	}
	public ShopDTO(ResultSet rs) throws SQLException {
		try {
			rs.getString("ShopID");
			rs.getString("ShopName");
			rs.getString("Address");
		} catch (SQLException ex) {
			if (ex.getMessage().equals("ResultSet closed"))
				throw ex;
			throw new SQLException("Error at ShopDTO constructor from ResultSet\n", ex);
		}
		id = new Integer(rs.getString("ShopID"));
		name = rs.getString("ShopName");
		address = rs.getString("Address");
		isComplete = true;
	}
	public ShopDTO(CSVFile csv) throws CSVException {
		if (csv.getColumns() != 2)
			throw new CSVException("Error at ShopDTO constructor from CSVParser: number of columns != 2");
		try {
			id = new Integer(csv.getString(1));
			name = csv.getString(2);
		} catch (NumberFormatException ex) {
			throw new CSVException("Bad CSV file: one of the columns (shopID) cannot be cast to Integer");
		}
	}
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public boolean isComplete() {
		return this.isComplete;
	}
	@Override
	public String toString() {
		return String.format("(Shop DTO) %d: %s; %s", id, name, address);
	}
}
