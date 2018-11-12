import java.io.File;
import java.io.FileNotFoundException;
/**
 * Main method
 *
 * @author Nick Coyle
 * @version 11.19.2018
 */
public class Main
{
    public static void main(String[] args) {
        try
        {
            File inputFile = new File("testdata.txt");
            HotelReviews hotelReviews = new HotelReviews(inputFile);
        } catch(Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
