package ass2;
import java.util.Formatter;
import java.util.Scanner;

public class Preserve extends FoodItem {
    private int jarSize;

    @Override
    public boolean addItem(Scanner scanner, boolean fromFile) {
        super.addItem(scanner, fromFile);
        System.out.print("Enter the size of the jar in milliliters: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid entry");
            scanner.next();
            System.out.print("Enter the size of the jar in milliliters: ");
        }
        jarSize = scanner.nextInt();
        return true;
    }

    @Override
    public void outputItem(Formatter writer) {
        super.outputItem(writer);
        writer.format("%d\n", jarSize);
    }

    @Override
    public String toString() {
        return super.toString() + " size: " + jarSize + "mL";
    }

    @Override
    public void addItemSpecificFields(Scanner scanner) {
        System.out.print("Enter the size of the jar in milliliters: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid entry");
            scanner.next();
            System.out.print("Enter the size of the jar in milliliters: ");
        }
        jarSize = scanner.nextInt();
    }
}
