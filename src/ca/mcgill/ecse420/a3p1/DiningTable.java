/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians 
 * 
 * This is the class containing the main method for question 3.1
 * The class uses 5 Philosopherp2 and chop stick instances to simulate
 * the dining philosopher problem. No 2 philosophers may hold the same
 * chop stick at the same time. However, deadlock and starvation are
 * NOT accounted for. 
 **/
package ca.mcgill.ecse420.a3p1;

public class DiningTable {

	public static void main(String[] args) {

		// Number of chop sticks is always equal to number of philosophers
		final int CHOPSTICKS = 5;
		final int PHILOSOPHERS = CHOPSTICKS;

		Philosopher[] philosophers = new Philosopher[PHILOSOPHERS];
		Chopsticks[] chopsticks = new Chopsticks[CHOPSTICKS];

		for (int i = 0; i < CHOPSTICKS; i++) { // Initialize all chop stick
												// instances

			chopsticks[i] = new Chopsticks(i);

		}

		for (int i = 0; i < PHILOSOPHERS; i++) { // Initialize all philosopher
													// instances

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
