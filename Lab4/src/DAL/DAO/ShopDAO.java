package DAL.DAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAL.DBDriver.DBDriver;
import DAL.DTO.ShopDTO;
import DAL.SearchRequest.SearchRequest;

public class ShopDAO implements DAO<ShopDTO> {
	private final DBDriver db;
	private static final String tableName = "[Shops]";
	public ShopDAO(DBDriver db) {
		this.db = db;
	}
	public void insert(ShopDTO shopDTO) throws SQLException {
		db.execute(String.format("INSERT INTO %s (ShopName, Address) VALUES ('%s', '%s')", tableName, shopDTO.getName(), shopDTO.getAddress()));
	}
	public void update(ShopDTO oldShop, ShopDTO newShop) throws SQLException {
		db.execute(String.format("UPDATE %s SET ShopName = '%s', Address = '%s' where ShopID = %d", tableName, newShop.getName(), newShop.getAddress(), oldShop.getName(), oldShop.getAddress()));
	}
	public void delete(ShopDTO shopDTO) throws SQLException {
		db.execute(String.format("DELETE FROM %s where ShopID = %s", tableName, shopDTO.getID()));
	}
	public ShopDTO getOne(SearchRequest<ShopDTO> sr) throws SQLException {
		ResultSet rs = db.execute(String.format("SELECT * from %s%s", tableName, sr.whereString()));
		ShopDTO result = new ShopDTO(rs);
		try {
			rs.close();
		} catch (Exception ex) { }
		return result;
	}
	public ShopDTO[] get(SearchRequest<ShopDTO> sr) throws SQLException {
		ResultSet rs = db.execute(String.format("SELECT * from %s%s", tableName, sr.whereString()));
		List<ShopDTO> ans = new ArrayList<ShopDTO>();
		while (rs.next())
			ans.add(new ShopDTO(rs));
		return ans.toArray(new ShopDTO[0]);
	}
}