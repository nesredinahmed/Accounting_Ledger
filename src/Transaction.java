import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Transaction {
    private static final String FILE_NAME = "transactions.csv";

    // Add a deposit
    public static void addDeposit(String description, String vendor, double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        addTransaction(description, vendor, amount);
    }

    // Make a payment (negative amount)
    public static void makePayment(String description, String vendor, double amount) {
        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return;
        }
        addTransaction(description, vendor, -amount);
    }

    // Internal method to write a transaction to file
    private static void addTransaction(String description, String vendor, double amount) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String transaction = String.format("%s|%s|%s|%s|%.2f",
                    date.format(DateTimeFormatter.ISO_DATE),
                    time.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                    description,
                    vendor,
                    amount);
            writer.write(transaction);
            writer.newLine();
            System.out.println("Transaction recorded.");
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read all transactions
    public static void showAll() {
        System.out.println("\nAll Transactions:");
        readTransactionsByType("ALL");
    }

    // Read only deposits (amount > 0)
    public static void showDeposits() {
        System.out.println("\nDeposits:");
        readTransactionsByType("DEPOSIT");
    }

    // Read only payments (amount < 0)
    public static void showPayments() {
        System.out.println("\nPayments:");
        readTransactionsByType("PAYMENT");
    }

    // Internal method to read and filter transactions
    private static void readTransactionsByType(String type) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            List<String> transactions = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    double amount = Double.parseDouble(parts[4]);

                    if (type.equals("ALL")
                            || (type.equals("DEPOSIT") && amount > 0)
                            || (type.equals("PAYMENT") && amount < 0)) {
                        transactions.add(line);
                    }
                }
            }
            Collections.reverse(transactions); // newest entries first
            transactions.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    // Prompt user for a new deposit
    public static void promptForDeposit(Scanner scanner) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");

        try {
            double amount = Double.parseDouble(scanner.nextLine());
            addDeposit(description, vendor, amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Transaction canceled.");
        }
    }

    // Prompt user for a new payment
    public static void promptForPayment(Scanner scanner) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");

        try {
            double amount = Double.parseDouble(scanner.nextLine());
            makePayment(description, vendor, amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Transaction canceled.");
        }
    }
}