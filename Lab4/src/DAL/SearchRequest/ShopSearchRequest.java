package DAL.SearchRequest;

import DAL.DTO.ShopDTO;

public class ShopSearchRequest implements SearchRequest<ShopDTO>{
	private Integer id;
	private String name, address;
	public ShopSearchRequest() {
		id = null;
		name = null;
		address = null;
	}
	public ShopSearchRequest setID(Integer shopID) {
		id = shopID;
		return this;
	}
	public ShopSearchRequest setName(String shopName) throws IllegalArgumentException {
		if (shopName.contains("'"))
			throw new IllegalArgumentException("ProductName cannot contain \"'\"");
		name = shopName;
		return this;
	}
	public ShopSearchRequest setAddress(String shopAddress) throws IllegalArgumentException {
		if (shopAddress.contains("'"))
			throw new IllegalArgumentException("ProductName cannot contain \"'\"");
		address = shopAddress;
		return this;
	}
	@Override
	public String whereString() {
		if (name == null && address == null && id == null)
			return "";
		boolean first = false;
		String ans = " where";
		if (id != null) {
			ans += " ShopID = \'" + id + "\'";
			first = true;
		}
		if (name != null) {
			ans += (first ? " and" : "") + " ShopName = \'" + name + "\'";
			first = true;
		}
		if (address != null)
			ans += (first ? " and" : "") + " Address = \'" + address + "\'";
		return ans;
	}
}
