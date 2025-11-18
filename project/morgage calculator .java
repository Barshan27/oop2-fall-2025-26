package lab;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class MortagecalculatorV2 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bangladesh Mortgage Calculator (BDT) ===");

        // Get salary
        double salary = 0;
        while (true) {
            System.out.print("Enter your monthly salary (BDT): ");
            if (scanner.hasNextDouble()) {
                salary = scanner.nextDouble();
                break;
            } else {
                System.out.println("Error digit enter numbers only(eg. 123456789).");
                scanner.next();
            }
        }
// Get credit score
        int creditScore = 0;
        while (true) {
            System.out.print("Enter credit score (0-500): ");
            if (scanner.hasNextInt()) {
                creditScore = scanner.nextInt();
                if (creditScore >= 0 && creditScore <= 500) {
                    break;
                } else {
                    System.out.println("Credit score must be between 0 and 500.");
                }
            } else {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.next();
            }
        }

        // Get criminal record
        boolean hasCriminalRecord = false;
        while (true) {
            System.out.print("Any criminal record? (true/false): ");
            String input = scanner.next().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                hasCriminalRecord = Boolean.parseBoolean(input);
                break;
            } else {
                System.out.println("Please enter 'true' or 'false' only.");
            }
        }

        // Check eligibility
        if (creditScore < 300 || hasCriminalRecord) {
            System.out.println("Not eligible for a loan.");
            System.out.println("Needed Credit score >= 300 and with no criminal record");
            scanner.close();
            return;
        }

        // Get loan amount
        double principal = 0;
        while (true) {
            System.out.print("Enter loan amount (Principal in BDT): ");
            if (scanner.hasNextDouble()) {
                principal = scanner.nextDouble();
                double maxLoan = salary * 2;
                if (principal <= maxLoan) {
                    break;
                } else {
                    System.out.println("Loan amount cannot be more than 2 times your salary ");
                }
            } else {
                System.out.println("Please enter numbers only.");
                scanner.next();
            }
        }

        // Get interest rate
        System.out.print("Enter annual interest rate (e.g., 8.5 for 8.5%): ");
        double annualInterestRate = scanner.nextDouble();

        // Get loan time in year
        System.out.print("Enter loan period (in years): ");
        int years = scanner.nextInt();


        double monthlyInterestRate = (annualInterestRate / 100) / 12;
        int numberOfPayments = years * 12;

        double mortgagePayment = principal
                * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        double totalPayment = mortgagePayment * numberOfPayments;
        double totalInterest = totalPayment - principal;

        // Display results
        Locale bdLocale = Locale.of("en", "BD");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(bdLocale);

        System.out.println("\n=== Mortgage Summary ===");
        System.out.println("Monthly Salary: " + currencyFormatter.format(salary));
        System.out.println("Credit Score: " + creditScore);
        System.out.println("Past or present criminal records? " + (hasCriminalRecord ? "Yes" : "No"));
        System.out.println("Loan Amount: " + currencyFormatter.format(principal));
        System.out.println("Monthly Payment: " + currencyFormatter.format(mortgagePayment));
        System.out.println("Total Payment: " + currencyFormatter.format(totalPayment));
        System.out.println("Total Interest: " + currencyFormatter.format(totalInterest));

        scanner.close();
    }
}
