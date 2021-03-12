import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
// @author Yash Butani
public class ProductReader implements  Reader{
    /*
this is the Product class which represents the type that all objects in the tree will be
 */
    public static ArrayList<Product> items = new ArrayList<>();

    public ProductReader() {
        // constructor does nothing right now
        /*
        when creating an object of type ReadIn just make a new object
        then make an arraylist and set that equal to ScanIn() so that the method populates the
        arraylist with values
         */
    }

    public  ArrayList<Product> ScanIn() {
        try {
            File input = new File("listdata.csv");
            Scanner in = new Scanner(input);
            String line = "";
            while (in.hasNextLine() && line != null) {
                line = in.nextLine();
                String[] parse = line.split(",");
                //items.add(new Product(Double.parseDouble(parse[0]), parse[1], Double.parseDouble(parse[2]), Double.parseDouble(parse[3]), Double.parseDouble(parse[4]), Double.parseDouble(parse[5])));
            }
            return items;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       return items;
    }

}
