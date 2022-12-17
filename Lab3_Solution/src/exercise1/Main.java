package exercise1;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame();

        // initialize the options for user to choose
        String[] options = {"Health", "Life"};

        // creates an array of insurance
        ArrayList<Insurance> insurances = new ArrayList<>();

        // checks if the user wants to exit
        boolean isExit;
        do {

            // ask user to select the type of insurance
            int typeOfInsurance = JOptionPane.showOptionDialog(
                    null,
                    "Select the type of your insurance",
                    "Type of Insurance",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    null
            );

            // ask user to enter the amount of insurance
            boolean isParsed;
            double monthlyInsuranceCost = 0d;
            do {

                String monthlyCostInString = JOptionPane.showInputDialog(jFrame, "Enter the amount of your monthly insurance");

                try{
                    monthlyInsuranceCost = Double.parseDouble(monthlyCostInString);    // try to parse the string to get the amount in double
                    isParsed = false;
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(
                            jFrame,
                            "The conversion between string to double has failed. Please check your input.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    isParsed = true;
                }

            } while (isParsed);


            // add the object to the array
            insurances.add(createObject(typeOfInsurance, monthlyInsuranceCost));

            // checks if the user wants to continue or exit
            isExit = doContinue();

        } while (isExit);


        // create formatter given the locale
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        JOptionPane.showMessageDialog(
                null,
                "*** Insurance Information ***",
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );

        // iterates through the array and display the information of the insurance
        for (int i = 0; i < insurances.size(); i++) {
            // sending setInsuranceCost messages to each object
            insurances.get(i).setInsuranceCost(999.99);

            JOptionPane.showMessageDialog(
                    null,
                    "Type of insurance: " + insurances.get(i).getTypeOfInsurance()
                            + "\nMonthly cost: " + dollarFormat.format(insurances.get(i).getMonthlyInsuranceCost()) + "\n",
                    "Insurance Info " + (i + 1),
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }


    // method to create the appropriate object
    public static Insurance createObject(int typeOfInsurance, double monthlyInsuranceCost){

        if(typeOfInsurance == 0){
            Health healthInsurance = new Health();
            healthInsurance.setInsuranceCost(monthlyInsuranceCost); // sets the type of insurance and the cost
            healthInsurance.displayInfo();  // displays the information about the insurance
            return healthInsurance; // returns the object
        }
        else{
            Life lifeInsurance = new Life();
            lifeInsurance.setInsuranceCost(monthlyInsuranceCost);   // sets the type of insurance and the cost
            lifeInsurance.displayInfo();    // displays the information about the insurance
            return lifeInsurance;  // returns the object
        }

    }


    // method to ask user to exit or continue
    public static boolean doContinue(){
        int exitOrContinue = JOptionPane.showOptionDialog(
                null,
                "Do you want to add another insurance?",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] {"Yes, add another insurance type.", "No, exit now."},
                null
        );

        return exitOrContinue == 0;
    }

}
