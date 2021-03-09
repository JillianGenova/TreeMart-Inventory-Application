interface BackendInterface {
	public int getStock(String productName); //returns the amount of stock of a certain producst
	public Product getProduct(String productName); //Returns a product
	public bool restock(String productName, int amount); //Increases the quantity of a product by amount. Also subtracts the cost of the products from the daily profit amount. Returns false if the product is not in the system, thue otherwise
	public void sell(String productName, int amount); //decreases the quantity of a product. Also adds to the profit
	public float dailyProfit(); //Returns the total money gotten from sold products, minus the total money spent on restocked or added products.
	public Product[] needsRestock(int length); //Returns an array of the <length> products with the least stock.
	public bool addProduct(Product newProduct); //Adds a new product to the tree. Returns true if successful, false otherwise
	public int newProductID(); //returns a product id that is not already being used by a product
}
