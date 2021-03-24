// --== CS400 File Header Information ==--
// Name: Yash Butani
// Email: @wisc.edu
// Team: Blue
// Role: Data Wrangler
// Group: KE
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
/*
This class contains methods that test various capabilities of this project specifically related to scanning in and using data from a file
 */
public class DataWranglerTests
{
    /*
     * this method tests the getName() method defined in the ProductReader class
     */
    @Test
    public void testGetProductName()
    {
        ArrayList<ProductInterface> items = new ArrayList<ProductInterface>();
        Product p1 = new Product(1,"apple",5,5,6.0,10.0);
        items.add(p1);
        items.add(new Product(2,"onion",4,5,6.0,10.0));
        items.add(new Product(3,"banana",10,13,1.0,3.0));
        String p1_name = p1.getName();
        assertEquals("apple",p1.getName());
        assertEquals("onion",items.get(1).getName());
        assertEquals("banana",items.get(2).getName());
    }

    /*
     * This method tests the scanIn method's implementation in the ProductReader class
     * The readDataSet method is defined in ProductDataReader Interface and is responsible for scanning in the csv
     * that contains all the data and making each line into a Product class and adding it to a list
     * This method tests whether the ScanIn method successufully returns a method of Product objects
     */
    @Test
    public void testScanIn()
    {
        ProductReader r = new ProductReader();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("listdata.csv"));
            ArrayList<Product> products = r.readDataSet(reader);
            for (ProductInterface it : products)
            {
                assertEquals("class Product",it.getClass().toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method tests the getID method of the Product class
     */
    @Test
    public void testGetProductId()
    {
        ArrayList<ProductInterface> items = new ArrayList<ProductInterface>();
        Product p1 = new Product(1,"apple",5,5,6.0,10.0);
        items.add(p1);
        items.add(new Product(2,"onion",4,5,6.0,10.0));
        items.add(new Product(3,"banana",10,13,1.0,3.0));
        String p1_name = p1.getName();
        assertEquals(1,items.get(0).getID());
        assertEquals(2,items.get(1).getID());
        assertEquals(3,items.get(2).getID());
    }


    /*
     * This method tests whether the ScanIn() method implemented in the ProductReader class and defined in the Reader Interface
     * scans in the full list of Products
     */
    @Test
    public  void testReader()
    {
        ProductReader r = new ProductReader();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("listdata.csv"));
            ArrayList<Product> products = r.readDataSet(reader);
            assertEquals(200,products.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
    }
    /*
    This method tests the getRetailPrice method defined in the product class
     */
    @Test
    public void testgetRetailPrice()
    {
        ArrayList<ProductInterface> items = new ArrayList<ProductInterface>();
        Product p1 = new Product(1,"apple",5,5,6.0,10.0);
        items.add(p1);
        items.add(new Product(2,"onion",4,5,6.0,12.0));
        items.add(new Product(3,"banana",10,13,1.0,3.0));
        String p1_name = p1.getName();
        assertEquals(10.0,items.get(0).getRetailPrice(), 0.00001);
        assertEquals(12.0,items.get(1).getRetailPrice(), 0.00001);
        assertEquals(3.0,items.get(2).getRetailPrice(), 0.00001);
    }

}
