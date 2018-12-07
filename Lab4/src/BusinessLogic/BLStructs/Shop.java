package BusinessLogic.BLStructs;

import DAL.DTO.ShopDTO;

public class Shop {
	ShopDTO shopDTO;
	private String name, address;
	public Shop(ShopDTO shopDTO) {
		this.shopDTO = shopDTO;
	}
	/*public Shop(String shopName, String shopAddress) throws SQLException {
		if (shopName == null || shopAddress == null || shopName.equals("null") || shopAddress.equals("null"))
			throw new SQLException("ShopName and ShopAddress must not be null, ID must be >= 0");
		shopDTO = new ShopDTO(shopName, shopAddress);
	}*/
	public String getName() {
		return shopDTO.getName();
	}
	public String getAddress() {
		return shopDTO.getAddress();
	}
	@Override
	public String toString() {
		return String.format("(Shop) %s; %s", getName(), getAddress());
	}
}
