package BusinessLogic;

import java.sql.SQLException;

import DAL.DBDriver.*;
import DAL.DAO.PlacingDAO;
import DAL.DTO.PlacingDTO;

import BusinessLogic.BLStructs.Placing;
import BusinessLogic.BLStructs.Shop;
import BusinessLogic.BLStructs.Product;

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
			throw new BLException("Class not found", ex);
		} catch (Exception ex) {
			throw new BLException("Unknown exception at Placings constructor", ex);
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
	public void addPlacing(Shop shop, Product product, int quantity, int price) throws BLException {
		if (shop == null || product == null)
			throw new BLException("Placing with null shop or product cannot be added");
		if (quantity < 1 || price <= 0)
			throw new BLException(String.format("Error while adding placing: quantity must be >0 (%d now), price must be >0 (%d now)", quantity, price));
		try {
			placings.insert(new PlacingDTO(shop.getDTO().getID(), product.getDTO().getID(), quantity, price));
		} catch (Exception ex) {
			throw new BLException(ex);
		}
	}
	public void addPlacing(Product product, Shop shop, int quantity, int price) throws BLException {
		addPlacing(shop, product, quantity, price);
	}
	public Placing getPlacing(Shop shop, Product product) throws BLException {
		if (shop == null || product == null)
			throw new BLException("No placing can exist with shop == null or product == null");
		try {
			return new Placing(placings.getOne(new PlacingSearchRequest().setShopID(shop.getDTO().getID()).setProductID(product.getDTO().getID())), product, shop);
		} catch (Exception ex) {
			throw new BLException(ex);
		}
	}
	public Placing getPlacing(Product product, Shop shop) throws BLException {
		return getPlacing(shop, product);
	}
	public void deletePlacing(Placing placing) throws BLException {
		try {
			placings.delete(placing.getDTO());
		} catch (Exception ex) {
			throw new BLException(ex);
		}
	}
}
