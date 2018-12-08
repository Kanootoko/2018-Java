package DAL.DAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAL.DBDriver.DBDriver;
import DAL.DTO.ProductDTO;
import DAL.SearchRequest.SearchRequest;

public class ProductDAO implements DAO<ProductDTO> {
	private final DBDriver db;
	private static final String tableName = "[Products]";
	public ProductDAO(DBDriver db) {
		this.db = db;
	}
	public void insert(ProductDTO productDTO) throws SQLException {
		db.execute(String.format("INSERT INTO %s (ProductName) VALUES ('%s')", tableName, productDTO.getName()));
	}
	public void update(ProductDTO oldProduct, ProductDTO newProduct) throws SQLException {
		db.execute(String.format("UPDATE %s SET ProductName = '%s' where ProductID = %d", tableName, newProduct.getName(), oldProduct.getID()));
	}
	public void delete(ProductDTO productDTO) throws SQLException {
		db.execute(String.format("DELETE FROM %s where ProductID = %s", tableName, productDTO.getID()));
	}
	public ProductDTO getOne(SearchRequest<ProductDTO> sr) throws SQLException {
		ResultSet rs = db.execute(String.format("SELECT * from %s%s", tableName, sr.whereString()));
		ProductDTO result = new ProductDTO(rs);
		try {
			rs.close();
		} catch (Exception ex) { }
		return result;
	}
	public ProductDTO[] get(SearchRequest<ProductDTO> sr) throws SQLException {
		ResultSet rs = db.execute(String.format("SELECT * from %s%s", tableName, sr.whereString()));
		List<ProductDTO> ans = new ArrayList<ProductDTO>();
		while (rs.next())
			ans.add(new ProductDTO(rs));
		return ans.toArray(new ProductDTO[0]);
	}
}