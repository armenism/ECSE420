package ca.mcgill.ecse420.a3;

public class DiningTable {

	public static void main(String[] args) {

		// Dictate the number of philosophers & chop sticks
		final int CHOPSTICKS = 30;
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
