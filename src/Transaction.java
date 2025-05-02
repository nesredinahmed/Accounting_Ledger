import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Transaction {
    private static final String csvfile = "transactions.csv";

    // Add a deposit
    public static void addDeposit(String description, String vendor, double amount) {
        addTransaction(description, vendor, amount);
    }

    // Make a payment (negative amount)
    public static void makePayment(String description, String vendor, double amount) {
        addTransaction(description, vendor, -amount);
    }

    // Internal method to write a transaction to file
    private static void addTransaction(String description, String vendor, double amount) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvfile, true))) {
            String transaction = String.format("%s|%s|%s|%s|%.2f",
                    date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    time.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                    description,
                    vendor,
                    amount);
            writer.write(transaction);
            writer.newLine();
            System.out.println("Transaction recorded.");
        } catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }

    // Read all transactions
    public static void showAll() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\n==================================");
            System.out.println("==========ALL TRANSACTION=========");
            System.out.println("==================================");
            readTransactionsByType("ALL");
            System.out.println("H) Go Back To Ledger");
            String option = scan.nextLine();
            if (option.equalsIgnoreCase("H")) {
                break;
            } else {
                System.out.println("INVALID OPTION!");
            }
        }

    }

    // Read only deposits (amount > 0)
    public static void showDeposits() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("\n==================================");
            System.out.println("============ DEPOSIT =============");
            System.out.println("==================================");
            readTransactionsByType("DEPOSIT");
            System.out.println("H) Go Back To Ledger");
            String option = scan.nextLine();
            if (option.equalsIgnoreCase("H")) {
                break;
            } else {
                System.out.println("INVALID OPTION!");
            }
        }
    }

    // Read only payments (amount < 0)
    public static void showPayments() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\n==================================");
            System.out.println("============ PAYMENT =============");
            System.out.println("==================================");
            readTransactionsByType("PAYMENT");
            System.out.println("H) GO Back To Ledger");
            String option = scan.nextLine();
            if (option.equalsIgnoreCase("H")) {
                break;
            } else {
                System.out.println("INVALID OPTION");
            }
        }
    }

    //  method to read and filter transactions
    private static void readTransactionsByType(String type) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvfile))) {
            List<String> transactions = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    double amount = Double.parseDouble(parts[4]);

                    if (type.equals("ALL") || (type.equals("DEPOSIT") && amount > 0) || (type.equals("PAYMENT") && amount < 0)) {
                        transactions.add(line);
                    }
                }
            }
            Collections.reverse(transactions); // newest entries first
            transactions.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error reading from file.");
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
            if (amount <= 0) {
                System.out.println("Payment amount must be positive.");
            } else {
                addDeposit(description, vendor, amount);
            }
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
            if (amount <= 0) {
                System.out.println("Payment amount must be positive.");
            } else {
                makePayment(description, vendor, amount);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Transaction canceled.");
        }
    }
}