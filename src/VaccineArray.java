/**
 * A class to hold all vaccination data and provide certain operations
 * 
 * @author Jordy Kafwe
 * @version 02/25/22
 */

public class VaccineArray {
    Vaccine[] data = new Vaccine[10000];
    int records = 0;

    public void add(Vaccine vaccine) {
        data[records] = vaccine; 
        records++;
    }

    /**
     * Searches the data (Vaccine array) for the given vaccine
     * 
     * @param vaccine the vaccine to look for in the array
     * @return the vaccine object that has the vaccinations for the country 
     * or null if it is not found
     */
    public Vaccine find(Vaccine vaccine) {
        for (int i = 0; i < records; i++) {
            boolean isEqual = vaccine.compareTo(data[i]) == 0;
            if (isEqual) {
                return data[i];
            }   
        }
        return null;
    }

}