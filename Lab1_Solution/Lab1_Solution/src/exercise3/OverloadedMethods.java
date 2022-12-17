package exercise3;

import javax.swing.*;

public class OverloadedMethods {
    public static void main(String[] args) {
        int mean1 = getMeanValue(50, 25);
        System.out.println("The mean value between two numbers is " + mean1);

        int mean2 = getMeanValue(100, 150, 23);
        System.out.println("The mean value between three numbers is " + mean2);

        int mean3 = getMeanValue(new int[] {100, 45, 2, 60, 67, 8, 34});
        System.out.println("The mean value between multiple numbers is " + mean3);
    }

    // method 1
    public static int getMeanValue(int num1, int num2){
        return (num1 + num2) / 2;
    }

    public static int getMeanValue(int num1, int num2, int num3){
        return (num1 + num2 + num3)  / 2;
    }

    public static int getMeanValue(int[] numbers){
        int mean = 0;
        for (int n : numbers){
            mean += n;
        }
        return mean / numbers.length;
    }
}
