/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians, ID: 260586139
 **/

package ca.mcgill.ecse420.a1.q31;

import java.util.Random;

public class Philosopher extends Thread {

	private Chopsticks rightChop;
	private Chopsticks leftChop;

	private int philIndex;
	private int count; // Check for starvation

	private int sleep;

	/**
	 * @param philIndex The unique index of the philosopher on the table
	 * @param leftChop  The instance of the chop stick to the left of the philosopher
	 * @param rightChop The instance of the chop stick to the right of the philosopher
	 */
	public Philosopher(int philIndex, Chopsticks leftChop, Chopsticks rightChop) {

		this.philIndex = philIndex + 1;

		this.rightChop = rightChop;
		this.leftChop = leftChop;

		count = 0;

	}

	/**
	 * This method is used to attempt to grab both chop sticks, and eat (by
	 * sleeping the thread for a random number of ms)
	 */
	public void tryToEat() {

		sleep = sleep();

		if (rightChop.take(philIndex)) { // Try to take both chop sticks
			if (leftChop.take(philIndex)) {

				count++;

				System.out.println("Phil " + philIndex + " is eating. Count: " + count);

				try { // Simulates philosopher eating
					Thread.sleep(sleep);
				} catch (InterruptedException ex) {

					System.out.println("ERROR: Thread " + philIndex + " could not execute sleep");
				}

				leftChop.letGo(philIndex); // release both chop sticks
				rightChop.letGo(philIndex);
			}
		}

	}

	/**
	 * This method is used to simulate the philosopher thinking by sleeping the
	 * thread for a random number of hours the thread for a random number of ms
	 */
	public void thinking() {
		sleep = sleep();

		System.out.println("Phil " + philIndex + " is thinking. Count: " + count);

		// Simulates philosopher thinking
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException ex) {

			System.out.println("ERROR: Thread " + philIndex + " could not execute sleep");
		}

	}

	/**
	 * @return random number between 1000 & 500 to sleep thread
	 */
	private int sleep() {

		Random rand = new Random();
		int random = rand.nextInt(1000) + 500;

		return random;
	}

	public void run() {

		while (true) {

			tryToEat();
			thinking();
		}

	}

}
