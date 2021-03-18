// --== CS400 File Header Information ==--
// Name: Yash Butani
// Email: @wisc.edu
// Team: Blue
// Group: KE
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class ProductReader implements  ProductDataReaderInterface{

    /**
     * Method that reads product data in CSV format from a data file using a Reader object
     *
     * @param inputFileReader object from which data is read
     * @return list of all the product objects read in from the data file
     */
    @Override
    public ArrayList<ProductInterface> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException {
        ArrayList<ProductInterface> items = new ArrayList<ProductInterface>();
        if (inputFileReader == null) {
            throw new FileNotFoundException();
        }
        BufferedReader dataReader = new BufferedReader(inputFileReader);
        dataReader.readLine(); // read in the first row
        String row = "";
        int linecount = 0;
        while ((row = dataReader.readLine()) != null) { // iterate until reach end of file
            if(linecount == 0){}//skip the first line that is just column headings
            String[] parse = row.split(",");
            items.add(new Product(Integer.parseInt(parse[0]), parse[1], Integer.parseInt(parse[2]), Integer.parseInt(parse[3]), Double.parseDouble(parse[4]), Double.parseDouble(parse[5])));
            linecount++;
        }
        dataReader.close();
        return items;
    }

}