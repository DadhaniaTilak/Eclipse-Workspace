package ass2;
import java.util.Scanner;

public class Assign2 {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            while (!scanner.hasNextInt()) {
                System.out.println("...Invalid input, please try again...");
                displayMenu();
                scanner.next();
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    inventory.addItem(scanner, false);
                    break;
                case 2:
                    inventory.displayInventory();
                    break;
                case 3:
                    inventory.updateQuantity(scanner, true);
                    break;
                case 4:
                    inventory.updateQuantity(scanner, false);
                    break;
                case 5:
                    inventory.searchForItem(scanner);
                    break;
                case 6:
                    inventory.saveToFile(scanner);
                    break;
                case 7:
                    inventory.readFromFile(scanner);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("...Invalid input, please try again...");
                    break;
            }
        } while (choice != 8);
    }

    public static void displayMenu() {
        System.out.println("Please select one of the following:");
        System.out.println("1: Add Item to Inventory");
        System.out.println("2: Display Current Inventory");
        System.out.println("3: Buy Item(s)");
        System.out.println("4: Sell Item(s)");
        System.out.println("5: Search for Item");
        System.out.println("6: Save Inventory to File");
        System.out.println("7: Read Inventory from File");
        System.out.println("8: To Exit");
        System.out.print("> ");
    }
}
