import java.util.Scanner;
import java.io.File;

/**
 * Class to allow searching through the data using an array
 * 
 * @author Jordy Kafwe Kioni
 * @version 02/25/22
 */

public class VaccineArrayApp {
    private VaccineArray array;
    private boolean isExperiment;
    
    /**
     * Constructs a VaccineArrayApp object and initialise a VaccineArray object 
     * that stores the data
     */
    public VaccineArrayApp() {
        array = new VaccineArray();
    }
     
    /**
     * Reads in the contents of a CSV file and populates
     * the VaccineArray with that data.
     * 
     * @param path the path of the CSV file to read
     */
    public void readInFile(String path) {
        try {
            Scanner reader = new Scanner(new File(path));

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.isEmpty()) {
                    array.add(new Vaccine(line));
                }
                if (isExperiment) {
                    int insertCount = array.getOpCount();
                    System.out.println(insertCount);
                }
            }
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches the array for the daily vaccination numbers for a 
     * particular country and on a specific date.
     * 
     * @param vaccine the Vaccine object to search for
     * @return the String containing the country and the daily vaccination number 
     * or <strong><Not Found></strong> where the Vaccine object could not be found
     */
    public String getResult(Vaccine vaccine) {
        Vaccine found = array.find(vaccine);
        String result = (found == null) ? vaccine.getCountry() + " = <Not Found>" 
        : found.toString();
        return result;
    }

    /**
     * Provides a user interface for the user to interact with the program. 
     */
    public void userInterface() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the date:");
        String date = input.nextLine();

        System.out.println("Enter the list of countries (end with an empty line):");
        String country = input.nextLine();
        
        String results = "";

        while (!country.isEmpty()) {
            String result = getResult(new Vaccine(country, date));
            results += result + "\n"; 
            country = input.nextLine();
        }

        System.out.println("Results:");
        System.out.println(results.strip());
    }

    /**
     * Searches for each item in the given subset. 
     * Writes all the operation count values for the operations 
     * performed to find each item to a file (using unix output redirection)
     */
    public void experiment() {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine(); 
    
        while (!line.isEmpty()) {
            getResult(new Vaccine(line));
            int searchCount = array.getOpCount();
            System.out.println(searchCount);
            line = input.nextLine();
        }
    }

    /**
     * Driver method for the class
     * 
     * @param args the first command line argument determines whether an experiment 
     * or normal user interface is executed.
     */
    public static void main(String[] args) {
        VaccineArrayApp app = new VaccineArrayApp();
        app.isExperiment = args.length == 1;
        app.readInFile("data/vaccinations.csv");

        if (app.isExperiment) {
            app.experiment();
        } else {
            app.userInterface();
        }
    }
     
}