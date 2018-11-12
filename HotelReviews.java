import java.io.File;
import java.io.FileNotFoundException;

/**
 * A class to practice reading file contents into arrays and sorting
 *
 * @author Nick Coyle
 * @version 11.19.2018
 */
public class HotelReviews
{
    private String[] hotels;
    private int[][] reviews;
    private double[] avgRanks;

    public HotelReviews(File inputFile) throws FileNotFoundException {
        readData(inputFile);
        calculateAvgRankings();
    }

    public int getHotelCount() {
        return hotels.length;
    }

    public int getRankHotel(int review, int hotel) throws IllegalArgumentException {
        return reviews[hotel][review];
    }

    public String getHotel(int index) throws IllegalArgumentException {
        return hotels[index];
    }

    public double getAvgRank(int index) throws IllegalArgumentException {
        return avgRanks[index];
    }

    private void readData(File inputFile) throws FileNotFoundException {

    }

    private void calculateAvgRankings() {

    }

    public void displayReviews() {

    }

    public void displayAvgRanks() {

    }

    public void displayHotels() {

    }

    public void sortByRanking() {

    }

    /**
     * 
     *	Write test methods for each of your methods. You don’t need to test the display methods.
     *	Test with different numbers of hotels and reviewers to make sure your algorithms are correct.
     *	
     */
    public void test() {

    }

}
