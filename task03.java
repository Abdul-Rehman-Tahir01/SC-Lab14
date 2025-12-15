package tasks;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class task03 {
    public static void main(String[] args) {
        List<String> sharedList = new CopyOnWriteArrayList<>();

        Runnable writer = () -> {
            for (int i = 0; i < 3; i++) {
                String item = Thread.currentThread().getName() + "-Item" + i;
                sharedList.add(item);
                System.out.println(Thread.currentThread().getName() + " added: " + item);
                sleepQuietly(100);
            }
        };

        Runnable reader = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " reading list: " + sharedList);
                sleepQuietly(150);
            }
        };

        Thread writer1 = new Thread(writer, "Writer-1");
        Thread writer2 = new Thread(writer, "Writer-2");
        Thread reader1 = new Thread(reader, "Reader-1");
        Thread reader2 = new Thread(reader, "Reader-2");

        writer1.start();
        writer2.start();
        reader1.start();
        reader2.start();

        try {
            writer1.join();
            writer2.join();
            reader1.join();
            reader2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nFinal list contents: " + sharedList);
        System.out.println("Total items: " + sharedList.size());
    }

    private static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
