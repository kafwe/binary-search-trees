import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Class to allow searching through the data using a binary search tree
 * 
 * @author Jordy Kafwe
 * @version 02/26/22
 */

public class VaccineBSTApp {
    private BinarySearchTree<Vaccine> tree;


    /**
     * Constructs a VaccineBSTApp object and initialises the binary search tree 
     * that stores the data.
     */
    private VaccineBSTApp() {
        tree = new BinarySearchTree<>();
    }

    /**
     * Reads in the contents of a CSV file and populates
     * the binary search tree with that data.
     * 
     * @param path the path of the CSV file to read
     */
    private void readFile(String path) {
        try {
            Scanner reader = new Scanner(new File(path));

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.isEmpty()) {
                    tree.insert(new Vaccine(line));
                }
            }
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches the binary search tree for the daily vaccination numbers for a 
     * particular country and on a specific date.
     * 
     * @param vaccine the Vaccine object to search for
     * @return the String containing the country and the daily vaccination number 
     * or <strong><Not Found></strong> where the Vaccine object could not be found
     */
    private String getResult(Vaccine vaccine) {
        BinaryTreeNode<Vaccine> found = this.tree.find(vaccine);
        String vaccinations = (found == null) ? "<Not Found>" : 
        Integer.toString(found.data.getVaccinations());
        String result = vaccine.getCountry() + " = " + vaccinations;
        return result;
    }

    /**
     * Provides a user interface for the user to interact with the program. 
     */
    private void userInterface() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the date:");
        String date = input.nextLine();

        System.out.println("Enter the list of countries (end with an empty line):");
        String country = input.nextLine();
        
        String results = "";

        while (!country.isEmpty()) {
            String result = this.getResult(new Vaccine(country, date));
            results += result + "\n";
            country = input.nextLine();
        }

        System.out.println("Results:");
        System.out.println(results.strip());

    }

    /**
     * Writes an operation count value to a file. 
     * The method does not overwrite the file. It just appends to it. 
     * 
     * @param filename the text file to write the operation count to
     * @param opCount the integer representing the number 
     * of key comparisons performed by the array
     */
    private void writeOpCount(String filename, int opCount) {
        File file = new File("data/bst/" + filename + ".txt");
        
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            writer.write(opCount + "\n");
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Searches for each item in the given subset. 
     * Writes all the operation count values for the operations 
     * performed to find each item to a file 
     * 
     * @param filename the text file to write the operation count to 
     */
    private void experiment(String filename) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine(); 
    
        while (!line.isEmpty()) {
            tree.setOpCount(0); 
            getResult(new Vaccine(line));
            int searchCount = tree.getOpCount();
            writeOpCount(filename, searchCount);
            line = input.nextLine();
        }
    }


    /**
     * Driver method for the class
     * 
     * @param args the first command line argument is the filename of the file 
     * where the operation counts are written. 
     * The presence of this argument also determines whether an experiment 
     * or normal user interface is executed.
     */
    public static void main(String[] args) {
        VaccineBSTApp app = new VaccineBSTApp();
        boolean isExperiment = args.length == 1;
        app.readFile("data/vaccinations.csv");

        if (!isExperiment) {
            app.userInterface();
        } else {
            String filename = args[0];
            int insertCount = app.tree.getOpCount();
            app.writeOpCount(filename, insertCount);
            app.experiment(filename);
        }
    }
} 