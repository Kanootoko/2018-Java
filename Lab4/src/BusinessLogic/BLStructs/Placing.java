package BusinessLogic.BLStructs;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import DAL.DTO.PlacingDTO;

public class Placing {
	PlacingDTO placingDTO;
	Product product;
	Shop shop;
	/* private */ public Placing(PlacingDTO placingDTO, Product product, Shop shop) {
		if (product == null || shop == null)
			throw new IllegalArgumentException("Shop or product cannot be null");
		this.product = product;
		this.shop = shop;
		this.placingDTO = placingDTO;
	}
	/*public Placing(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		if (rsmd.getColumnCount() != 4 || !rsmd.getColumnName(1).equals("ProductID") ||
		    !rsmd.getColumnName(2).equals("ShopID") || !rsmd.getColumnName(3).equals("Quantity") ||
		    !rsmd.getColumnName(4).equals("Price"))
			throw new SQLException("Error at Placing constructor from ResultSet");
		productID = new Integer(rs.getString(1));
		shopID = new Integer(rs.getString(2));
		quantity = new Integer(rs.getString(3));
		price = new Integer(rs.getString(4));
	}*/
	public Product getProduct() {
		return product;
	}
	public Shop getShop() {
		return shop;
	}
	public int getQuantity() {
		return placingDTO.getQuantity();
	}
	public int getPrice() {
		return placingDTO.getPrice();
	}
	public Placing setQuantity(int newQuantity) {
		placingDTO.setQuantity(newQuantity);
		return this;
	}
	public Placing setPrice(int newPrice) {
		placingDTO.setQuantity(newPrice);
		return this;
	}
	@Override
	public String toString() {
		return String.format("(Placing) Product [%s] at place [%s], %d pieces with price %d", product, shop, getQuantity(), getPrice());
	}
}
