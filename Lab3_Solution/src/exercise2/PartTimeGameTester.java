package exercise2;

public class PartTimeGameTester extends GameTester {

    // defines the instance variable
    private final double baseSalary = 20;


    // defines the constructor
    public PartTimeGameTester(String gameName, boolean isStatus) {
        super(gameName, isStatus);
    }


    // implements the abstract class
    @Override
    public double calculateSalary(double numberOfHours) throws InvalidNumberOfHoursException {

        if (numberOfHours < 0)
            throw new InvalidNumberOfHoursException("Please enter a valid hours of worked!");
        else if (numberOfHours == 0)
            return 0;
        else
            return (numberOfHours * baseSalary);

    }
}
