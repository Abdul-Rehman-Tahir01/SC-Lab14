package tasks;

public class task01 {
	public static void main(String[] args) {
		Runnable printNumbers = () -> {
			for (int i = 1; i <= 10; i++) {
				System.out.println("Number: " + i);
				sleepQuietly(200);
			}
		};

		Runnable printSquares = () -> {
			for (int i = 1; i <= 10; i++) {
				System.out.println("Square: " + (i * i));
				sleepQuietly(200);
			}
		};

		Thread numbersThread = new Thread(printNumbers, "NumbersThread");
		Thread squaresThread = new Thread(printSquares, "SquaresThread");

		numbersThread.start();
		squaresThread.start();
	}

	private static void sleepQuietly(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
