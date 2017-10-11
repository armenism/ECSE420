/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians ID: 260586139
 * 
 * This class is used to simulate the philosophers around the dining table.
 * Each philosopher waits on the lowest index chopstick first, then the next
 * chop tick.This prevents deadlocks but may NOT prevent starvation. 
 **/

package ca.mcgill.ecse420.a1.q33;

import java.util.Random;

public class Philosopherp3 extends Thread {

	private Chopsticksp3 rightChop;
	private Chopsticksp3 leftChop;

	private int index; // unique philosopher index on table
	private int count; // Check for starvation
	private boolean state; // true if eating, false if thinking
	

	/**
	 * 
	 * @param number
	 *            The unique index of the philosopher on the table
	 * @param leftChop
	 *            The instance of the chop stick to the left of the philosopher
	 * @param rightChop
	 *            The instance of the chop stick to the right of the philosopher
	 */
	public Philosopherp3(int number, Chopsticksp3 leftChop, Chopsticksp3 rightChop) {

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

		int chew = sleep();

		// Wait until lowest index chop stick is released
		while (!leftChop.take(index))
			thinking();

		// Wait until next chop stick is available
		while (!rightChop.take(index))
			thinking();
		
		state = true;
		count++;

		System.out.println("Phil " + (index + 1) + " is eating. Count: " + count);

		try { // Simulates philosopher eating
			Thread.sleep(chew);
		} catch (InterruptedException ex) {

			System.out.println("ERROR: Thread " + (index + 1) + " could not execute sleep");
		}

		leftChop.letGo(index); // release both chop sticks
		rightChop.letGo(index);

	}

	/**
	 * This method is used to simulate the philosopher thinking by sleeping the
	 * thread for a random number of hours the thread for a random number of ms
	 */
	public void thinking() {
		int think = sleep();
		state = false;
		System.out.println("Phil " + (index + 1) + " is thinking. Count: " + count);

		// Simulates philosopher thinking
		try {
			Thread.sleep(think);
		} catch (InterruptedException ex) {

			System.out.println("ERROR: Thread " + (index + 1) + " could not execute sleep");
		}

	}

	/**
	 * @return random number between 1500 & 500 to sleep thread
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
