package exercise2;

import javax.swing.*;
import java.util.Random;

public class Lotto {
    private static int[] lottoNumbers = new int[3];

    private  static int countOfPlayedLotto;

    public Lotto (){
        Random random = new Random();

        for (int i = 0; i < lottoNumbers.length; i++) {
            if (lottoNumbers[i] == 0){
                int randomNumber;
                do {
                    randomNumber = random.nextInt(10);
                    lottoNumbers[i] = randomNumber;
                } while (randomNumber == 0);
            }
        }
    }

    public void inputNumber(){
        int inputNumber = -1;

        do {
            String userInput = JOptionPane.showInputDialog("Enter a number between 3 and 27");

            try {
                inputNumber = Integer.parseInt(userInput);

                if (inputNumber < 4 || inputNumber > 28){
                    inputNumber = -1;
                    throw new Exception();
                }
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Please enter a valid integer number between 3 and 27!");
            }
        } while (inputNumber == -1);

        countOfPlayedLotto++;

        checkSum(inputNumber);
    }

    private void checkSum(int inputNumber){
        int sum = 0;
        for (int n : lottoNumbers){     // sum of the lotto numbers
            sum += n;
        }

        if (inputNumber == sum){
            JOptionPane.showMessageDialog(null, "Congratulations! You won.");
        }
        else{
            if (countOfPlayedLotto < 5){
                JOptionPane.showMessageDialog(null, "You still have " + (5 - countOfPlayedLotto) + " number of chances. Please try again.");

                new Lotto();
                inputNumber();
            }
            else {
                JOptionPane.showMessageDialog(null, "You run out of chances. Computer wins!");
            }
        }
    }

    public int[] returnLottoNumbers(){
        return lottoNumbers;
    }
}
