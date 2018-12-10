package BusinessLogic;

import java.sql.SQLException;

import DAL.DBDriver.*;
import DAL.DAO.ProductDAO;
import DAL.DTO.ProductDTO;
import BusinessLogic.BLStructs.Product;

import DAL.SearchRequest.ProductSearchRequest;
import DAL.SearchRequest.SearchRequest;

public class Products {
	private final ProductDAO products;
	private final String connectionType;
	public Products(String connectionType) throws BLException {
		this.connectionType = connectionType;
		DBDriver db;
		try {
			db = new SQLite("./SQLite/lab4.db");
		} catch (SQLException ex) {
			throw new BLException(ex);
		} catch (ClassNotFoundException ex) {
			throw new BLException("Class not found");
		} catch (Exception ex) {
			throw new BLException("Unknown exception at Products constructor", ex);
		}
		products = new ProductDAO(db);
		
	}
	public Products() throws BLException {
		this("SQLite");
	}
	/* default */ Product getOne(SearchRequest<ProductDTO> sr) throws BLException {
		try {
			return new Product(products.getOne(sr));
		} catch (SQLException ex) {
			if (ex.getMessage().equals("SELECT is empty"))
				throw new BLException("No such product: " + sr);
			throw new BLException(ex);
		}
	}
	public Product[] getProducts() throws BLException {
		ProductDTO[] DTOProducts;
		Product[] products;
		try {
			DTOProducts = this.products.get(new ProductSearchRequest());
		} catch (Exception ex) {
			throw new BLException(ex);
		}
		products = new Product[DTOProducts.length];
		for (int i = 0; i < DTOProducts.length; ++i)
			products[i] = new Product(DTOProducts[i]);
		return products;
	}
	public void addProduct(String productName) throws BLException {
		try {
			products.insert(new ProductDTO(productName));
		} catch (Exception ex) {
			throw new BLException(ex);
		}
	}
	public Product getProduct(String productName) throws BLException {
		try {
			return new Product(products.getOne(new ProductSearchRequest().setName(productName)));
		} catch (SQLException ex) {
			if (ex.getMessage().equals("SELECT is empty"))
				throw new BLException("No such product: " + productName);
			throw new BLException(ex);
		} catch (Exception ex) {
			throw new BLException(ex);
		}
	}
	public void deleteProduct(Product product) throws BLException {
		try {
			products.delete(product.getDTO());
		} catch (Exception ex) {
			throw new BLException(ex);
		}
	}
}
