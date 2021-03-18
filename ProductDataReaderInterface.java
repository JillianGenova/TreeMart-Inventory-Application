// --== CS400 File Header Information ==--
// Name: ADD YOUR INFO HERE
// Email: @wisc.edu
// Team: Blue
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
 * MovieDataReaderInterface to be implemented by MovieDataReader
 *
 * @author Yash Butani
 *
 */
public interface ProductDataReaderInterface {
    public ArrayList<ProductInterface> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException;
}