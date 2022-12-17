package exercise1;

public abstract class Insurance {

    // defines instance variables
    protected String typeOfInsurance;
    protected double monthlyInsuranceCost;

    // defines the getters for the instance variables
    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public double getMonthlyInsuranceCost() {
        return monthlyInsuranceCost;
    }

    // defines the abstract methods
    // sets the cost of the insurance
    abstract public void setInsuranceCost(double monthlyInsuranceCost);

    // displays the info about this insurance
    abstract public void displayInfo();
}
