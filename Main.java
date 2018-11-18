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
            File inputFile = new File("data.txt");
            HotelReviews hotelReviews = new HotelReviews(inputFile);
            
            hotelReviews.displayReviews();
            hotelReviews.displayAvgRanks();
            hotelReviews.displayHotels();
            
            hotelReviews.sortByRanking();
            hotelReviews.displayReviews();
            hotelReviews.displayAvgRanks();
            hotelReviews.displayHotels();
               
            System.out.println();
            System.out.println("**********************Reached test method from main**********************");
            System.out.println();
            HotelReviews.test();
            
        } catch(Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
