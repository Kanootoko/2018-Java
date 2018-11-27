package SearchRequest;

import DTO.ShopDTO;

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
	public ShopSearchRequest setName(String shopName) {
		name = shopName;
		return this;
	}
	public ShopSearchRequest setAddress(String shopAddress) {
		address = shopAddress;
		return this;
	}
	@Override
	public String whereString() {
		if (name == null && address == null && id == null)
			return "";
		String ans = "where";
		if (id != null)
			ans += " ShopID = \'" + id + "\'";
		if (name != null)
			ans += " ShopName = \'" + name + "\'";
		if (address != null)
			ans += " Address = \'" + address + "\'";
		return ans;
	}
}
