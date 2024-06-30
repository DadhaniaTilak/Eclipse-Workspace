package ass2;
import java.util.Formatter;
import java.util.Scanner;

public class Fruits extends FoodItem {
    private String orchardName;

    @Override
    public boolean addItem(Scanner scanner, boolean fromFile) {
        super.addItem(scanner, fromFile);
        System.out.print("Enter the name of the orchard supplier: ");
        scanner.nextLine();
        orchardName = scanner.nextLine();
        return true;
    }

    @Override
    public void outputItem(Formatter writer) {
        super.outputItem(writer);
        writer.format("%s\n", orchardName);
    }

    @Override
    public String toString() {
        return super.toString() + " orchard supplier: " + orchardName;
    }

    @Override
    public void addItemSpecificFields(Scanner scanner) {
        System.out.print("Enter the name of the orchard supplier: ");
        orchardName = scanner.nextLine();
    }
}
