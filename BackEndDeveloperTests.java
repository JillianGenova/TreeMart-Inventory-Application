import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class BackEndDeveloperTests {
	
	Backend backend;
	ProductReader reader;
	ArrayList<Product> products;
	String file = "listdata.csv";

	@BeforeEach
	public void setup(){
		try {
			FileReader fileReader = new FileReader(file);
			reader = new ProductReader();
			products = reader.readDataSet(fileReader);
		} catch (IOException | DataFormatException e) {
			System.out.println("An error occured when loading the file");
		}
		backend = new Backend(file);
		if(products.size() < 2){
			Product prod1 = new Product(1,"Oranges",1,1,1,1);
			Product prod2 = new Product(5,"Apples",2,2,2,2);
			Product prod3 = new Product(3,"Bananas",3,3,3,3);
			Product prod4 = new Product(4,"Watermelon",0,0,4,4);

			products.add(prod1);
			products.add(prod2);
			products.add(prod3);
			products.add(prod4);

			backend.addProduct(prod1);
			backend.addProduct(prod2);
			backend.addProduct(prod3);
			backend.addProduct(prod4);
		}
	}



	//This tests that the backend can find a products id from the hash table, and then can find that product in the rb tree by that id
	@Test
	void IDFromHash(){
		for(int i = 0; i < products.size(); i++){
			int ID = backend.getProduct(products.get(i).getName()).getID();
			assertEquals(products.get(i).getID(), ID);
		}
	}

	//This tests that all products from the CSV get automatically added when the backend is instantiated
	@Test
	void fromCSV(){
		try{
			for(int i = 0; i < products.size(); i++){
				Product prod = backend.getProduct(products.get(i).getName());
				assertTrue(prod != null);
			}
		}
		catch(Exception excpt){
			assertTrue(false);
		}
	}

	//This ensures we cannot add a duplicate product
	@Test 
	void addExistingToTree(){
		boolean added = backend.addProduct(products.get(1));
		assertFalse(added);
	}


	//This test ensures that the backend will report false or throw an exception when trying to stock or sell an item not in the tree
	@Test 
	void stockUnknown(){
		boolean test1 = backend.restock("BadTastingFood",2);
		boolean test2 = false;
		try{backend.sell("BadTastingFood",2);}
		catch (Exception excpt){test2 = true;}

		assertFalse(test1);
		assertTrue(test2);

	}

	//This test makes sure you cannot sell more than you have
	@Test
	void sellTooMuch(){
		try{backend.sell(products.get(0).getName(), 1000000);assertTrue(false);}
		catch(Exception excpt){assertTrue(true);}
	}


	//This test makes sure the backend correctly tracks daily profit


	//This tests the functionality of the needsRestock() method
	@Test
	void needRestock(){
		Backend back = new Backend(file);

		ArrayList<Product> test = back.needsRestock(2);

		/*
		for(Product prod : test){
			System.out.print(prod.getName());
			System.out.println(prod.getQuantityAvailable());
		}*/

		assertEquals("Capon", test.get(0).getName());
		assertEquals("Beans", test.get(1).getName());
		assertEquals(test.size(),2);
	}

	
	//This test makes sure the back end correctly tracks daily profit
	@Test
	void dailyProfit(){
		backend.restock("Oranges",2);
		assertEquals(-13.86, backend.dailyProfit(), 0.000001);

	}
}
