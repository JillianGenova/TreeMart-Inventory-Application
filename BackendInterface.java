// --== CS400 File Header Information ==--
// Name: Sidney Hestres
// Email: shestres@wisc.edu
// Team: Blue
// Role: Backend Developer
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.NoSuchElementException;

interface BackendInterface {
	public ArrayList<Product> getAllProducts(); // returns all products
	public int getStock(String productName); //returns the amount of stock of a certain products
	public Product getProduct(String productName); //Returns a product
	public boolean restock(String productName, int amount); //Increases the quantity of a product by amount. Also subtracts the cost of the products from the daily profit amount. Returns false if the product is not in the system, thue otherwise
	public void sell(String productName, int amount) throws NoSuchElementException; //decreases the quantity of a product. Also adds to the profit
	public float dailyProfit(); //Returns the total money gotten from sold products, minus the total money spent on restocked or added products.
	public ArrayList<Product> needsRestock(int length); //Returns an array of the <length> products with the least stock.
	public boolean addProduct(Product newProduct); //Adds a new product to the tree. Returns true if successful, false otherwise
}
