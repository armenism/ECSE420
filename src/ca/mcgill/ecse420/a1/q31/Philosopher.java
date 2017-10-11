/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians 
 * 
 * This class is used to simulate the philosophers around the dining table.
 * Philosophers have 2 states, eating & thinking. The eating & thinking methods 
 * are not written in a way to prevent deadlocks nor starvation. Each philosopher
 * eats or thinks for a random number of ms.
 **/

package ca.mcgill.ecse420.a1.q31;

import java.util.Random;

public class Philosopher extends Thread {

	private Chopsticks rightChop;
	private Chopsticks leftChop;

	private int index; // unique philosopher index on table
	private int count; // Check for starvation
	private boolean state; // true if eating, false if thinking

	private int sleep;

	/**
	 * 
	 * @param number
	 *            The unique index of the philosopher on the table
	 * @param leftChop
	 *            The instance of the chop stick to the left of the philosopher
	 * @param rightChop
	 *            The instance of the chop stick to the right of the philosopher
	 */
	public Philosopher(int number, Chopsticks leftChop, Chopsticks rightChop) {

		state = false;
		index = number;
		count = 0;

		this.rightChop = rightChop;
		this.leftChop = leftChop;

	}

	/**
	 * This method is used to attempt to grab both chop sticks, and eat (by
	 * sleeping the thread for a random number of ms)
	 */
	public void tryToEat() {

		sleep = sleep();

		if (rightChop.take(index)) { // Try to take both chop sticks
			if (leftChop.take(index)) {

				state = true;
				count++;

				System.out.println("Phil " + (index+1) + " is eating. Count: " + count);

				try { // Simulates philosopher eating
					Thread.sleep(sleep);
				} catch (InterruptedException ex) {

					System.out.println("ERROR: Thread " + (index+1) + " could not execute sleep");
				}

				leftChop.letGo(index); // release both chopsticks
				rightChop.letGo(index);
			}
		}

	}

	/**
	 * This method is used to simulate the philosohper thinking by sleeping the
	 * thread for a random number of hours the thread for a random number of ms
	 */
	public void thinking() {
		sleep = sleep();
		state = false;
		System.out.println("Phil " + (index+1) + " is thinking. Count: " + count);

		// Simulates philosopher thinking
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException ex) {

			System.out.println("ERROR: Thread " + (index+1) + " could not execute sleep");
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
