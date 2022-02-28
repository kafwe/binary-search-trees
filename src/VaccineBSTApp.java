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
    
}