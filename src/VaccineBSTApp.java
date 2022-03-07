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


    private VaccineBSTApp() {
        tree = new BinarySearchTree<>();
    }

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

    private String getResult(Vaccine vaccine) {
        BinaryTreeNode<Vaccine> found = this.tree.find(vaccine);
        String vaccinations = (found == null) ? "<Not Found>" : 
        Integer.toString(found.data.getVaccinations());
        String result = vaccine.getCountry() + " = " + vaccinations;
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
            String result = this.getResult(new Vaccine(country, date));
            results += result + "\n";
            country = input.nextLine();
        }

        System.out.println("Results:");
        System.out.println(results.strip());

    }

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