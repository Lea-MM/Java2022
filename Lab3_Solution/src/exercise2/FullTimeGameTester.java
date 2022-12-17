package exercise2;

public class FullTimeGameTester extends GameTester {

    // defines the instance variables
    private final double baseSalary = 3000;


    // defines the constructor
    public FullTimeGameTester(String gameName, boolean isStatus) {
        super(gameName, isStatus);
    }


    // implements the abstract class
    @Override
    public double calculateSalary(double numberOfHours) {
        return baseSalary;
    }

}
