package DAL.DTO;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class PlacingDTO implements DTO {
	int productID, shopID, quantity, price;
	public PlacingDTO(int productID, int shopID, int quantity, int price) throws SQLException {
		if (price <= 0 || quantity < 0 || shopID < 0 || productID < 0)
			throw new SQLException("price <= 0 || quantity < 0 || shopID < 0 || productID < 0");
		this.productID = productID;
		this.shopID = shopID;
		this.quantity = quantity;
		this.price = price;
	}
	public PlacingDTO(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		if (rsmd.getColumnCount() != 4 || !rsmd.getColumnName(1).equals("ProductID") ||
		    !rsmd.getColumnName(2).equals("ShopID") || !rsmd.getColumnName(3).equals("Quantity") ||
		    !rsmd.getColumnName(4).equals("Price"))
			throw new SQLException("Error at PlacingDTO constructor from ResultSet");
		productID = new Integer(rs.getString(1));
		shopID = new Integer(rs.getString(2));
		quantity = new Integer(rs.getString(3));
		price = new Integer(rs.getString(4));
	}
	public int getProductID() {
		return productID;
	}
	public int getShopID() {
		return shopID;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getPrice() {
		return price;
	}
	public PlacingDTO setQuantity(int newQuantity) {
		quantity = newQuantity;
		return this;
	}
	public PlacingDTO setPrice(int newPrice) {
		quantity = newPrice;
		return this;
	}
	@Override
	public String toString() {
		return String.format("(Placing DTO) Product %d at place %d, %d pieces with price %d", productID, shopID, quantity, price);
	}
}