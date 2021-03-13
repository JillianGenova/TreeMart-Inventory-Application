import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 * Product Reader dummy to help back end test code
 * 
 * @author genova.jill
 *
 */
public class ProductDataReaderDummy implements ProductDataReaderInterface {

	@Override
	public ArrayList<ProductInterface> readDataSet(Reader inputFileReader)
			throws FileNotFoundException, IOException, DataFormatException {
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
		
		productList.add(new ProductInterface() {
			@Override
			public double getRetailPrice() {
				return 5.05;
			}

			@Override
			public double getCost() {
				return 10.50;
			}

			@Override
			public int getQuantityAvailable() {
				return 11;
			}

			@Override
			public int getQuantitySold() {
				return 4;
			}

			@Override
			public String getName() {
				return "Pears";
			}

			@Override
			public int getID() {
				return 1235;
			}
		});
		
		productList.add(new ProductInterface() {
			@Override
			public double getRetailPrice() {
				return 7.99;
			}

			@Override
			public double getCost() {
				return 4.00;
			}

			@Override
			public int getQuantityAvailable() {
				return 30;
			}

			@Override
			public int getQuantitySold() {
				return 6;
			}

			@Override
			public String getName() {
				return "Oranges";
			}

			@Override
			public int getID() {
				return 1236;
			}
		});
		return productList;
	}

}
