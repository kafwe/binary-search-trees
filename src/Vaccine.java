/**
 * A class to represent the daily vaccination number of a country
 * 
 * @author Jordy Kafwe
 * @version 02/25/22
 */

public class Vaccine implements Comparable<Vaccine> {

    private String country;
    private String date;
    private int vaccinations;

    /**
     * Constructs a Vaccine object by parsing a line from the CSV file.
     * The data on the line is in the order: country, date, vaccinations.
     * 
     * Where there the number of vaccinations are missing, it is set to 0.
     * 
     * @param line a line of the CSV file 
     */
    public Vaccine(String line) {
        String[] data = line.split(",");
        country = data[0];
        date = data[1];
        vaccinations = data.length == 3 ? Integer.parseInt(data[2]) : 0;
    }

    /**
     * Constructs a Vaccine object with the given country and date. 
     * The vaccinations are set to 0.
     * 
     * @param country the country which the vaccinations are from 
     * @param date the date these vaccinations took place
     */
    public Vaccine(String country, String date) {
        this.country = country;
        this.date = date;
        vaccinations = 0;
    }

    /**
     * Returns the country
     * 
     * @return the String representing the country where the vaccinations are from
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the date
     * 
     * @return the String representing the date the vaccinations took place
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the number of vaccinations
     * 
     * @return the integer representing the number of vaccinations for the country on a given date
     */
    public int getVaccinations() {
        return vaccinations;
    }

    /**
     * Concatenates the country and date to form a unique key
     * 
     * @return the key of the Vaccine object
     */
    public String key() {
        return country + date; 
    }

    /**
     * Compares this object with the specified object for order. 
     * 
     * @param otherVaccine the object to be compared.
     *
     * @return a negative integer, zero, or a positive integer 
     * as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Vaccine otherVaccine) {
        return this.key().compareTo(otherVaccine.key());
    }

    /**
     * Returns a string representation of a Vaccine object
     */
    @Override
    public String toString() {
        return country + " = " + vaccinations;
    }

}