package DAL.DTO;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

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
		if (shopName.contains("'") || shopAddress.contains("'"))
			throw new SQLException("ShopName and ShopAddress cannot contain \"'\"");
		id = -1;
		name = shopName;
		address = shopAddress;
		isComplete = false;
	}
	public ShopDTO(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		/*if (rsmd.getColumnCount() != 3 || !rsmd.getColumnName(1).equals("ShopID") ||
		    !rsmd.getColumnName(2).equals("ShopName") || !rsmd.getColumnName(3).equals("Address"))*/
		try {
			rs.getString("ShopID");
			rs.getString("ShopName");
			rs.getString("Address");
		} catch (Exception ex) {
			throw new SQLException("Error at ShopDTO constructor from ResultSet\n" + ex);
		}
		id = new Integer(rs.getString("ShopID"));
		name = rs.getString("ShopName");
		address = rs.getString("Address");
		isComplete = true;
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
