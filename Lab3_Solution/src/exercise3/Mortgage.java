package exercise3;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class Mortgage implements MortgageConstants{

    // defines the variables
    private final int mortgageNumber;
    private final String customerName;
    private final double amountOfMortgage;
    private final double interestRate;
    private final int term;

    // constructor to initialize the variables
    public Mortgage(int mortgageNumber, String customerName, double amountOfMortgage, double interestRate, int term) throws Exception {

        // checks the inputs if valid
        if (mortgageNumber <= 0)
            throw new Exception("Please enter a valid mortgage number.");

        if (amountOfMortgage > maxMortgageAmount)
            throw new Exception("Mortgage amount over $300,000.00 is not allowed.");

        if (interestRate < 0)
            throw new Exception("Please enter a valid interest rate.");

        this.mortgageNumber = mortgageNumber;
        this.customerName = customerName;
        this.amountOfMortgage = amountOfMortgage;
        this.interestRate = interestRate;
        this.term = (term != shortTerm || term != mediumTerm || term != longTerm) ? 1 : term;
    }

    // method to display the mortgage information
    public void getMortgageInfo(){
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

        JOptionPane.showMessageDialog(
                null,
                "MORTGAGE INFO\nMortgage #: " + mortgageNumber + "\nCustomer Name: " + customerName + "\nAmount of Mortgage: " + dollarFormat.format(amountOfMortgage)
                        + "\nInterest Rate: " + interestRate + "%\nTerm: " + (term == 1 ? "Short-term (1 yr)" : (term == 2 ? "Medium-term (2 yrs)" : "Long-term (5 yrs)")),
                bankName,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
