package exercise3;

import com.sun.source.tree.TryTree;

import javax.swing.*;

public class ProcessMortgage {

    public static void main(String[] args) {
        // initialize the options for user to choose
        String[] options = {"Personal Mortgage", "Business Mortgage"};

        // variable to store the 3 mortgages
        Mortgage[] mortgages = new Mortgage[3];
        int mortgageCount = 0;


        // prompt user to ask for the current interest rate
        double interestRate = -1;
        do {
            String interestRateString = JOptionPane.showInputDialog(null, "Enter the current interest rate");
            try {
                interestRate = Double.parseDouble(interestRateString);   // check if input is number
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "Invalid input! Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (interestRate == -1);


        // get other information
        do {
            // ask for mortgage type
            int mortgageType = JOptionPane.showOptionDialog(
                    null,
                    "Select the type of mortgage",
                    "Type of Mortgage",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    null
            );

            // ask mortgage number and parse it to the equivalent type
            int mortgageNumber = 0;
            do {
                String mortgageNumberString = JOptionPane.showInputDialog(null, "Enter the mortgage number.");
                    try {
                        mortgageNumber = Integer.parseInt(mortgageNumberString);
                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Invalid input! Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
            } while(mortgageNumber <= 0);


            // ask for customer name
            String customerNameString;
            do {
                customerNameString = JOptionPane.showInputDialog(null, "Enter your name.");
            } while(customerNameString == null || customerNameString.equals(""));


            // ask for the amount of mortgage and parse it to the equivalent type
            double amountOfMortgage = 0;
            do {
                String amountOfMortgageString = JOptionPane.showInputDialog(null, "Enter the amount of mortgage.");
                try {
                    amountOfMortgage = Double.parseDouble(amountOfMortgageString);
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Invalid input! Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } while(amountOfMortgage < 0);


            // ask for the term and parse it to the equivalent type
            int term = 0;
            do {
                String termString = JOptionPane.showInputDialog(null, "Enter the mortgage term.");
                try {
                    term = Integer.parseInt(termString);
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Invalid input! Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } while(term < 0);


            // create the right instance based on the type
            if (mortgageType == 0){
                try{
                    mortgages[mortgageCount] = new PersonalMortgage(mortgageNumber, customerNameString, amountOfMortgage, interestRate, term);
                    JOptionPane.showMessageDialog(null, "Mortgage added successfully!");
                }
                catch (Exception e){
                    mortgageCount--;
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                try {
                    mortgages[mortgageCount] = new BusinessMortgage(mortgageNumber, customerNameString, amountOfMortgage, interestRate, term);
                    JOptionPane.showMessageDialog(null, "Mortgage added successfully!");
                }
                catch (Exception e){
                    mortgageCount--;
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

            mortgageCount++;
        } while (mortgageCount < 3);

        for (Mortgage m:mortgages) {
            m.getMortgageInfo();
        }
    }
}
