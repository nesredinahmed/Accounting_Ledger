import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Reports {
    private static String csvfile = "transactions.csv";

    public void reportMenu(Scanner scanner) {
        boolean reportMenu = true;

        while (reportMenu) {
            System.out.println("==================================");
            System.out.println("=============REPORTS==============");
            System.out.println("==================================");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
//            System.out.println("6) Custom Search");
            System.out.println("0) Back to Ledger");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    filterMonthToDate();
                    break;
                case "2":
                    filterPreviousMonth();
                    break;
                case "3":
                    filterYearToDate();
                    break;
                case "4":
                    filterPreviousYear();
                    break;
                case "5":
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    filterByVendor(vendor);
                    break;
//                case "6":
//                    customSearch(scanner);
//                    break;
                case "0":
                    reportMenu = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void filterMonthToDate() {
        while(true){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n==================================");
        System.out.println("========= MONTH TO DATE ==========");
        System.out.println("==================================");
        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = today.withDayOfMonth(1);
        filterByDateRange(firstOfMonth, today);
        System.out.println("H) Go Back To Ledger");
        String option = scan.nextLine();
        if (option.equalsIgnoreCase("H")){
            break;
        }
        else {
            System.out.println("INVALID OPTION!");
        }
    }

    }

    private void filterPreviousMonth() {
        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("\n==================================");
            System.out.println("========= PREVIOUS MONTH =========");
            System.out.println("==================================");
            LocalDate today = LocalDate.now();
            LocalDate firstOfLastMonth = today.minusMonths(1).withDayOfMonth(1);
            LocalDate lastOfLastMonth = YearMonth.from(firstOfLastMonth).atEndOfMonth();
            filterByDateRange(firstOfLastMonth, lastOfLastMonth);
            System.out.println("H) Go Back To Ledger");
            String option = scan.nextLine();
            if (option.equalsIgnoreCase("H")){
                break;
            }
            else {
                System.out.println("INVALID OPTION!");
            }
        }

    }

    private void filterYearToDate() {
        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("\n==================================");
            System.out.println("========== YEAR TO DATE ==========");
            System.out.println("==================================");
            LocalDate today = LocalDate.now();
            LocalDate firstOfYear = today.withDayOfYear(1);
            filterByDateRange(firstOfYear, today);
            System.out.println("H) Go Back To Ledger");
            String option = scan.nextLine();
            if (option.equalsIgnoreCase("H")){
                break;
            }
            else {
                System.out.println("INVALID OPTION!");
            }
        }

    }

    private void filterPreviousYear() {
        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("\n==================================");
            System.out.println("========== PREVIOUS YEAR =========");
            System.out.println("==================================");
            LocalDate today = LocalDate.now();
            LocalDate firstOfLastYear = today.minusYears(1).withDayOfYear(1);
            LocalDate lastOfLastYear = firstOfLastYear.withDayOfYear(firstOfLastYear.lengthOfYear());
            filterByDateRange(firstOfLastYear, lastOfLastYear);
            System.out.println("H) Go Back To Ledger");
            String option = scan.nextLine();
            if (option.equalsIgnoreCase("H")){
                break;
            }
            else {
                System.out.println("INVALID OPTION!");
            }
        }

    }

    private void filterByVendor(String vendor) {

        while(true){
            System.out.println("\n==================================");
            System.out.println("======== SEARCH BY VENDOR ========");
            System.out.println("==================================");
        Scanner scan = new Scanner(System.in);

        LocalDate today = LocalDate.now();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvfile))) {
            List<String> transactions = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5 && parts[3].equalsIgnoreCase(vendor)) {
                    transactions.add(line);
                }
            }
//            for (String t : transactions) {
//                System.out.println(t);
//            }
            transactions.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error reading from file.");
        }
        System.out.println("H) Go Back To Ledger");
        String option = scan.nextLine();
        if (option.equalsIgnoreCase("H")){
            break;
        }
        else {
            System.out.println("INVALID OPTION!");
        }
    }

    }

    public void filterByDateRange(LocalDate startDate, LocalDate endDate) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvfile))) {
            List<String> transactions = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDate transactionDate = LocalDate.parse(parts[0]);
                    if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                        transactions.add(line);
                    }
                }
            }
            Collections.reverse(transactions);
            transactions.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error reading from file.");
        }
    }
}