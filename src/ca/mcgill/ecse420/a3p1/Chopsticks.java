/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians 
 * 
 * This class is used to represent the chop sticks. The class
 * allows a chop stick to be taken, or released. These methods prevent 
 * two philosophers from obtaining the same chop stick. 
 * Starvation is NOT accounted for.
 **/
package ca.mcgill.ecse420.a3p1;

public class Chopsticks {

	public boolean inUse; // True if in use, false otherwise
	public int number; // The chop stick index

	/**
	 * 
	 * @param num
	 *            The unique index of the chop stick on the table
	 */
	public Chopsticks(int num) {
		number = num;
		inUse = false;

	}

	/**
	 * This method is used to set the state of a chop stick. The method ensures
	 * that no two philosophers take the same chop stick at the same time.
	 * 
	 * @param num
	 *            The index of the philosopher attempting to take the chopstick
	 * @return true if chop stick is successfully taken, or false if chop stick
	 *         is in use.
	 */
	public synchronized boolean take(int num) {
		if (inUse) { // Failed to take chop stick

			System.out.println("Phil " + num + " could not take chop stick " + number);
			return (!inUse);

		} else {

			inUse = true;
			System.out.println("Chopstick " + number + " in use by phil " + num);
			return inUse;
		}
	}

	/**
	 * The method is used by philosophers to release a chop stick when
	 * appropriate.
	 * 
	 * @param num
	 *            The index of the philosopher releasing the chop stick
	 */
	public synchronized void letGo(int num) {

		inUse = false;
		System.out.println("Chopstick " + number + " released by phil " + num);

	}

}
