import java.util.Scanner;
import java.io.File;

/**
 * Class to allow searching through the data using a binary search tree
 * 
 * @author Jordy Kafwe
 * @version 02/26/22
 */

public class VaccineBSTApp {
    private BinarySearchTree<Vaccine> tree;
    private String[] countries;
    private int numCountries; 
    private String date; 

    private VaccineBSTApp() {
        tree = new BinarySearchTree<>();
        countries = new String[10000];
        numCountries = 0;
    }

    public void readFile(String path) {
        try {
            Scanner reader = new Scanner(new File(path));

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.isEmpty()) {
                    Vaccine vaccine = new Vaccine(line);
                    tree.insert(vaccine);
                }
            }
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void userInterface() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the date:");
        date = input.nextLine();

        System.out.println("Enter the list of countries (end with an empty line):");
        String country = input.nextLine(); 

        while (!country.isEmpty()) {
            countries[numCountries] = country;
            numCountries++;
            country = input.nextLine();
        }
    }

    public String getResult(String country) {
        Vaccine vaccine = new Vaccine(country, this.date);
        BinaryTreeNode<Vaccine> found = this.tree.find(vaccine);
        String vaccinations = (found == null) ? "<Not Found>" : 
        Integer.toString(found.data.getVaccinations());
        String result = country + " = " + vaccinations;
        return result;
    }

    public static void main(String[] args) {
        VaccineBSTApp app = new VaccineBSTApp();
        app.readFile("data/vaccinations.csv");
        app.userInterface();

        System.out.println("Results:");
        
        for (String country : app.countries) {
            if (country == null) break;
            String result = app.getResult(country);
            System.out.println(result);  
        } 
    }
}