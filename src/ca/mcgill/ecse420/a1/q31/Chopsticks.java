/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians, ID: 260586139
 * <p>
 * This class is used to represent the chop sticks. The class
 * allows a chop stick to be taken, or released. These methods prevent
 * two philosophers from obtaining the same chop stick.
 * Starvation is NOT accounted for.
 **/
package ca.mcgill.ecse420.a1.q31;

public class Chopsticks {

	private boolean inUse;
	public int chopIndex; // The chop stick index

	/**
	 * @param num The unique index of the chop stick on the table
	 */
	public Chopsticks(int num) {
		chopIndex = num;
		inUse = false;

	}

	/**
	 * This method is used to set the state of a chop stick. The method ensures
	 * that no two philosophers take the same chop stick at the same time.
	 *
	 * @param philIndex The index of the philosopher attempting to take the chop stick
	 * @return true if chop stick is successfully taken, or false if chop stick
	 * is in use.
	 */
	public synchronized boolean take(int philIndex) {
		if (inUse) { // Failed to take chop stick

			System.out.println("Phil " + (philIndex + 1) + " could not take chop stick " + (chopIndex + 1));
			return (!inUse);

		} else {

			inUse = true;
			System.out.println("Chopstick " + (chopIndex + 1) + " in use by phil " + (philIndex + 1));
			return inUse;
		}
	}

	/**
	 * The method is used by philosophers to release a chop stick when
	 * appropriate.
	 *
	 * @param philIndex The index of the philosopher releasing the chop stick
	 */
	public synchronized void letGo(int philIndex) {

		inUse = false;
		System.out.println("Chopstick " + (chopIndex + 1) + " released by phil " + (philIndex + 1));

	}

}
