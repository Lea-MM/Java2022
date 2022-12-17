package exercise2;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Lotto lotto = new Lotto();
        lotto.inputNumber();

        // display the values of the lotto array
        StringBuilder lottoNumbers = new StringBuilder("The lotto numbers are ");
        StringBuilder sumOfLottoNumbers = new StringBuilder("\nThe sum of lotto numbers is ");

        int[] lottoArray = lotto.returnLottoNumbers();
        int sum = 0;
        for (int j : lottoArray) {
            lottoNumbers.append(j);
            lottoNumbers.append(" ");
            sum += j;
        }
        JOptionPane.showMessageDialog(null, lottoNumbers.append(sumOfLottoNumbers.append(sum)));
    }
}
