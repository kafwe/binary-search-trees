/**
 * A class to represent the daily vaccination number of a country
 * 
 * @author Jordy Kafwe
 */

public class Vaccine implements Comparable<Vaccine> {

    private String country;
    private String date;
    private String vaccinations;


    /**
     * Constructs a Vaccine object by parsing a line from the CSV file.
     * The data is in the order: country, date, vaccinations
     * 
     * @param line a line of the CSV file 
     */
    public Vaccine(String line) {
        String[] data = line.split(",");
        country = data[0];
        date = data[1];
        vaccinations = data.length == 3 ? data[2] : "0";
    }


    
}