public class morgage calculator {
    import java.util.Scanner;

public class MortgageCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print ("Enter principal amount: ");
        double principal = input.nextDouble();

        System .out. print ("Enter annual interest rate (in %): ");
        double annualRate = input.nextDouble();

        System .out. print("Enter loan term in years: ");
        int years = input.nextInt();

    
        double monthlyRate = (annualRate / 100) / 12;
        int numberOfPayments = years * 12;

        
        double monthlyPayment = principal * 
                (monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) /
                (Math.pow(1 + monthlyRate, numberOfPayments) - 1);

        System.out.printf("\nMonthly Payment: %.2f\n\n", monthlyPayment);

        double balance = principal;

        System.out.println("Month\tPayment\t\tInterest\tPrincipal\tBalance");
        System.out.println("------------------------------------------------------------");

        for (int month = 1; month <= numberOfPayments; month++) {
            double interest = balance * monthlyRate;
            double principalPaid = monthlyPayment - interest;
            balance -= principalPaid;

            if (balance < 0) balance = 0; // prevent negative rounding

            System.out.printf("%d\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\n",
                    month, monthlyPayment, interest, principalPaid, balance);
        }

        input.close();
    }
}
}
