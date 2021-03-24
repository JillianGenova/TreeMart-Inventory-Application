// --== CS400 File Header Information ==--
// Name: Yash Butani
// Email: butani@wisc.edu
// Team: Blue
// Role: Data Wrangler
// Group: KE
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

/**
 * ProductDataReaderInterface to be implemented by ProductReader class
 *
 * @author Yash Butani
 *
 */
public interface ProductDataReaderInterface {
    public ArrayList<Product> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException;
}