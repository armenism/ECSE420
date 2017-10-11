/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians 
 * 
 * This class is used to represent the chop sticks. The class
 * allows a chop stick to be taken, or released. These methods prevent 
 * two philosophers from obtaining the same chop stick. 
 * Starvation is NOT accounted for.
 **/
package ca.mcgill.ecse420.a1.q33;

public class Chopsticksp3 {

	public boolean inUse; // True if in use, false otherwise
	public int chopIndex; // The chop stick index

	private boolean firstTime;
	private int nextAllowedPhil;
	private int total;
	private int next;

	/**
	 * 
	 * @param num
	 *            The unique index of the chop stick on the table
	 */
	public Chopsticksp3(int num, int total) {
		chopIndex = num;
		firstTime = true;
		inUse = false;

	}

	/**
	 * This method is used to set the state of a chop stick. The method ensures
	 * that no two philosophers take the same chop stick at the same time.
	 * 
	 * @param num
	 *            The index of the philosopher attempting to take the chop stick
	 * @return true if chop stick is successfully taken, or false if chop stick
	 *         is in use.
	 */

	public synchronized boolean take(int philIndex) {

		if (firstTime) {

			if (philIndex == 0) {

				next = -1;

			} else if (philIndex < chopIndex || (total - chopIndex) == 1) {

				next = 1;
			} else {

				next = -1;

			}

			nextAllowedPhil = philIndex;
			firstTime = false;

		}

		if (inUse || nextAllowedPhil != philIndex) { // Failed to take chop
														// stick

			System.out.println("Phil " + (philIndex + 1) + " could not take chop stick " + (chopIndex + 1));
			return false;

		} else { // not in use and permission granted
					// based on turn
			inUse = true;

			// update permission
			nextAllowedPhil = (philIndex % total + next) % total;
			next = -next;

			System.out.println("Chopstick " + (chopIndex + 1) + " in use by phil " + (philIndex + 1));
			return true;
		}

	}

	/**
	 * The method is used by philosophers to release a chop stick when
	 * appropriate.
	 * 
	 * @param num
	 *            The index of the philosopher releasing the chop stick
	 */
	public synchronized void letGo(int philIndex) {

		inUse = false;
		System.out.println("Chopstick " + (chopIndex + 1) + " released by phil " + (philIndex+1));

	}

}
