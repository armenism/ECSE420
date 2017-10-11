/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians 
 * 
 * This is the class containing the main method for question 3.2
 *Starvation is taken care of by forcing each philosopher to take
 * the smallest index chop stick first. That is, for all philosopher, 
 * except the last one, the left chop stick.
 * 
 **/
package ca.mcgill.ecse420.a1.q33;

public class DiningTablep3 {

	public static void main(String[] args) {

		// Number of chop sticks is always equal to number of philosophers
		final int CHOPSTICKS = 5;
		final int PHILOSOPHERS = CHOPSTICKS;
		final int FINAL_PHILOSOPHER = PHILOSOPHERS - 1;

		Philosopherp3[] philosophers = new Philosopherp3[PHILOSOPHERS];
		Chopsticksp3[] chopsticks = new Chopsticksp3[CHOPSTICKS];

		for (int i = 0; i < CHOPSTICKS; i++) { // Initialize chop stick
												// instances

			chopsticks[i] = new Chopsticksp3(i, CHOPSTICKS);

		}

		for (int i = 0; i < PHILOSOPHERS - 1; i++) { // Initialize
														// philosopher
														// instances

			philosophers[i] = new Philosopherp3(i, chopsticks[i], chopsticks[i + 1]);

		}

		// Flip left and right chop sticks for final philosopher so that the
		// smallest index chop stick is checked first
		philosophers[FINAL_PHILOSOPHER] = new Philosopherp3(FINAL_PHILOSOPHER, chopsticks[0],
				chopsticks[FINAL_PHILOSOPHER]);

		System.out.println("STARTING PHILOSOPHERS");
		for (int i = 0; i < PHILOSOPHERS; i++) { // Start philosopher threads

			Thread t = new Thread(philosophers[i]);
			t.start();
		}

	}

}