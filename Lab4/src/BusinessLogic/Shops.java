package BusinessLogic;

import java.sql.SQLException;

import DAL.DBDriver.*;
import DAL.DAO.ShopDAO;
import DAL.DTO.ShopDTO;
import BusinessLogic.BLStructs.Shop;

import DAL.SearchRequest.ShopSearchRequest;
import DAL.SearchRequest.SearchRequest;

public class Shops {
	private final ShopDAO shops;
	private final String connectionType;
	public Shops(String connectionType) throws BLException {
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
		shops = new ShopDAO(db);
	}
	public Shops() throws BLException {
		this("SQLite");
	}
	/* default */ Shop getOne(SearchRequest<ShopDTO> sr) throws BLException {
		try {
			return new Shop(shops.getOne(sr));
		} catch (SQLException ex) {
			throw new BLException(ex);
		}
	}
	public Shop[] getShops() throws BLException {
		ShopDTO[] DTOShops;
		Shop[] shops;
		try {
			DTOShops = this.shops.get(new ShopSearchRequest());
		} catch (Exception ex) {
			throw new BLException(ex);
		}
		shops = new Shop[DTOShops.length];
		for (int i = 0; i < DTOShops.length; ++i)
			shops[i] = new Shop(DTOShops[i]);
		return shops;
	}
	public void addShop(String shopName, String shopAddress) throws BLException {
		try {
			shops.insert(new ShopDTO(shopName, shopAddress));
		} catch(Exception ex) {
			throw new BLException(ex);
		}
	}

}
