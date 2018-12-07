package DAL.DAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAL.DBDriver.DBDriver;
import DAL.DTO.PlacingDTO;
import DAL.SearchRequest.SearchRequest;

public class PlacingDAO implements DAO<PlacingDTO> {
	private final DBDriver db;
	private static final String tableName = "[Placings]";
	public PlacingDAO(DBDriver db) {
		this.db = db;
	}
	public void insert(PlacingDTO placingDTO) throws SQLException {
		db.execute(String.format("INSERT INTO %s (ProductID, ShopID, Quantity, Price) VALUES (%d, %d, %d, %d)",
		           tableName, placingDTO.getProductID(), placingDTO.getShopID(), placingDTO.getQuantity(), placingDTO.getPrice()));
	}
	public void update(PlacingDTO oldPlacing, PlacingDTO newPlacing) throws SQLException {
		db.execute(String.format("UPDATE %s SET Quantity = %d, Price = %d where ProductID = %d and ShopID = %d",
		           tableName, newPlacing.getQuantity(), newPlacing.getPrice(), oldPlacing.getProductID(), oldPlacing.getShopID()));
	}
	public void delete(PlacingDTO placingDTO) throws SQLException {
		db.execute(String.format("DELETE FROM %s where ProductID = %d and ShopID = %d", tableName, placingDTO.getProductID(), placingDTO.getShopID()));
	}
	public PlacingDTO getOne(SearchRequest<PlacingDTO> sr) throws SQLException {
		return new PlacingDTO(db.execute(String.format("SELECT * from %s%s", tableName, sr.whereString())));
	}
	public PlacingDTO[] get(SearchRequest<PlacingDTO> sr) throws SQLException {
		ResultSet rs = db.execute(String.format("SELECT * from %s%s", tableName, sr.whereString()));
		List<PlacingDTO> ans = new ArrayList<PlacingDTO>();
		while (rs.next())
			ans.add(new PlacingDTO(rs));
		return ans.toArray(new PlacingDTO[0]);
	}
}