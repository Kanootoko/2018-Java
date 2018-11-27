package Lab4Structs;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Products {
	private List<Product> products;
	public Products() {
		products = new ArrayList<Product>();
	}
	public int size() {
		return products.size();
	}
	public String getName(int productID) {
		for (Product product: products)
			if (product.getID() == productID)
				return product.getName();
		return null; // throw ?
	}
	public Integer getID(String productName) {
		for (Product product: products)
			if (product.getName().equals(productName))
				return product.getID();
		return null; // throw ?
	}
	public Product get(String productName) {
		for (Product product: products)
			if (product.getName().equals(productName))
				return product;
		return null; // throw ?
	}
	public Product get(int productID) {
		for (Product product: products)
			if (product.getID() == productID)
				return product;
		return null; // throw ?
	}
	public void add(Product product) {
		if (product != null)
			products.add(product);
	}
	public List<Product> getList() {
		return Collections.unmodifiableList(products);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Product product: products) {
			if (i > 0)
				sb.append("\n");
			sb.append(product.toString());
			++i;
		}
		return sb.toString();
	}
}
