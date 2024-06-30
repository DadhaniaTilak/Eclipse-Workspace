package ass2;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Inventory {
    private ArrayList<FoodItem> inventory = new ArrayList<>();

    public boolean addItem(Scanner scanner, boolean fromFile) {
        FoodItem item = null;
        if (!fromFile) {
            System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
            String type = scanner.next();
            if (type.equalsIgnoreCase("f")) {
                item = new Fruits();
            } else if (type.equalsIgnoreCase("v")) {
                item = new Vegetables();
            } else if (type.equalsIgnoreCase("p")) {
                item = new Preserve();
            } else {
                System.out.println("Invalid entry");
                return false;
            }
        } else {
            String type = scanner.next();
            if (type.equalsIgnoreCase("f")) {
                item = new Fruits();
            } else if (type.equalsIgnoreCase("v")) {
                item = new Vegetables();
            } else if (type.equalsIgnoreCase("p")) {
                item = new Preserve();
            }
        }

        if (item.addItem(scanner, fromFile)) {
            item.addItemSpecificFields(scanner);
            int index = manualBinarySearch(item.getItemCode());
            if (index < 0) {
                inventory.add(-index - 1, item);
            } else {
                System.out.println("Item code already exists");
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean updateQuantity(Scanner scanner, boolean isBuy) {
        System.out.print("Enter the code for the item: ");
        int code = 0;
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid entry");
            scanner.next();
            System.out.print("Enter the code for the item: ");
        }
        code = scanner.nextInt();

        System.out.print("Enter valid quantity: ");
        int quantity = 0;
        while (!scanner.hasNextInt() || (quantity = scanner.nextInt()) < 0) {
            System.out.println("Invalid entry");
            scanner.next();
            System.out.print("Enter valid quantity: ");
        }

        int index = manualBinarySearch(code);
        if (index >= 0) {
            if (isBuy) {
                return inventory.get(index).updateItem(quantity);
            } else {
                return inventory.get(index).updateItem(-quantity);
            }
        } else {
            System.out.println("Error...could not update item");
            return false;
        }
    }

    private int manualBinarySearch(int code) {
        int left = 0;
        int right = inventory.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midCode = inventory.get(mid).getItemCode();
            if (midCode == code) {
                return mid;
            } else if (midCode < code) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -(left + 1);
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty");
        } else {
            System.out.println("Inventory:");
            for (FoodItem item : inventory) {
                System.out.println(item.toString());
            }
        }
    }

    public void searchForItem(Scanner scanner) {
        System.out.print("Enter the code for the item: ");
        int code = scanner.nextInt();
        int index = manualBinarySearch(code);
        if (index >= 0) {
            System.out.println(inventory.get(index));
        } else {
            System.out.println("Item not found");
        }
    }

    public void saveToFile(Scanner scanner) {
        System.out.print("Enter the filename to save to: ");
        String filename = scanner.next();
        try (Formatter writer = new Formatter(new File(filename))) {
            writer.format("%d\n", inventory.size());
            for (FoodItem item : inventory) {
                writer.format(item.getClass().getSimpleName().charAt(0) + " ");
                item.outputItem(writer);
            }
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    public void readFromFile(Scanner scanner) {
        System.out.print("Enter the filename to read from: ");
        String filename = scanner.next();
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            int numItems = fileScanner.nextInt();
            fileScanner.nextLine();
            for (int i = 0; i <= numItems; i++) {
                String type = fileScanner.next();
                FoodItem item;
                if (type.equalsIgnoreCase("f")) {
                    item = new Fruits();
                } else if (type.equalsIgnoreCase("v")) {
                    item = new Vegetables();
                } else if (type.equalsIgnoreCase("p")) {
                    item = new Preserve();
                } else {
                    System.out.println("Invalid item type in file");
                    continue;
                }
                item.inputCode(fileScanner, true);
                item.addItem(fileScanner, true);
                item.addItemSpecificFields(fileScanner);
                inventory.add(item);
            }
            System.out.println("Inventory read from file");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file");
        }
    }
}
