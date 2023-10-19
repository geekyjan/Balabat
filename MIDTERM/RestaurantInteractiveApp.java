import java.util.TreeMap;
import java.util.Map;
import java.util.Scanner;

public class RestaurantInteractiveApp {
    public static void main(String[] args) {
        Map<String, Double> menu = new TreeMap<>();
        menu.put("C1", 100.00);
        menu.put("C2", 150.00);
        menu.put("C3", 200.00);

        Map<String, Double> addons = new TreeMap<>();
        addons.put("R1", 35.00);
        addons.put("R2", 50.00);

        int totalQuantity = 0;
        Map<String, Integer> order = new TreeMap<>(); // item name and quantity

        Scanner scan = new Scanner(System.in);

        System.out.println("Restaurant Interactive Application");
        int option = 0;
        while (option != 3) {
            System.out.println("\n[1] Order \n[2] Checkout \n[3] Exit");
            
            if (scan.hasNextInt()) {
               option = scan.nextInt();
               scan.nextLine();
            } else {
               System.out.println("Invalid option!");
               scan.next();
               continue;
            }

            switch (option) {
                case 1:
                    while (true) {
                        System.out.println("\nMenu:");
                        displayMenu(menu);
                        System.out.println("\nAdd Ons:");
                        displayMenu(addons);

                        System.out.print("\nEnter item code: ");
                        String choice = scan.nextLine().trim().toUpperCase();

                        if (menu.containsKey(choice) || addons.containsKey(choice)) {
                            System.out.print("Enter quantity: ");
                            int quantity = scan.nextInt();
                            scan.nextLine(); // Consume the newline character

                            if (order.containsKey(choice))
                                order.put(choice, order.get(choice) + quantity);
                            else
                                order.put(choice, quantity);

                            totalQuantity += quantity;

                            System.out.print("\nDo you want to add more items? (y/n) ");
                            String contOrder = scan.nextLine().trim().toLowerCase();

                            if (!contOrder.equals("y"))
                                break;
                        } else
                            System.out.println("Invalid item code!");
                    }
                    break;

                case 2:
                    if (order.isEmpty())
                        System.out.println("You haven't added an item yet!");
                    else {
                        displayOrderDetails(order, menu, addons, totalQuantity);
                        System.exit(0);
                    }
                    break;

                case 3:
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
        scan.close(); // close the scanner
    }

    // Method to display the menu
    private static void displayMenu(Map<String, Double> menu) {
        for (Map.Entry<String, Double> entry : menu.entrySet()) {
            System.out.printf("%s - Php %.2f\n", entry.getKey(), entry.getValue());
        }
    }
    
    // Method to display order details
    private static void displayOrderDetails(Map<String, Integer> order, Map<String, Double> menu, Map<String, Double> addons, int totalQuantity) {
        double totalAmount = calculateTotalAmount(order, menu, addons);

        System.out.println("\nORDER DETAILS");
        System.out.printf("\n%-15s %-20s %-10s %-15s", "ITEM", "PRICE", "QTY", "TOTAL PRICE");
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            double price = menu.containsKey(item) ? menu.get(item) : addons.get(item);
            double totalPrice = price * quantity;
            System.out.printf("\n%-15s Php %-16.2f %-10d Php %-15.2f", item, price, quantity, totalPrice);
        }
        System.out.println("\n\nTOTAL ITEM/S: " + totalQuantity);
        double totalInDollars = totalAmount * 0.018; // As of 10/03/2023
        System.out.printf("TOTAL PRICE : Php %.2f --> $%.2f\n", totalAmount, totalInDollars);
        System.out.println("\nThanks for ordering!");
    }

    // Method to calculate the total amount
    private static double calculateTotalAmount(Map<String, Integer> order, Map<String, Double> menu, Map<String, Double> addons) {
        double totalAmount = 0.0;

        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            double price = menu.containsKey(item) ? menu.get(item) : addons.get(item);
            totalAmount += price * quantity;
        }
        return totalAmount;
    }
}
