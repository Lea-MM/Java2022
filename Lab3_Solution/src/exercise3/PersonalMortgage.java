package exercise3;

public class PersonalMortgage extends Mortgage{

    // constructor to initialize the mortgage
    public PersonalMortgage(int mortgageNumber, String customerName, double amountOfMortgage, double interestRate, int term) throws Exception {
        super(mortgageNumber, customerName, amountOfMortgage, 0.02 + interestRate, term);
    }

}
