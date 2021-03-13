import java.io.Reader;
import java.util.ArrayList;

/**
 * Backend dummy to help front end test code
 * 
 * @author genova.jill
 *
 */
public class BackendDummy implements BackendInterface {

	/**
	 * This constructor instantiates a backend with the command line arguments
	 * passed to the app when started by the user. The arguments are expected to
	 * contain the path to the data file that the backend will read in.
	 * 
	 * @param args list of command line arguments passed to front end
	 */
	public BackendDummy(String[] args) {
		System.out.println("Dummy backend; ignoring file paths in argument");
	}

	/**
	 * A constructor that will create a backend from data that can be read in with a
	 * reader object.
	 * 
	 * @param r A reader that contains the data in CSV format.
	 */
	public BackendDummy(Reader r) {
		System.out.println("Dummy backend; ignoring data in argument");
	}

	/**
	 * returns the amount of stock of a certain product- Always returns 50
	 */
	@Override
	public int getStock(String productName) {
		return 50;
	}

	/**
	 * returns a product object- Dummy object with constant values
	 */
	@Override
	public ProductInterface getProduct(String productName) {
		return new ProductInterface() {
			@Override
			public double getRetailPrice() {
				return 9.99;
			}
				
			@Override
			public double getCost() {
				return 15.99;
			}
				
			@Override
			public int getQuantityAvailable() {
				return 55;
			}
			
			@Override
			public int getQuantitySold() {
				return 10;
			}
				
			@Override
			public String getName() {
				return "Apples";
			}
				
			@Override
			public int getID() {
				return 1234;
			}
		};
	}

	/**
	 * Increases the quantity of a product by amount. Also subtracts the cost of the
	 * products from the daily profit amount. Returns false if the product is not in
	 * the system, true otherwise
	 */
	@Override
	public boolean restock(String productName, int amount) {
		return true;
	}

	/**
	 * decreases the quantity of a product. Also adds to the profit
	 */
	@Override
	public void sell(String productName, int amount) {
		System.out.println("Sell product " + productName + " by dummy amount " + amount);
	}

	/**
	 * Returns the total money gotten from sold products, minus the total money
	 * spent on restocked or added products.
	 */
	@Override
	public float dailyProfit() {
		return (float) 200.00;
	}

	/**
	 * Returns an array of the <length> products with the least stock
	 */
	@Override
	public ArrayList<ProductInterface> needsRestock(int length) {
		ArrayList<ProductInterface> productList = new ArrayList<ProductInterface>();
		productList.add(new ProductInterface() {
			@Override
			public double getRetailPrice() {
				return 9.99;
			}
				
			@Override
			public double getCost() {
				return 15.99;
			}
				
			@Override
			public int getQuantityAvailable() {
				return 55;
			}
			
			@Override
			public int getQuantitySold() {
				return 10;
			}
				
			@Override
			public String getName() {
				return "Apples";
			}
				
			@Override
			public int getID() {
				return 1234;
			}
		});
		return productList;
	}

	/**
	 * Adds a new product to the tree. Returns true if successful, false otherwise
	 */
	@Override
	public boolean addProduct(ProductInterface newProduct) {
		return true;
	}

	public int newProductID() {
		return 1234;
	}
}
