/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians 
 * 
 * This is the class containing the main method for question 3.1
 * The class uses 5 Philosopherp3 and chop stick instances to simulate
 * the dining philosopher problem. No 2 philosophers may hold the same
 * chop stick at the same time. However, deadlock and starvation are
 * NOT accounted for. 
 **/
package ca.mcgill.ecse420.a1.q31;

public class DiningTable {

	public static void main(String[] args) {

		// Number of chop sticks is always equal to number of philosophers
		final int CHOPSTICKS = 5;
		final int PHILOSOPHERS = CHOPSTICKS;

		Philosopher[] philosophers = new Philosopher[PHILOSOPHERS];
		Chopsticks[] chopsticks = new Chopsticks[CHOPSTICKS];

		for (int i = 0; i < CHOPSTICKS; i++) { // Initialize chop sticks

			chopsticks[i] = new Chopsticks(i);

		}

		for (int i = 0; i < PHILOSOPHERS; i++) { // Initialize philosophers

			int wrapAround = (i + 1) % PHILOSOPHERS;
			philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[wrapAround]);

		}

		System.out.println("*************QUESTION 3.1 - STARTING PHILOSOPHERS*****************");
		for (int i = 0; i < PHILOSOPHERS; i++) { // Start philosopher threads

			Thread t = new Thread(philosophers[i]);
			t.start();

		}

	}

}
