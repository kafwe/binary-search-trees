import java.util.Scanner;
import java.io.File;

public class VaccineArrayApp {
    private VaccineArray data;
    private String[] countries;
    private int numCountries;
    private String date;


    private VaccineArrayApp() {
        data = new VaccineArray();
        countries = new String[10000];
        numCountries = 0;
    }

    public void readFile(String path) {
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

    public static void main(String[] args) {
        VaccineArrayApp app = new VaccineArrayApp();
        app.readFile("data/vaccinations.csv");
        app.userInterface();

        System.out.println("Results:");
        
        for (String country : app.countries) {
            if (country == null) break;
            Vaccine vaccine = new Vaccine(country, app.date);
            Vaccine found = app.data.find(vaccine);
            String vaccinations = (found == null) ? "<Not Found>" : 
            Integer.toString(found.getVaccinations());
            System.out.println(country + " = " + vaccinations);
        } 
    }
     
}