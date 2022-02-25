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

}