import java.util.Scanner;

public class Ledger {
    public void ledgerHome() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("==================================");
            System.out.println("=========LEDGER ACCOUNT===========");
            System.out.println("==================================");
            System.out.println("A) Show All Transactions");
            System.out.println("D) Show Deposits Only");
            System.out.println("P) Show Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Go Back to Home");
            System.out.print("Choose an option: ");

            String ledgerOption = scan.nextLine();

            if (ledgerOption.equalsIgnoreCase("A")) {
                Transaction.showAll();

            } else if (ledgerOption.equalsIgnoreCase("D")) {
                Transaction.showDeposits();

            } else if (ledgerOption.equalsIgnoreCase("P")) {
                Transaction.showPayments();

            } else if (ledgerOption.equalsIgnoreCase("R")) {
                Reports reports = new Reports();
                reports.reportMenu(scan);

            } else if (ledgerOption.equalsIgnoreCase("H")) {
                break;

            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}