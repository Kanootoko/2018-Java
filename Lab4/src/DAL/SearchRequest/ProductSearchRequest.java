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
	public ProductSearchRequest setName(String productName) throws IllegalArgumentException {
		if (productName.contains("'"))
			throw new IllegalArgumentException("ProductName cannot contain \"'\"");
		name = productName;
		return this;
	}
	@Override
	public String whereString() {
		if (name == null && id == null)
			return "";
		boolean first = false;
		String ans = " where";
		if (id != null) {
			ans += " ProductID = \'" + id + "\'";
			first = true;
		}
		if (name != null)
			ans += (first ? " and" : "") + " ProductName = \'" + name + "\'";
		return ans;
	}
}
