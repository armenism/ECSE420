/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians, ID: 260586139
 * <p>
 * This class is used to represent the chop sticks. The class
 * allows a chop stick to be taken, or released. These methods prevent
 * two philosophers from obtaining the same chop stick.
 * Starvation is NOT accounted for.
 **/
package ca.mcgill.ecse420.a1.q32;

public class Chopsticksp2 {

	public boolean inUse; // True if in use, false otherwise
	public int chopIndex; // The chop stick index

	/**
	 * @param chopIndex The unique index of the chop stick on the table
	 */
	public Chopsticksp2(int chopIndex) {
		this.chopIndex = chopIndex;
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

			System.out.println("Phil " + philIndex + " could not take chop stick " + chopIndex);
			return false;
		} else {

			inUse = true;
			System.out.println("Chopstick " + chopIndex + " in use by phil " + philIndex);
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
