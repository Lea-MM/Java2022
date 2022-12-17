package exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {
    public static void main(String[] args) {
        try {
            // create a list for transactions
            List<Transaction> transactionList = new ArrayList<>();

            // adding transactions to the list
            System.out.println("\u001B[36m" + "-- ACCOUNT CREATION --" + "\u001B[0m");
            transactionList.add(new Transaction(new Account("Lea2022", 10)));
            transactionList.add(new Transaction(new Account("Ricky2021", 50)));
            transactionList.add(new Transaction(new Account("Sam2020", 100)));
            transactionList.add(new Transaction(new Account("Wilma2019", 150)));

            // create the executor service to manage the threads
            ExecutorService executorService = Executors.newCachedThreadPool();

            // start the transactions
            System.out.println("\u001B[36m" + "-- ACCOUNT TRANSACTIONS --" + "\u001B[0m");
            for (Transaction transaction:transactionList)
                executorService.execute(transaction);

            executorService.shutdown();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
