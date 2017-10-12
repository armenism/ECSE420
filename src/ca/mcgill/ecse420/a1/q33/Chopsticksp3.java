/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians, ID: 260586139
 * <p>
 * This class is used to represent the chop sticks. The class
 * allows a chop stick to be taken, or released. These methods prevent
 * two philosophers from obtaining the same chop stick.
 * Starvation is NOT accounted for.
 **/
package ca.mcgill.ecse420.a1.q33;

public class Chopsticksp3 {

	public boolean inUse; // True if in use, false otherwise
	public int chopIndex; // The chop stick index

	private boolean firstTime; // first time chop stick being grabbed
	private int nextAllowedPhil;
	private int total;
	private int next;

	private final int LAST_PHILOSOPHER;

	/**
	 * @param chopIndex The unique index of the chop stick on the table
	 */
	public Chopsticksp3(int chopIndex, int total, final int LAST_PHILOSOPHER) {
		this.chopIndex = chopIndex;
		this.total = total;
		this.LAST_PHILOSOPHER = LAST_PHILOSOPHER;

		firstTime = true;
		inUse = false;
		next = -1;

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

		/*
		 * Initial conditions set to allow calculations of next allowed
		 * philosopher in line. This if statement is only used the first time a
		 * philosopher attempts to get the chop stick
		 */
		if (firstTime) {

			if (philIndex == 0) {
				next = -1;
			} else if (philIndex < chopIndex || philIndex == LAST_PHILOSOPHER) {
				next = 1;
			}

			nextAllowedPhil = philIndex;
			firstTime = false; // Only enter this if structure once

		}

		if (inUse || nextAllowedPhil != philIndex) { // Failed to take chop
			// stick

			System.out.println("Phil " + philIndex + " could not take chop stick " + chopIndex);
			return false;

		} else {
			inUse = true;

			/*
			 * Calculate next philosopher allowed to use chop stick based on
			 * current.
			 */
			nextAllowedPhil = ((philIndex % total + next) % total);

			if (nextAllowedPhil == -1) {
				nextAllowedPhil = LAST_PHILOSOPHER;
			}
			next = -next;

			System.out.println(
					"Chopstick " + chopIndex + " in use by phil " + philIndex + " NextAllowed: " + nextAllowedPhil);
			return true;
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
		System.out.println("Chopstick " + chopIndex + " released by phil " + philIndex);

	}

}
