import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {

                System.out.println("==================================");
                System.out.println("============ ACCOUNT =============");
                System.out.println("==================================");
                System.out.println("D) Add Deposit");
                System.out.println("P) Make Payment (Debit)");
                System.out.println("L) Ledger");
                System.out.println("X) Exit");
                System.out.print("Please select an option: ");

                String choice = scan.nextLine();

                if (choice.equalsIgnoreCase("D")) {
                    System.out.println("You selected: Add Deposit");
                    Transaction.promptForDeposit(scan);

                } else if (choice.equalsIgnoreCase("P")) {
                    System.out.println("You selected: Make Payment");
                    Transaction.promptForPayment(scan);

                } else if (choice.equalsIgnoreCase("L")) {
                    Ledger ledger = new Ledger();
                    ledger.ledgerHome();

                } else if (choice.equalsIgnoreCase("X")) {
                    System.out.println("Thank you for using the Ledger App. Goodbye!");
                    System.out.println("░░░░░░▄▄▄░░▄██▄░░░\n" +
                            "░░░░░▐▀█▀▌░░░░▀█▄░░░\n" +
                            "░░░░░▐█▄█▌░░░░░░▀█▄░░\n" +
                            "░░░░░░▀▄▀░░░▄▄▄▄▄▀▀░░\n" +
                            "░░░░▄▄▄██▀▀▀▀░░░░░░░\n" +
                            "░░░█▀▄▄▄█░▀▀░░\n" +
                            "░░░▌░▄▄▄▐▌▀▀▀░░\n" +
                            "▄░▐░░░▄▄░█░▀▀ ░░ \n" +
                            "▀█▌░░░▄░▀█▀░▀ ░░\n" +
                            "░░░░░░░▄▄▐▌▄▄░░░\n" +
                            "░░░░░░░▀███▀█░▄░░\n" +
                            "░░░░░░▐▌▀▄▀▄▀▐▄░░\n" +
                            "░░░░░░▐▀░░░░░░▐▌░░\n" +
                            "░░░░░░█░░░░░░░░█░░░\n" +
                            "░░░░░▐▌░░░░░░░░░█░░");
                    break;

                } else {
                    System.out.println("Invalid input. Please try again.");
                }

        }
    }
}