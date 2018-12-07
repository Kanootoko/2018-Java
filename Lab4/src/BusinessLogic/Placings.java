package BusinessLogic;

import java.sql.SQLException;

import DAL.DBDriver.*;
import DAL.DAO.PlacingDAO;
import DAL.DTO.PlacingDTO;
import BusinessLogic.BLStructs.Placing;

import DAL.SearchRequest.PlacingSearchRequest;
import DAL.SearchRequest.ProductSearchRequest;
import DAL.SearchRequest.ShopSearchRequest;

public class Placings {
	private final PlacingDAO placings;
	private final String connectionType;
	public Placings(String connectionType) throws BLException {
		this.connectionType = connectionType;
		DBDriver db;
		try {
			db = new SQLite("./SQLite/lab4.db");
		} catch (SQLException ex) {
			throw new BLException(ex);
		} catch (ClassNotFoundException ex) {
			throw new BLException("Class not found");
		} catch (Exception ex) {
			throw new BLException("Unknown exception: " + ex);
		}
		placings = new PlacingDAO(db);
	}
	public Placings() throws BLException {
		this("SQLite");
	}
	public Placing[] getPlacings() throws BLException {
		PlacingDTO[] DTOPlacings;
		Placing[] placings;
		try {
			DTOPlacings = this.placings.get(new PlacingSearchRequest());
		} catch (Exception ex) {
			throw new BLException(ex);
		}
		placings = new Placing[DTOPlacings.length];
		Shops shops = new Shops(connectionType);
		Products products = new Products(connectionType);
		for (int i = 0; i < DTOPlacings.length; ++i)
			placings[i] = new Placing(DTOPlacings[i],
			                          products.getOne(new ProductSearchRequest().setID(DTOPlacings[i].getProductID())),
			                          shops.getOne(new ShopSearchRequest().setID(DTOPlacings[i].getShopID()))
			                         );
		return placings;
	}
}
