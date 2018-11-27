package BuisnessLogic;

import java.sql.SQLException;
import DBDriver.*;
import DAO.*;
import SearchRequest.*;

import DTO.*;

public class BL {
	private final DBDriver db;
	private final ShopDAO shops;
	public BL(String connectionType) throws SQLException, ClassNotFoundException {
		db = new SQLite("./SQLite/lab4.db");
		shops = new ShopDAO(db);
	}
	public BL() throws SQLException, ClassNotFoundException {
		this("SQLite");
	}
	public void showShops() throws SQLException {
		for (ShopDTO shop: shops.select(new ShopSearchRequest()))
			System.out.println(shop);
	}
}
