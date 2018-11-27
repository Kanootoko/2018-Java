package Lab4Structs;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import DAL.DTO.ShopDTO;

public class Shop {
	private Integer id;
	private String name, address;
	public Shop(ShopDTO shopDTO) throws SQLException {
		id = shopDTO.getID();
		name = shopDTO.getName();
		address = shopDTO.getAddress();
	}
	public Shop(String shopName, String shopAddress) throws SQLException {
		if (shopName == null || shopAddress == null || shopName.equals("null") || shopAddress.equals("null"))
			throw new SQLException("ShopName and ShopAddress must not be null, ID must be >= 0");
		id = -1;
		name = shopName;
		address = shopAddress;
		id = null;
	}
	public ShopDTO getDTO () {
		if (id == null) {
			return new ShopDTO(shopName, shopAddress);
		} else
			return new ShopDTO(id, shopName, shopAddress);
	}
	public Integer getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	@Override
	public String toString() {
		return String.format("(Shop) %d: %s; %s", id, name, address);
	}
}
