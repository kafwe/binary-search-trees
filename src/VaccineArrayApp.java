import java.util.ArrayList;
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


    public static void main(String[] args) {

    }
     
}