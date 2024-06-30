package ass2;
import java.util.Formatter;
import java.util.Scanner;

public class Vegetables extends FoodItem {
    private String farmName;

    @Override
    public boolean addItem(Scanner scanner, boolean fromFile) {
        super.addItem(scanner, fromFile);
        System.out.print("Enter the name of the farm supplier: ");
        scanner.nextLine();
        farmName = scanner.nextLine();
        return true;
    }

    @Override
    public void outputItem(Formatter writer) {
        super.outputItem(writer);
        writer.format("%s\n", farmName);
    }

    @Override
    public String toString() {
        return super.toString() + " farm supplier: " + farmName;
    }

    @Override
    public void addItemSpecificFields(Scanner scanner) {
        System.out.print("Enter the name of the farm supplier: ");
        farmName = scanner.nextLine();
    }
}
