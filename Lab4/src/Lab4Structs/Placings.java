package Lab4Structs;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Placings {
	private List<Placing> placings;
	public Placings() {
		placings = new ArrayList<Placing>();
	}
	public int size() {
		return placings.size();
	}
	public Integer getQuantity(Product product, Shop shop) {
		for (Placing placing: placings)
			if (placing.getProductID() == product.getID() && placing.getShopID() == shop.getID())
				return placing.getQuantity();
		return null; // throw ?
	}
	public Integer getPrice(Product product, Shop shop) {
		for (Placing placing: placings)
			if (placing.getProductID() == product.getID() && placing.getShopID() == shop.getID())
				return placing.getPrice();
		return null; // throw ?
	}
	public Placing get(Product product, Shop shop) {
		for (Placing placing: placings)
			if (placing.getProductID() == product.getID() && placing.getShopID() == shop.getID())
				return placing;
		return null; // throw ?
	}
	public void add(Placing placing) {
		if (placing != null)
			placings.add(placing);
	}
	public List<Placing> getList() {
		return Collections.unmodifiableList(placings);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder() ;
		int i = 0;
		for (Placing placing: placings) {
			if (i > 0)
				sb.append("\n");
			sb.append(placing.toString());
			++i;
		}
		return sb.toString();
	}
}
