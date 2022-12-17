package exercise1;

public class Account {
    // instance variables
    private double money = 0;
    private final String accountName;

    // constructor to get initial money
    public Account(String accountName, double money) throws Exception {
        if (money < 0)
            throw new Exception("The initial value of money must be greater than $0.00");

        this.money = money;
        this.accountName = accountName;
        System.out.println(toString() + "\n");
    }

    // method to add deposit in the bank
    public synchronized void deposit(double money) throws Exception {
        if (money < 0){
            throw new Exception("\u001B[33m" +"Depositing...\n" + "\u001B[0m" + toString() + "\n" + "\u001B[91m" + "Depositing " + String.format("$%,.2f", money) + "failed. Please deposit money greater than $0.00!\n" + "\u001B[0m");
        }

        this.money += money;
        System.out.println("\u001B[33m" + "Depositing...\n" + "\u001B[0m" + "Account Name: " + accountName +
                "\nA deposit of " + String.format("$%,.2f", money) + " has been completed successfully!\n" +
                "New Balance: " + String.format("$%,.2f", this.money) + "\n");
    }

    // method to withdraw from the bank
    public synchronized void withdraw(double money) throws Exception {
        if (money > this.money){
            throw new Exception("\u001B[32m" +"Withdrawing...\n" + "\u001B[0m" + toString() + "\n" + "\u001B[91m" + "Withdrawing " + String.format("$%,.2f", money) + " failed. Not enough balance!\n" + "\u001B[0m");
        }

        this.money -= money;
        System.out.println("\u001B[32m" + "Withdrawing...\n" + "\u001B[0m"+ "Account Name: " + accountName +
                "\nA withdraw of " + String.format("$%,.2f", money) + " has been completed successfully!\n" +
                "New Balance: " + String.format("$%,.2f", this.money) + "\n");
    }

    @Override
    public String toString() {
        return "Account Name: " + accountName + "\nAccount Balance: " + String.format("$%,.2f", money);
    }
}
