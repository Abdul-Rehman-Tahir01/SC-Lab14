package tasks;

import java.util.Random;

public class task04 {
    private int balance = 1000;
    private Random random = new Random();

    public static void main(String[] args) {
        task04 account = new task04();
        System.out.println("Initial Balance: " + account.getBalance());

        Runnable client = () -> {
            for (int i = 0; i < 5; i++) {
                int amount = account.random.nextInt(100) + 1;
                if (account.random.nextBoolean()) {
                    account.deposit(amount);
                    System.out.println(Thread.currentThread().getName() + " deposited: " + amount + 
                                       " | Balance: " + account.getBalance());
                } else {
                    account.withdraw(amount);
                    System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + 
                                       " | Balance: " + account.getBalance());
                }
                sleepQuietly(100);
            }
        };

        Thread client1 = new Thread(client, "Client-1");
        Thread client2 = new Thread(client, "Client-2");
        Thread client3 = new Thread(client, "Client-3");

        client1.start();
        client2.start();
        client3.start();

        try {
            client1.join();
            client2.join();
            client3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nFinal Balance: " + account.getBalance());
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to withdraw " + 
                               amount + " but insufficient funds!");
        }
    }

    public synchronized int getBalance() {
        return balance;
    }

    private static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
