package tasks;

public class task02 {
	private int counter = 0;

	public static void main(String[] args) {
		task02 demo = new task02();
		Runnable incrementer = () -> {
			for (int i = 0; i < 100; i++) {
				demo.increment();
			}
		};

		Thread t1 = new Thread(incrementer, "Worker-1");
		Thread t2 = new Thread(incrementer, "Worker-2");
		Thread t3 = new Thread(incrementer, "Worker-3");

		t1.start();
		t2.start();
		t3.start();

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		System.out.println("Final counter value: " + demo.counter);
	}

	private synchronized void increment() {
		counter++;
	}
}
