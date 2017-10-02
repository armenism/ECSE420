package ca.mcgill.ecse420.a3;

import java.util.Scanner;

public class DiningTable {

	private static Scanner scanner;

	public static void main(String[] args) {

		scanner = new Scanner(System.in);

		int num = 0;
		boolean error;

		// Adapt to N philosophers (ask user for # of philosophers)
		do {
			try {
				System.out.println("Select number Philosophers");
				num = scanner.nextInt();
				error = false;
			} catch (Exception e) {
				error = true;
				System.out.println("ERROR: Please enter an integer value");
			}
		} while (error);

		// Dictate the number of philosophers & chop sticks
		final int CHOPSTICKS = num;
		final int PHILOSOPHERS = CHOPSTICKS;

		Philosopher[] philosophers = new Philosopher[PHILOSOPHERS];
		Chopsticks[] chopsticks = new Chopsticks[CHOPSTICKS];

		for (int i = 0; i < CHOPSTICKS; i++) { // Initialize all chopsticks

			chopsticks[i] = new Chopsticks(i);

		}

		for (int i = 0; i < PHILOSOPHERS; i++) { // Initialize all philosophers

			int wrapAround = (i + 1) % PHILOSOPHERS;
			philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[wrapAround]);

		}

		for (int i = 0; i < PHILOSOPHERS; i++) { // Start philosopher threads

			System.out.println("START THREAD " + i);
			Thread t = new Thread(philosophers[i]);
			t.start();

		}

	}

}
