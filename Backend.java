// add file header
// figure out get()

import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class Backend implements BackendInterface {

	private ArrayList<Product> products;
	private int maxID;

	private RedBlackTree<Product> rbt;
	private hashNode[] hashTable;
	private int hashSize;
	private int hashCapacity;
	private float dailyProfit;

	class hashNode {

		public int ID;
		public String name;

		public hashNode(String _name, int _ID) {
			ID = _ID;
			name = _name;
		}

	}

	/**
	 * Constructor that takes in a string of a desired file from the user/ front end
	 * 
	 * @param f file name to read
	 */
	public Backend(String f) {
		ProductReader pr = new ProductReader();
		FileReader file;
		try {
			file = new FileReader(f);
			products = pr.readDataSet(file);
		} catch (DataFormatException e) {
			System.out.println("Data could not be read");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/O Exception");
			e.printStackTrace();
		}

		rbt = new RedBlackTree<Product>();

		dailyProfit = 0;
		maxID = 0;

		hashSize = 0;
		hashCapacity = 100;
		hashTable = new hashNode[100];

		for (Product prod : products) {
			addToHash(prod);
			rbt.insert(prod);
			if (prod.getID() > maxID)
				maxID = prod.getID();
		}

	}

	public ArrayList<Product> getAllProducts() {
		return this.products;
	}

	/**
	 * Gets the amount in stock of a given product, search-able by name
	 *
	 * @param productName
	 * @return quantityAvailable
	 */
	public int getStock(String productName) {
		Product tempProd = getFromHash(productName);
		Product prod = rbt.get(tempProd, rbt.root);
		return prod.getQuantityAvailable();

	}

	/**
	 * Gets a product object
	 *
	 * @param productName
	 * @return Product
	 */
	public Product getProduct(String productName) {
		Product prod;
		try {
			Product tempProd = getFromHash(productName);
			prod = rbt.get(tempProd, rbt.root);
		} catch (NoSuchElementException e) {
			System.out.println(e);
			e.printStackTrace();
			prod = null;
		}
		return prod;
	}

	/**
	 * Increases the quantity of a product by amount. Also subtracts the cost of the
	 * products from the daily profit
	 *
	 * @param productName
	 * @param amount      to restock
	 * @return true if successful, false if not
	 */
	public boolean restock(String productName, int amount) {
		if (amount < 0) {
			return false;
		}
		Product tempProd;
		try {
			tempProd = getFromHash(productName);
		} catch (Exception excpt) {
			return false;
		}
		Product prod = rbt.get(tempProd, rbt.root);
		dailyProfit -= prod.getCost() * amount;
		return prod.addStock(amount);
	}

	/**
	 * Decreases the quantity of a product. Also adds to the profit
	 *
	 * @param productName
	 * @param amount
	 * 
	 */
	public void sell(String productName, int amount) {
		Product tempProd = getFromHash(productName);
		Product prod = rbt.get(tempProd, rbt.root);
		if (prod.addStock(amount * -1))
			dailyProfit += prod.getRetailPrice() * amount;
		else
			throw new IllegalArgumentException();
	}

	/**
	 * Returns the total money gotten from sold products, minus the total money
	 * spent on restocked or added products.
	 *
	 * @return profit amount
	 */
	public float dailyProfit() {
		return dailyProfit;
	}

	/**
	 * Returns an array of the length products with the least stock.
	 *
	 * @param length - the number of items we can afford to restock
	 * @return products that need restocking
	 */
	public ArrayList<Product> needsRestock(int length) {
		ArrayList<Product> need = new ArrayList<Product>();
		for (int i = 0; i < hashCapacity; i++) {
			hashNode hn = hashTable[i];
			if (hn != null) {
				Product tempProd = getFromHash(hn.name);
				Product prod = rbt.get(tempProd, rbt.root);
				need.add(prod);
			}
		}
		Collections.sort(need, (p1, p2) -> {
			return p1.getQuantityAvailable() - p2.getQuantityAvailable();
		});
		ArrayList<Product> out = new ArrayList<Product>();
		for (int i = 0; i < length; i++) {
			out.add(need.get(i));
		}
		return out;
	}

	/**
	 * Adds a new product to the tree.
	 *
	 * @param newProduct
	 * @return true if successful, false otherwise
	 */
	public boolean addProduct(Product newProduct) {
		if (rbt.contains(newProduct))
			return false;
		else {
			addToHash(newProduct);
			rbt.insert(newProduct);
			return true;
		}
	}

	/**
	 * Returns a product id that is not already being used by a product. By tracking
	 * the largest ID that is currently being used, a number 1 above that will
	 * always be unused
	 * 
	 * @return unused ID number
	 */
	public int newProductID() {
		maxID++;
		return maxID;
	}

	/**
	 * Adds a product to the hash table
	 *
	 * @param product to add
	 */
	private void addToHash(Product prod) {
		int ID = prod.getID();
		String name = prod.getName();
		hashNode hn = new hashNode(name, ID);
		int i = Math.abs(name.hashCode());
		while (hashTable[i % hashCapacity] != null) {
			i++;
		}
		hashTable[i % hashCapacity] = hn;
		hashSize++;
		if (hashSize / (double) hashCapacity > 0.85)
			resizeHash();
	}

	/**
	 * Add a hashNode to the hash table
	 *
	 * @param node
	 */
	private void addToHash(hashNode node) {
		int i = Math.abs(node.name.hashCode());
		while (hashTable[i % hashCapacity] != null) {
			i++;
		}
		hashTable[i % hashCapacity] = node;
		hashSize++;
	}

	/**
	 * Doubles the size of the hash table
	 *
	 */
	private void resizeHash() {
		hashNode[] temp = hashTable;
		hashCapacity *= 2;
		hashSize = 0;
		hashTable = new hashNode[hashCapacity];
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != null)
				addToHash(new hashNode(temp[i].name, temp[i].ID));
		}
	}

	/**
	 * Looks up a the product ID given a name, and returns an product shell with the
	 * same name and ID Used to get shell products to compare against when searching
	 * the rbt
	 *
	 * @param productName
	 * @return product shell
	 */
	private Product getFromHash(String name) {
		hashNode hn = getFromHashHelper(name);
		Product prod = new Product(hn.ID, hn.name, 0, 0, 0, 0);
		return prod;

	}

	/**
	 * A helper method to get a hashNode out of the hash table. This can be returned
	 * to and used by the getFromHash() method
	 *
	 * @param name
	 * @return hashNode
	 */
	private hashNode getFromHashHelper(String name) {
		int i = Math.abs(name.hashCode());
		while (hashTable[i % hashCapacity] != null) {
			if (hashTable[i % hashCapacity].name == name) {
				return hashTable[i % hashCapacity];
			} else {
				i++;
			}
		}
		throw new NoSuchElementException("Could not find product ID for that product. Perhaps the name is misspelled?");
	}

	public void printHash() {
		for (int i = 0; i < hashCapacity; i++) {
			if (hashTable[i] != null) {
				System.out.print(hashTable[i].name + ": ");
				System.out.println(hashTable[i].ID);
			} else
				System.out.println("Empty");
		}
	}

	public void clearHash() {
		for (int i = 0; i < hashCapacity; i++) {
			hashTable[i] = null;
		}
	}

	/**
	 * Updates the CSV file based on users choice to add a product to the file
	 * 
	 * @return true if successfully added
	 */
	public boolean printToCSV() {
		try {
			final String CSV_FILE_NAME = "inventory.csv";
			FileWriter myWriter = new FileWriter(CSV_FILE_NAME);
			for (ProductInterface p : this.getAllProducts()) {
				myWriter.write(p.getID() + "," + p.getName() + "," + p.getQuantityAvailable() + ","
						+ p.getQuantitySold() + "," + String.format("%.2f", p.getCost()) + ","
						+ String.format("%.2f", p.getRetailPrice()) + "\n");
			}
			myWriter.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
