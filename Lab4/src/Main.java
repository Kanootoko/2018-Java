import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

//import DAL.*;
//import Lab4Structs.*;
import Utils.Utils;

import BusinessLogic.*;
import BusinessLogic.BLStructs.*;

public class Main {
	public static void main(String[] args) throws Exception {
		Shops SQLShops = new Shops();
		Products SQLProducts = new Products();
		Placings SQLPlacings = new Placings();
		boolean showHelp = true;
		//SQLProducts.addProduct("БонПари мармелад, 100г");
		//SQLProducts.deleteProduct(SQLProducts.getProduct("БонПари мармелад, 100г"));
		//SQLShops.addShop("ShopName-test", "ShopAddress-test");
		//SQLShops.deleteShop(SQLShops.getShop("ShopName-test", "ShopAddress-test"));
		//SQLPlacings.addPlacing(SQLShops.getShop("Карусель", "Ленинский проспект, дом 100, корпус 1"), SQLProducts.getProduct("Coca-Cola 0.5л"), 30, 90);
		//SQLPlacings.addPlacing(SQLShops.getShops()[1], SQLProducts.getProducts()[2], 200, 60);
		//SQLPlacings.deletePlacing(SQLPlacings.getPlacings()[SQLPlacings.getPlacings().length - 1]);
		while (true) {
			try {
			if (showHelp) {
				System.out.println("\n\tHelp:");
				System.out.println("\"add shop <ShopName> <Address>\" - add shop to database");
				System.out.println("\"add product <ProductName>\" - add product to database");
				System.out.println("\"add placing <ProductName> <ShopName> <ShopAddress> <Quantity> <Price>\" - export (set quantity and proce) product to shop");
				System.out.println("\"add placing <ProductNumber> <ShopNumber> <Quantity> <Price>\" - export (set quantity and proce) product to shop");
				System.out.println();
				System.out.println("\"delete shop <ShopName> <ShopAddress>\" - delete shop from database");
				System.out.println("\"delete product <ProductName>\" - delete product from database");
				System.out.println("\"delete placing <ProductName> <ShopName> <ShopAddress>\" - delete placing from database");
				System.out.println("\"delete shop <ShopNumber>\" - delete shop from database by number");
				System.out.println("\"delete product number <ProductNumber>\" - delete product from database by number");
				System.out.println("\"delete placing <PlacingNumber>\" - delete placing from database by number");
				System.out.println();
				System.out.println("\"show shops\" - prints all shops in database");
				System.out.println("\"show products\" - prints all products in database");
				System.out.println("\"show placings\" - prints all placings in database");
				System.out.println();
				System.out.println("\"set price <price> <placingNumber>\" - set price for product at shop by placing number");
				System.out.println("\"set price <price> <productName> <shopName>\" - set price for product at shop");
				System.out.println("\"set quantity <price> <placingNumber>\" - set quantity for product at shop by placing number");
				System.out.println("\"set quantity <price> <productName> <shopName>\" - set quantity for product at shop");
			}
			System.out.print(">>> ");
			String[] command = Utils.parseQuotes(System.console().readLine());
			if (command.length > 0 && (command[0].equals("quit") || command[0].equals("exit")))
				break;
			if (command.length > 0 && command[0].equals("add")) {
				if (command.length == 4 && command[1].equals("shop")) {
					SQLShops.addShop(command[2], command[3]);
					System.out.println("Shop was successfully added");
				} else if (command.length == 3 && command[1].equals("product")) {
					SQLProducts.addProduct(command[2]);
					System.out.println("Product was successfully added");
				} else if (command[1].equals("placing")) {
					if (command.length == 7)
						try {
							SQLPlacings.addPlacing(SQLProducts.getProduct(command[2]), SQLShops.getShop(command[3], command[4]), new Integer(command[5]), new Integer(command[6]));
							System.out.println("Placing was successfully added");
						} catch (NumberFormatException ex) {
							throw new IllegalArgumentException("Placing addition failed. Quantity and price must be integers");
						}
					else if (command.length == 6) {
						try {
							SQLPlacings.addPlacing(SQLProducts.getProducts()[2], SQLShops.getShops()[3], new Integer(command[4]), new Integer(command[5]));
							System.out.println("Placing was successfully added");
						} catch (NumberFormatException ex) {
							throw new IllegalArgumentException("Placing addition failed. Quantity and price must be integers");
						}
					} else
						throw new IllegalArgumentException(String.format("Error with (add placing) command: you need to use ((ProductName+ShopName+Address) or (ProductNumber+ShopNumber))+Quantity+Price arguments. " +
						                                                 "Entered: \"%s\"", String.join("; ", command)));
				} else {
					throw new IllegalArgumentException(String.format("Unknown parameter to (add) command. Full line: \"%s\"", String.join("; ", command)));
				}
			} else if (command.length > 0 && command[0].equals("delete")) {
				if (command.length > 1 && command[1].equals("shop")) {
					if (command.length == 3) {
						try {
							SQLShops.deleteShop(SQLShops.getShops()[new Integer(command[2])]);
							System.out.println("Shop was successfully deleted");
						} catch (NumberFormatException ex) {
							throw new IllegalArgumentException("Shop number must be integer", ex);
						} catch (IndexOutOfBoundsException ex) {
							throw new IllegalArgumentException("Shop number is out of bounds", ex);
						}
					} else if (command.length == 4) {
						SQLShops.deleteShop(SQLShops.getShop(command[2], command[3]));
						System.out.println("Shop was successfully deleted");
					} else
						System.out.println(String.format("Error with (delete shop) command: you need to use (Name + Address) or (Number). Entered: \"%s\"", String.join("; ", command)));
				} else if (command.length > 1 && command[1].equals("product")) {
					if (command.length == 4 && command[2].equals("number")) {
						try {
							SQLProducts.deleteProduct(SQLProducts.getProducts()[new Integer(command[3])]);
							System.out.println("Product was successfully deleted");
						} catch (NumberFormatException ex) {
							throw new IllegalArgumentException("Product number must be integer", ex);
						} catch (IndexOutOfBoundsException ex) {
							throw new IllegalArgumentException("Product number is out of bounds", ex);
						}
					} else if (command.length == 3) {
						SQLProducts.deleteProduct(SQLProducts.getProduct(command[2]));
						System.out.println("Product was successfully deleted");
					} else
						throw new IllegalArgumentException(String.format("Error with (delete product) command: you need to use (Name) or (number Number). Entered: \"%s\"", String.join("; ", command)));
				} else if (command.length > 1 && command[1].equals("placing")) {
					if (command.length == 3) {
						try {
							SQLPlacings.deletePlacing(SQLPlacings.getPlacings()[new Integer(command[2])]);
							System.out.println("Placing was successfully deleted");
						} catch (NumberFormatException ex) {
							throw new IllegalArgumentException("Placing number must be integer", ex);
						} catch (IndexOutOfBoundsException ex) {
							throw new IllegalArgumentException("Placing number is out of bounds", ex);
						}
					} else if (command.length == 5) {
						SQLPlacings.deletePlacing(SQLPlacings.getPlacing(SQLProducts.getProduct(command[2]), SQLShops.getShop(command[3], command[4])));
						System.out.println("Placing was successfully deleted");
					} else
						throw new IllegalArgumentException(String.format("Error with (delete placing) command: you need to use (ProductName+ShopName+Address) or (Number). Entered: \"%s\"", String.join("; ", command)));
				} else 
					throw new IllegalArgumentException(String.format("Unknown parameter to (delete) command. Full line: \"%s\"", String.join("; ", command)));
			} else if (command.length > 0 && command[0].equals("show")) {
				if (command.length > 1 && command[1].equals("shops")) {
					Shop[] shops = SQLShops.getShops();
					System.out.println(shops.length + " shops" + (shops.length == 0 ? "." : ":"));
					for (int i = 0; i < shops.length; ++i)
						System.out.println(String.format("%3d: %s", i, shops[i]));
				} else if (command.length > 1 && command[1].equals("products")) {
					Product[] products = SQLProducts.getProducts();
					System.out.println(products.length + " products" + (products.length == 0 ? "." : ":"));
					for (int i = 0; i < products.length; ++i)
						System.out.println(String.format("%3d: %s", i, products[i]));
				} else if (command.length > 1 && command[1].equals("placings")) {
					Placing[] placings = SQLPlacings.getPlacings();
					System.out.println(placings.length + " placings" + (placings.length == 0 ? "." : ":"));
					for (int i = 0; i < placings.length; ++i)
						System.out.println(String.format("%3d: %s", i, placings[i]));
				} else {
					throw new IllegalArgumentException(String.format("Unknown parameter to (show) command. Full line: \"%s\"", String.join("; ", command)));
				}
			} else if (command.length > 0 && command[0].equals("set")) {
				if (command.length > 1 && command[1].equals("price")) {
					if (command.length == 4) {
						try {
							SQLPlacings.setPrice(SQLPlacings.getPlacings()[new Integer(command[3])], new Integer(command[2]));
							System.out.println("Price was set successfully");
						} catch (NumberFormatException ex) {
							throw new IllegalArgumentException("Price and placing number must be integer", ex);
						} catch (IndexOutOfBoundsException ex) {
							throw new IllegalArgumentException("Placing number is out of bounds", ex);
						}
					} else if (command.length == 6) {
						try {
							SQLPlacings.setPrice(SQLPlacings.getPlacing(SQLProducts.getProduct(command[3]),
							                                            SQLShops.getShop(command[4], command[5])
							                                           ),
							                  new Integer(command[2])
							                 );
							System.out.println("Price was set successfully");
						} catch (NumberFormatException ex) {
							throw new IllegalArgumentException("Price must be integer", ex);
						}
					} else
						throw new IllegalArgumentException("Error with (set price) command: " +
						                                   "you need to use price+((productName+shopName+shopAddress) or (placingNumber))"
						                                  );
				} else if (command.length > 1 && command[1].equals("quantity")) {
					if (command.length == 4) {
						try {
							SQLPlacings.setQuantity(SQLPlacings.getPlacings()[new Integer(command[3])], new Integer(command[2]));
							System.out.println("Quantity was set successfully");
						} catch (NumberFormatException ex) {
							throw new IllegalArgumentException("Quantity and placing number must be integer", ex);
						} catch (IndexOutOfBoundsException ex) {
							throw new IllegalArgumentException("Placing number is out of bounds", ex);
						}
					} else if (command.length == 5) {
						try {
							SQLPlacings.setQuantity(SQLPlacings.getPlacing(SQLProducts.getProduct(command[3]),
							                                                SQLShops.getShop(command[4], command[5])
							                                               ),
							                     new Integer(command[2])
							                    );
							System.out.println("Quantity was set successfully");
						} catch (NumberFormatException ex) {
							throw new IllegalArgumentException("Quantity must be integer", ex);
						}
					} else
						throw new IllegalArgumentException("Error with (set price) command: " +
						                                   "you need to use price+((productName+shopName+shopAddress) or (placingNumber))"
						                                  );
				} else
					throw new IllegalArgumentException(String.format("Unknown parameter to (set) command. Full line: \"%s\"", String.join("; ", command)));
			} else if (command.length > 0 && command[0].equals("help")) {
				if (command.length > 1 && command[1].equals("on")) {
					showHelp = true;
					System.out.println("Help if turned on. Command \"help on\" to turn it off");
				} else if (command.length > 1 && command[1].equals("off")) {
					showHelp = false;
					System.out.println("Help if turned off. Command \"help on\" to turn it on again");
				} else
					throw new IllegalArgumentException("Ugage of (help): \"help on\" to turn help on, \"help off\" to turn it off");
			} else {
				System.out.println(String.format("Unknown command, splitted: \"%s\"", String.join("; ", command)));
			}
			} catch (Exception ex) {
				System.out.println("Error occured:");
				ex.printStackTrace();
			} finally {
				System.out.println();
			}
		}
	}
}
