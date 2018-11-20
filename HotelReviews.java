import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class to practice reading file contents into arrays and sorting 3 arrays and keeping all data in parallel
 *
 * @author Nick Coyle
 * @version 11.20.2018
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

    /**
     * A method to get how many hotels are in the dataset
     * 
     * @return Returns an integer how many hotels
     */
    public int getHotelCount() {
        return hotels.length;
    }

    /**
     * A method to get the ranking of one hotel according to the specific reviewer
     * 
     * @params review an integer designating the reviewer and hotel an integer designating the hotel by spot in array
     * @return Returns integer the review score
     */
    public int getRankHotel(int review, int hotel) throws IllegalArgumentException {
        return reviews[review][hotel];
    }

    /**
     * A method to get the hotel name by index
     * 
     * @params index an integer for the spot in array to get
     * @return String the name of the hotel
     */
    public String getHotel(int index) throws IllegalArgumentException {
        return hotels[index];
    }

    /**
     * A method to get the average ranking for a hotel
     * 
     * @params index integer of the hotel in the array
     * @return double the ranking value
     */
    public double getAvgRank(int index) throws IllegalArgumentException {
        return avgRanks[index];
    }

    /**
     * A method to read the data from the input file and store its values into our array fields
     * 
     * @params inputFile File the data file
     * 
     */
    private void readData(File inputFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(inputFile);

        int numHotels = scanner.nextInt();
        int numReviewers = scanner.nextInt();

        hotels = new String[numHotels];
        reviews = new int[numReviewers][numHotels];
        avgRanks = new double[numHotels];

        for(int i = 0; i < numReviewers; ++i) {
            for(int j = 0; j < numHotels; ++j) {
                reviews[i][j] = scanner.nextInt();
            }
        }

        scanner.nextLine();

        for(int i = 0; i < numHotels; ++i) {
            hotels[i] = scanner.nextLine();
        }        

    }

    /**
     * A method to calculate the average rankings for each hotel and store in an array field called avgRanks
     *
     */
    private void calculateAvgRankings() {
        int numHotels = getHotelCount();
        double avg;
        
        for(int i = 0; i < numHotels; ++i) {
            avg = 0;
            
            for(int j = 0; j < reviews.length; ++j) {
                avg += reviews[j][i];
            }
            
            avg /= reviews.length;
            avgRanks[i] = avg;
        }
        
    }

    /**
     * A method to print the reviews in a nice table
     * 
     * Have to use a fencepost algorithm here to avoid extra
     * tab at end of output
     */
    public void displayReviews() {
        int numHotels = getHotelCount();
        
        System.out.println("Reviews");
        
        for(int i = 0; i < reviews.length; ++i) {
            System.out.print(reviews[i][0]);
        
            for(int j = 1; j < numHotels; ++j) {
                System.out.print("\t" + reviews[i][j]);
            }
            
            System.out.println();
        }

        System.out.println();
    }

    /** 
     * A method to print the average rankings in a nice table
     * 
     * Have to use a fencepost algorithm here to avoid extra
     * tab at end of output
     */
    public void displayAvgRanks() {
        System.out.println("Average Rankings");
        System.out.print(String.format("%.2f",avgRanks[0]));
        
        for(int i = 1; i < avgRanks.length; ++i) {
            System.out.print("\t" + String.format("%.2f", avgRanks[i]));
        }

        System.out.println();
        System.out.println();
    }

    /** 
     * A method to print the names of the hotels in a list
     */
    public void displayHotels() {
        System.out.println("Hotels");
        
        for(int i = 0; i < hotels.length; ++i) {
            System.out.println(hotels[i]);
        }

        System.out.println();
    }

    /**
     * A method to sort all 3 of our parallel arrays hotels, reviews, and avgRankings based on average ranking value for each hotel from largest to smallest
     * employs a selection sort
     */
    public void sortByRanking() {
        int numHotels = getHotelCount();
        int iMax;
        double tempRanking;
        String tempHotel;
        int tempReview;
        int reviewsRow;

        for(int i = 0; i < numHotels - 1; ++i)
        {
            iMax = i;

            for(int j = i + 1; j < numHotels; ++j)
            {                
                if(avgRanks[iMax] < avgRanks[j]) {
                    iMax = j;   
                }
            }

            if(iMax != i) {
                tempRanking = avgRanks[i];
                avgRanks[i] = avgRanks[iMax];
                avgRanks[iMax] = tempRanking; 

                tempHotel = hotels[i];
                hotels[i] = hotels[iMax];
                hotels[iMax] = tempHotel;

                for(reviewsRow = 0; reviewsRow < reviews.length; ++reviewsRow) {
                    tempReview = reviews[reviewsRow][i];
                    reviews[reviewsRow][i] = reviews[reviewsRow][iMax];
                    reviews[reviewsRow][iMax] = tempReview;
                }                   

            }
        }
    }

    /**
     * 
     *  Write test methods for each of your methods. You don’t need to test the display methods.
     *  Test with different numbers of hotels and reviewers to make sure your algorithms are correct.
     *  
     */
    public static void test() throws FileNotFoundException {
            File inputFile = new File("testData.txt");
            HotelReviews hotelReviews = new HotelReviews(inputFile);
            
            if(hotelReviews.getHotelCount() != 7) System.out.println("getHotelCount should be 7 but is " + hotelReviews.getHotelCount());
            if(hotelReviews.getRankHotel(0,0) != 5) System.out.println("Before sorting, getRankHotel of first hotel and first review should be 5 but is " + hotelReviews.getRankHotel(0,0));
            if(hotelReviews.getRankHotel(2,6) != 6) System.out.println("Before sorting, getRankHotel of last hotel and last review should be 6 but is " + hotelReviews.getRankHotel(2,6));
            if(!hotelReviews.getHotel(0).equals("Fancy Pants Hotel - Free Bug Spray Included")) System.out.println("Before sorting, getHotel method should return 'Fancy Pants Hotel - Free Bug Spray Included' but instead returned " + hotelReviews.getHotel(0));
            if(hotelReviews.getAvgRank(0) != 8.00) System.out.println("Before sorting, getAvgRank of hotel 0 should be 8.00 but is " + hotelReviews.getAvgRank(0));
          
            hotelReviews.sortByRanking();
            if(hotelReviews.getRankHotel(0,0) != 8) System.out.println("After sorting, getRankHotel of first hotel and first review should be 8 but is " + hotelReviews.getRankHotel(0,0));
            if(!hotelReviews.getHotel(0).equals("So Easy a Caveman Can Do It Hotel")) System.out.println("After sorting, getHotel method should return 'So Easy a Caveman Can Do It Hotel' but instead returned " + hotelReviews.getHotel(0));
            if(hotelReviews.getAvgRank(0) != 28.0/3.0) System.out.println("After sorting, getAvgRank of hotel 0 should be 9.33 but is " + hotelReviews.getAvgRank(0));       
    }

}
