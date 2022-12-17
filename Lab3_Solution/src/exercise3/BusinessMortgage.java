package exercise3;

public class BusinessMortgage extends Mortgage {

    // constructor to initialize the mortgage
    public BusinessMortgage(int mortgageNumber, String customerName, double amountOfMortgage, double interestRate, int term) throws Exception {
        super(mortgageNumber, customerName, amountOfMortgage, 0.01 + interestRate, term);
    }

}
