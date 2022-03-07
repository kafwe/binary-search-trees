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
    private VaccineArray array;
    
    private VaccineArrayApp() {
        array = new VaccineArray();
    }
     
    private void readFile(String path) {
        try {
            Scanner reader = new Scanner(new File(path));

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.isEmpty()) {
                    array.add(new Vaccine(line));
                }
            }
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getResult(Vaccine vaccine) {
        Vaccine found = this.array.find(vaccine);
        String vaccinations = (found == null) ? "<Not Found>" : 
        Integer.toString(found.getVaccinations());
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
            String result = getResult(new Vaccine(country, date));
            results += result + "\n"; 
            country = input.nextLine();
        }

        System.out.println("Results:");
        System.out.println(results.strip());
    }

    private void writeOpCount(String filename, int opCount) {
        File file = new File("data/array/" + filename + ".txt");
        
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
            array.setOpCount(0); 
            getResult(new Vaccine(line));
            int searchCount = array.getOpCount();
            writeOpCount(filename, searchCount);
            line = input.nextLine();
        }
    }
    public static void main(String[] args) {
        VaccineArrayApp app = new VaccineArrayApp();
        app.readFile("data/vaccinations.csv");
        boolean isExperiment = args.length == 1; 

        if (!isExperiment) {
            app.userInterface(); 
        } else {
            String filename = args[0];
            int insertCount = app.array.getOpCount();
            app.writeOpCount(filename, insertCount);
            app.experiment(filename);   
        }
    }
     
}