package exercise1;

import java.util.Scanner;

public class Transaction implements Runnable{

    Account account;

    public Transaction(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        // performing withdraw operation
        try {
            account.withdraw(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // performing deposit operation
        try{
            account.deposit(20);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
