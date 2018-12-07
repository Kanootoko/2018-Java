package DAL.SearchRequest;

import DAL.DTO.ProductDTO;

public class ProductSearchRequest implements SearchRequest<ProductDTO>{
	private Integer id;
	private String name;
	public ProductSearchRequest() {
		id = null;
		name = null;
	}
	public ProductSearchRequest setID(Integer productID) {
		id = productID;
		return this;
	}
	public ProductSearchRequest setName(String productName) {
		name = productName;
		return this;
	}
	@Override
	public String whereString() {
		if (name == null && id == null)
			return "";
		String ans = "where";
		if (id != null)
			ans += " ProductID = \'" + id + "\'";
		if (name != null)
			ans += " ProductName = \'" + name + "\'";
		return ans;
	}
}
