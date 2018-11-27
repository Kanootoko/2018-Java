package Lab4Structs;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Shops {
	private List<Shop> shops;
	public Shops() {
		shops = new ArrayList<Shop>();
	}
	public int size() {
		return shops.size();
	}
	public String getName(int shopID) {
		for (Shop shop: shops)
			if (shop.getID() == shopID)
				return shop.getName();
		return null; // throw ?
	}
	public Integer getID(String shopName, String address) {
		for (Shop shop: shops)
			if (shop.getName().equals(shopName) && shop.getAddress().equals(address))
				return shop.getID();
		return null; // throw ?
	}
	public Shop get(String shopName, String address) {
		for (Shop shop: shops)
			if (shop.getName().equals(shopName) && shop.getAddress().equals(address))
				return shop;
		return null; // throw ?
	}
	public Shop get(int shopID) {
		for (Shop shop: shops)
			if (shop.getID() == shopID)
				return shop;
		return null; // throw ?
	}
	public void add(Shop shop) {
		if (shop != null)
			shops.add(shop);
	}
	public List<Shop> getList() {
		return Collections.unmodifiableList(shops);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder() ;
		int i = 0;
		for (Shop shop: shops) {
			if (i > 0)
				sb.append("\n");
			sb.append(shop.toString());
			++i;
		}
		return sb.toString();
	}
}
