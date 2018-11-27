package Lab4Structs;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import DAL.DTO.ShopDTO;

public class Shop_old {
	private int id;
	private String name, address;
	boolean isComplete;
	/* default */ public Shop(int shopID, String shopName, String shopAddress) throws SQLException {
		if (shopID < 0 || shopName == null || shopAddress == null || shopName.equals("null") || shopAddress.equals("null"))
			throw new SQLException("ShopName and ShopAddress must not be null, ID must be >= 0");
		id = shopID;
		name = shopName;
		address = shopAddress;
		isComplete = true;
	}
	public Shop(String shopName, String shopAddress) throws SQLException {
		if (shopName == null || shopAddress == null || shopName.equals("null") || shopAddress.equals("null"))
			throw new SQLException("ShopName and ShopAddress must not be null, ID must be >= 0");
		id = -1;
		name = shopName;
		address = shopAddress;
		isComplete = false;
	}
	public Shop(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		if (rsmd.getColumnCount() != 3 || !rsmd.getColumnName(1).equals("ShopID") ||
		    !rsmd.getColumnName(2).equals("ShopName") || !rsmd.getColumnName(3).equals("Address"))
			throw new SQLException("Error at Shop constructor from ResultSet");
		id = new Integer(rs.getString(1));
		name = rs.getString(2);
		address = rs.getString(3);
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
		return String.format("%d: %s; %s", id, name, address);
	}
}
