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
	public PlacingSearchRequest setPlacingID(Integer productID) {
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
		String ans = "where";
		if (productID != null)
			ans += " ProductID = " + productID;
		if (shopID != null)
			ans += " ProductID = " + shopID;
		if (quantity != null)
			ans += " Quantity = " + quantity;
		if (price != null)
			ans += " Price = " + price;
		return ans;
	}
}
