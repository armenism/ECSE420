package ca.mcgill.ecse420.a3;

public class Chopsticks {

	public boolean inUse; // True if in use, false otherwise
	public int number; // Used to identify the chop stick

	public Chopsticks(int num) {
		number = num;

	}

	public synchronized void take(int num) {

		inUse = true;
		System.out.println("Chopstick " + number + " in use by philosopher " + num);

	}

	public synchronized void letGo(int num) {

		inUse = false;
		System.out.println("Chopstick " + number + " released by philosopher " + num);

	}

}
