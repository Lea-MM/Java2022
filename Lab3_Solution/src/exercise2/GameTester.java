package exercise2;

public abstract class GameTester {

    // defines the instance fields
    private String gameTesterName;
    private boolean isStatus;


    // constructor to initialize the field
    public GameTester(String gameName, boolean isStatus) {
        this.gameTesterName = gameName;
        this.isStatus = isStatus;
    }


    // abstract method to calculate salary of a game tester
    abstract public double calculateSalary(double numberOfHours) throws InvalidNumberOfHoursException;


    // getters for the gameName and isStatus
    protected String getGameTesterName() {
        return gameTesterName;
    }

    protected String getStatus(){
        if (isStatus){
            return "Full-Time Game Tester";
        }
        else{
            return "Part-Time Game Tester";
        }
    }
}
