package ass2;
import java.util.Scanner;
import java.util.Formatter;

public class FoodItem implements Comparable<FoodItem> {
    private int itemCode;
    private String itemName;
    private int itemQuantityInStock;
    private float itemCost;
    private float itemPrice;

    public FoodItem() {}

    public boolean addItem(Scanner scanner, boolean fromFile) {
        if (!fromFile) {
            System.out.print("Enter the code for the item: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid entry");
                scanner.next();
                System.out.print("Enter the code for the item: ");
            }
            this.itemCode = scanner.nextInt();
        }

        System.out.print("Enter the name for the item: ");
        scanner.nextLine();
        this.itemName = scanner.nextLine();

        System.out.print("Enter the quantity for the item: ");
        while (!scanner.hasNextInt() || (this.itemQuantityInStock = scanner.nextInt()) < 0) {
            System.out.println("Invalid entry");
            scanner.next();
            System.out.print("Enter the quantity for the item: ");
        }

        System.out.print("Enter the cost of the item: ");
        while (!scanner.hasNextFloat() || (this.itemCost = scanner.nextFloat()) < 0) {
            System.out.println("Invalid entry");
            scanner.next();
            System.out.print("Enter the cost of the item: ");
        }

        System.out.print("Enter the sales price of the item: ");
        while (!scanner.hasNextFloat() || (this.itemPrice = scanner.nextFloat()) < 0) {
            System.out.println("Invalid entry");
            scanner.next();
            System.out.print("Enter the sales price of the item: ");
        }

        return true;
    }

    public boolean updateItem(int amount) {
        if (itemQuantityInStock + amount < 0) {
            return false;
        }
        itemQuantityInStock += amount;
        return true;
    }

    public boolean isEqual(FoodItem item) {
        return this.itemCode == item.itemCode;
    }

    public int getItemCode() {
        return itemCode;
    }

    public boolean inputCode(Scanner scanner, boolean fromFile) {
        if (fromFile) {
            this.itemCode = scanner.nextInt();
            return true;
        }

        System.out.print("Enter the code for the item: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid entry");
            scanner.next();
            System.out.print("Enter the code for the item: ");
        }
        this.itemCode = scanner.nextInt();
        return true;
    }

    public void outputItem(Formatter writer) {
        writer.format("%d\n%s\n%d\n%.2f\n%.2f\n", itemCode, itemName, itemQuantityInStock, itemCost, itemPrice);
    }

    @Override
    public String toString() {
        return String.format("Item: %d %s %d price: $%.2f cost: $%.2f", itemCode, itemName, itemQuantityInStock, itemPrice, itemCost);
    }

    @Override
    public int compareTo(FoodItem other) {
        return Integer.compare(this.itemCode, other.itemCode);
    }

    public void addItemSpecificFields(Scanner scanner) {}
}
