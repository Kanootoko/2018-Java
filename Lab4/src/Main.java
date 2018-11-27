import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

//import DAL.*;
//import Lab4Structs.*;
import Utils.Utils;

import BuisnessLogic.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BL lab4 = new BL();
		lab4.showShops();
		/*Lab4DAL_old db = new Lab4DAL_old("C:\\sqlite\\Lab4.db");
		while (true) {
			System.out.println("\n\tHelp:");
			System.out.println("\"add shop <ShopName> <Address>\" - add shop to database");
			System.out.println("\"add product <ProductName>\" - add product to database");
			System.out.println("\"export <ProductName> <ShopName> <ShopAddress> <Quantity> <Price>\" - export (set quantity and proce) product to shop");
			System.out.println("\"export <ProductID> <ShopID> <Quantity> <Price>\" - export (set quantity and price) product to shop");
			System.out.println("\"show shops\" - prints all shops in database");
			System.out.println("\"show products\" - prints all products in database");
			System.out.println("\"show placings\" - prints all placings in database");
			System.out.println("\"show placings formatted\" - prints all shops in database with shop/product names instaed of IDs");
			System.out.print(">>> ");
			String[] command = Utils.parseQuotes(System.console().readLine());
			if (command.length > 0 && (command[0].equals("quit") || command[0].equals("exit")))
				break;
			if (command.length > 0 && command[0].equals("add")) {
				if (command.length == 4 && command[1].equals("shop")) {
					db.addShop(new Shop(command[2], command[3]));
					System.out.println("Shop added");
				} else if (command.length == 3 && command[1].equals("product")) {
					db.addProduct(new Product(command[2]));
					System.out.println("Product added");
				} else {
					System.out.println(String.format("Error with adding. Full command: \"%s\"", String.join("; ", command)));
				}
			} else if (command.length > 0 && command[0].equals("export")) {
				if (command.length == 6) {
					System.out.println("Placing added");
				} else if (command.length == 5) {
					try {
						db.addPlacing(new Placing(new Integer(command[1]), new Integer(command[2]), new Integer(command[3]), new Integer(command[4])));
						System.out.println("Placing added");
					} catch (IndexOutOfBoundsException ex) {
						System.out.println(ex.getMessage());
					} catch (NumberFormatException ex) {
						System.out.println("IDs, quantity and price must be integers");
					}
				} else {
					System.out.println(String.format("Error with export command: you need to type 5 or 6 arguments. Entered: \"%s\"", String.join("; ", command)));
				}
			} else if (command.length > 0 && command[0].equals("show")) {
				if (command.length > 1 && command[1].equals("shops")) {
					Shops shops = db.getShops();
					System.out.println(shops.size() + " shops" + (shops.size() == 0 ? "." : ":"));
					System.out.println(shops);
				} else if (command.length > 1 && command[1].equals("products")) {
					Products products = db.getProducts();
					System.out.println(products.size() + " products" + (products.size() == 0 ? "." : ":"));
					System.out.println(products);
				} else if (command.length > 2 && command[1].equals("placings") && command[2].equals("formatted")) {
				} else if (command.length > 1 && command[1].equals("placings")) {
					Placings placings = db.getPlacings();
					System.out.println(placings.size() + " placings" + (placings.size() == 0 ? "." : ":"));
					System.out.println(placings);
				} else {
					System.out.println(String.format("Error with showing. Full command: \"%s\"", String.join("; ", command)));
				}
			} else {
				System.out.println(String.format("Unknown command, splitted: \"%s\"", String.join("; ", command)));
			}
			System.out.println();
		}*/
	}
}
