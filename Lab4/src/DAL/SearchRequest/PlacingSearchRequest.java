package DAL.SearchRequest;

import DAL.DTO.PlacingDTO;

public class PlacingSearchRequest implements SearchRequest<PlacingDTO>{
	private Integer productID, shopID, quantity, price;
	public PlacingSearchRequest() {
		productID = null;
		shopID = null;
		quantity = null;
		price = null;
	}
	public PlacingSearchRequest setProductID(Integer productID) {
		this.productID = productID;
		return this;
	}
	public PlacingSearchRequest setShopID(Integer shopID) {
		this.shopID = shopID;
		return this;
	}
	public PlacingSearchRequest setQuantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}
	public PlacingSearchRequest setPrice(Integer price) {
		this.price = price;
		return this;
	}
	@Override
	public String whereString() {
		if (productID == null && shopID == null && quantity == null && price == null)
			return "";
		boolean first = false;
		String ans = " where";
		if (shopID != null) {
			ans += " ProductID = " + shopID;
			first = true;
		}
		if (productID != null) {
			ans += (first ? " and" : "") + " ProductID = " + productID;
			first = true;
		}
		if (quantity != null) {
			ans += (first ? " and" : "") + " Quantity = " + quantity;
			first = true;
		}
		if (price != null)
			ans += (first ? " and" : "") + " Price = " + price;
		return ans;
	}
}
