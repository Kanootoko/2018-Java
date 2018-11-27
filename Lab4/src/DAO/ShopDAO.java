package DAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.ShopDTO;
import DBDriver.DBDriver;
import SearchRequest.SearchRequest;

public class ShopDAO implements DAO<ShopDTO> {
	private final DBDriver db;
	private static final String tableName = "[Shops]";
	public ShopDAO(DBDriver db) {
		this.db = db;
	}
	public void insert(ShopDTO shopDTO) throws SQLException {
		db.execute(String.format("INSERT INTO %s (ShopName, ShopAddress) VALUES (%s, %s)", tableName, shopDTO.getName(), shopDTO.getAddress()));
	}
	public void update(ShopDTO oldShop, ShopDTO newShop) throws SQLException {
		db.execute(String.format("UPDATE %s SET ShopName = %s, ShopAddress = %s where ShopID = %d", tableName, newShop.getName(), newShop.getAddress(), oldShop.getName(), oldShop.getAddress()));
	}
	public void delete(ShopDTO shopDTO) throws SQLException {
		db.execute(String.format("DELETE FROM %s where ShopID = %s", tableName, shopDTO.getID()));
	}
	public ShopDTO selectOne(SearchRequest<ShopDTO> sr) throws SQLException {
		return new ShopDTO(db.execute(String.format("SELECT * from %s%s", tableName, sr.whereString())));
	}
	public ShopDTO[] select(SearchRequest<ShopDTO> sr) throws SQLException {
		ResultSet rs = db.execute(String.format("SELECT * from %s%s", tableName, sr.whereString()));
		List<ShopDTO> ans = new ArrayList<ShopDTO>();
		while (rs.next())
			ans.add(new ShopDTO(rs));
		return ans.toArray(new ShopDTO[0]);
	}
}