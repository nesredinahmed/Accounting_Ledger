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

//            if(option.equals("1")){
//                filterMonthToDate();
//            }
//            else  if(option.equals("2")){
//                filterPreviousMonth();
//            }
//            else if(option.equals("3")){
//                filterYearToDate();
//            }
//            else if(option.equals("4")){
//                filterPreviousYear();
//            }
//            else if(option.equals("5")){
//                System.out.print("Enter vendor name: ");
//                String vendor = scanner.nextLine();
//                filterByVendor(vendor);
//            }
//            else if(option.equals("0")){
//                break;
//            }
//            else {
//                System.out.println("Invalid option. Please try again.");
//            }

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
        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = today.withDayOfMonth(1);
        filterByDateRange(firstOfMonth, today);
    }

    private void filterPreviousMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfLastMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastOfLastMonth = YearMonth.from(firstOfLastMonth).atEndOfMonth();
        filterByDateRange(firstOfLastMonth, lastOfLastMonth);
    }

    private void filterYearToDate() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfYear = today.withDayOfYear(1);
        filterByDateRange(firstOfYear, today);
    }

    private void filterPreviousYear() {
        LocalDate today = LocalDate.now();
        LocalDate firstOfLastYear = today.minusYears(1).withDayOfYear(1);
        LocalDate lastOfLastYear = firstOfLastYear.withDayOfYear(firstOfLastYear.lengthOfYear());
        filterByDateRange(firstOfLastYear, lastOfLastYear);
    }

    private void filterByVendor(String vendor) {
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