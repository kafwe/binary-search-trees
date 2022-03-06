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

    private VaccineBSTApp() {
        tree = new BinarySearchTree<>();
    }

    private void readFile(String path) {
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


    private String getResult(String country, String date) {
        Vaccine vaccine = new Vaccine(country, date);
        BinaryTreeNode<Vaccine> found = this.tree.find(vaccine);
        String vaccinations = (found == null) ? "<Not Found>" : 
        Integer.toString(found.data.getVaccinations());
        String result = country + " = " + vaccinations;
        return result;
    }


    private void userInterface() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the date:");
        String date = input.nextLine();

        System.out.println("Enter the list of countries (end with an empty line):");
        String country = input.nextLine();
        
        String results = "";

        while (!country.isEmpty()) {
            String result = getResult(country, date);
            results += result + "\n"; 
            country = input.nextLine();
        }

        System.out.println("Results:");
        System.out.println(results);
    }


    public static void main(String[] args) {
        VaccineBSTApp app = new VaccineBSTApp();
        app.readFile("data/vaccinations.csv");
        app.userInterface();
    }
}