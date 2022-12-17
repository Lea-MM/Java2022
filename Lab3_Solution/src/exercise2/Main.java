package exercise2;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        // defines the option to choose for a game tester
        String[] options = {"Full-Time Game Tester", "Part-Time Game Tester"};

        // method to ask for the user's name
        String gameTesterName = getGameTesterName();

        // method to ask the user to select a game tester type
        int gameTesterType = getGameTesterType(options);

        // check what type of tester
        if(gameTesterType == 0){
            FullTimeGameTester fullTimeGameTester = new FullTimeGameTester(gameTesterName, true);
            double salary = fullTimeGameTester.calculateSalary(0);
            displayInfo(gameTesterName, options[0], salary);
        }
        else {
            PartTimeGameTester partTimeGameTester = new PartTimeGameTester(gameTesterName, false);
            double salary = 0;

            // check if user input is greater than 0
            try{
                salary = partTimeGameTester.calculateSalary(getNumberOfHours());
            }
            catch (InvalidNumberOfHoursException e){
                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            // displays the information of the game tester
            displayInfo(gameTesterName, options[1], salary);
        }
    }


    // method to ask for user's name
    public static String getGameTesterName(){
        return JOptionPane.showInputDialog(
                null,
                "Enter your name"
        );
    }


    // method to ask user for a game tester type
    public static int getGameTesterType(String[] options){

        return JOptionPane.showOptionDialog(
                null,
                "Select the type of game tester",
                "Game Testers",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null
        );

    }


    // method to ask user for number of hours
    public static double getNumberOfHours(){

        // defines the local variables
        boolean isParsed = false;
        double numOfHours = 0;

        // checks the user input
        do {

            String numOfHoursInString = JOptionPane.showInputDialog(
                    null,
                    "Enter the number of hours worked"
            );

            // try to parse the string to get the amount in double
            try{
                numOfHours = Integer.parseInt(numOfHoursInString);
                isParsed = false;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(
                        null,
                        "The conversion between string to integer has failed. Please check your input.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                isParsed = true;
            }

        } while (isParsed);

        return numOfHours;
    }


    // method to display the game tester information
    public static void displayInfo(String gameTesterName, String typeOfGameTester, double salary){
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

        JOptionPane.showMessageDialog(
                null,
                "Game Tester Name: " + gameTesterName + "\nGame Tester Type: " + typeOfGameTester + "\nSalary: " + dollarFormat.format(salary),
                "Info",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

}
