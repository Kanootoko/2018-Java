package DAL.DAO;

import java.util.List;
import java.util.ArrayList;

import DAL.CSVFile.CSVFile;
import DAL.CSVFile.CSVException;
import DAL.DTO.PlacingDTO;
import DAL.SearchRequest.SearchRequest;

public class PlacingDAOCSV implements DAO<PlacingDTO> {
	private final CSVFile csv;
	public PlacingDAOCSV(CSVFile csv) {
		this.csv = csv;
	}
	public void insert(PlacingDTO placingDTO) throws SQLException {
		csv.rewind();
		while (csv.hasNext()) {
			PlacingDTO tmp = new PlacingDTO(csv);
			if (tmp.getProductID == placingDTO.getProductID() && tmp.getShopID == placingDTO.getShopID())
				throw new SQLException("There is already (placings, shop) pair in db");
		}
		csv.insert(String[] {placingDTO.getProductID().toString(), placingDTO.getShopID().toString(),
		                     placingDTO.getQuantity().toString(), placingDTO.getPrice().toString()});
	}
	public void update(PlacingDTO oldPlacing, PlacingDTO newPlacing) throws SQLException {
		csv.rewind();
		while (csv.hasNext()) {
			PlacingDTO tmp = new PlacingDTO(csv);
			if (tmp.getProductID == placingDTO.getProductID() && tmp.getShopID == placingDTO.getShopID())
				csv.update(String[] {placingDTO.getProductID().toString(), placingDTO.getShopID().toString(),
		                             placingDTO.getQuantity().toString(), placingDTO.getPrice().toString()});
		}
	}
	public void delete(PlacingDTO placingDTO) throws SQLException {
		csv.rewind();
		while (csv.hasNext()) {
			PlacingDTO tmp = new PlacingDTO(csv);
			if (tmp.getProductID == placingDTO.getProductID() && tmp.getShopID == placingDTO.getShopID())
				csv.delete();
		}
		db.execute(String.format("DELETE FROM %s where ProductID = %d and ShopID = %d", tableName, placingDTO.getProductID(), placingDTO.getShopID()));
	}
	public PlacingDTO getOne(SearchRequest<PlacingDTO> sr) throws SQLException {
		ResultSet rs = db.execute(String.format("SELECT * from %s%s", tableName, sr.whereString()));
		PlacingDTO result;
		try {
			result = new PlacingDTO(rs);
		} catch (SQLException ex) {
			if (ex.getMessage().equals("ResultSet closed"))
				throw new SQLException("SELECT is empty");
			else
				throw ex;
		}
		try {
			rs.close();
		} catch (Exception ex) { }
		return result;
	}
	public PlacingDTO[] get(SearchRequest<PlacingDTO> sr) throws SQLException {
		ResultSet rs = db.execute(String.format("SELECT * from %s%s", tableName, sr.whereString()));
		List<PlacingDTO> ans = new ArrayList<PlacingDTO>();
		while (rs.next())
			ans.add(new PlacingDTO(rs));
		return ans.toArray(new PlacingDTO[0]);
	}
}