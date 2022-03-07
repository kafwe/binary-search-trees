/**
 * A class to store all vaccination data and provide certain operations
 * 
 * @author Jordy Kafwe
 * @version 02/25/22
 */

public class VaccineArray {
    private Vaccine[] data;
    private int records;
    private int opCount;


    /**
     * Constructs a VaccineArray object capable of storing all the 
     * entries in the CSV file.
     */
    public VaccineArray() {
        data = new Vaccine[10000]; // 9919 entries in csv file
        records = 0;
        opCount = 0;
    }

    /**
     * Adds a Vaccine object to the array
     * 
     * @param vaccine the vaccine to add
     */
    public void add(Vaccine vaccine) {
        data[records] = vaccine; 
        records++;
    }

    /**
    * Returns the number of key comparison operations performed by the
    * VaccineArray.
    * 
    *  @return the integer representing the number of key comparisons 
    */
    public int getOpCount() {
        return opCount;
    }

    /**
    * Sets the number of key comparison operations to the given integer.
    * 
    * @param opCount the integer representing the number of key comparisons
    */
    public void setOpCount(int opCount) {
        this.opCount = opCount;
    }

    /**
     * Searches the VaccineArray for the given vaccine
     * 
     * @param vaccine the vaccine to look for in the array
     * @return the vaccine object that has the vaccinations for the country 
     * or null if it is not found
     */
    public Vaccine find(Vaccine vaccine) {
        for (int i = 0; i < records; i++) {
            boolean isEqual = vaccine.compareTo(data[i]) == 0;
            opCount++;
            if (isEqual) {
                return data[i];
            }   
        }
        return null;
    }    

}