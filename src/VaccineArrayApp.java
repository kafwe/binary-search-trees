import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Class to allow searching through the data using an array
 * 
 * @author Jordy Kafwe
 * @version 02/25/22
 */

public class VaccineArrayApp {
    private VaccineArray data;
    
    private VaccineArrayApp() {
        data = new VaccineArray();
    }

     
    private void readFile(String path) {
        try {
            Scanner reader = new Scanner(new File(path));

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.isEmpty()) {
                    data.add(new Vaccine(line));
                }
            }
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getResult(String country, String date) {
        Vaccine vaccine = new Vaccine(country, date);
        Vaccine found = this.data.find(vaccine);
        //String vaccinations = (found == null) ? "<Not Found>" : 
        //Integer.toString(found.getVaccinations());
       // String result = country + " = " + vaccinations;
       // return result;
    }


    private void userInterface() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the date:");
        String date = input.nextLine();

        System.out.println("Enter the list of countries (end with an empty line):");
        String country = input.nextLine();
        
        String results = "";

        while (!country.isEmpty()) {
            getResult(country, date);
           // results += result + "\n"; 
            country = input.nextLine();
        }

        System.out.println("Results:");
        //System.out.println(results);
    }

    private void writeOpCount(String filename) {
        int opCount = data.getOpCount();
        File file = new File("data/array/" + filename + ".txt");
        
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            writer.write(opCount + "\n");
            writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        VaccineArrayApp app = new VaccineArrayApp();
        app.readFile("data/vaccinations.csv");
        app.userInterface();
        String filename = args[0];
        app.writeOpCount(filename);
    }
     
}