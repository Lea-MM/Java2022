package exercise1;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.Locale;

public class Life extends Insurance {

    @Override
    public void setInsuranceCost(double monthlyInsuranceCost) {
        super.typeOfInsurance = "Life";
        super.monthlyInsuranceCost = monthlyInsuranceCost;
    }

    // implements the displayInfo method of the superclass
    @Override
    public void displayInfo() {
        // create formatter given the locale
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

        JOptionPane.showMessageDialog(
                null,
                "You have a " + super.getTypeOfInsurance() + " insurance"
                        + "\nYour total monthly cost is " + dollarFormat.format(super.getMonthlyInsuranceCost()),
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

}
