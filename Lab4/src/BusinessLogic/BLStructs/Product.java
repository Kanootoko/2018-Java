package BusinessLogic.BLStructs;

import java.sql.SQLException;

import DAL.DTO.ProductDTO;

public class Product {
	private ProductDTO productDTO;
	public Product(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	/*public Product(String productName) throws SQLException {
		if (productName == null || productName.equals("null"))
			throw new SQLException("ProductName must not be null, ID must be >= 0");
		id = -1;
		name = productName;
		isComplete = false;
	}*/
	public String getName() {
		return productDTO.getName();
	}
	@Override
	public String toString() {
		return String.format("(Product) %s", getName());
	}
}
