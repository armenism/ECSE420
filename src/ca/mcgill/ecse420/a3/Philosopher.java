package ca.mcgill.ecse420.a3;

public class Philosopher extends Thread {

	private Chopsticks rightChop;
	private Chopsticks leftChop;

	private int individualNumber; // This is to identify individual philosophers
	private boolean state; // True if eating, false if thinking

	private final int SLEEP = 1000;

	public Philosopher(int number, Chopsticks leftChop, Chopsticks rightChop) {

		state = false;
		individualNumber = number;

		this.rightChop = rightChop;
		this.leftChop = leftChop;

	}

	public void tryToEat() {

		// Try to take both chopsticks
		if (!rightChop.inUse) {
			if (!leftChop.inUse) {

				leftChop.take(individualNumber);
				rightChop.take(individualNumber);

				state = true;

				System.out.println("Philosopher " + individualNumber + " is eating");

				// Simulates philosopher eating
				try {
					Thread.sleep(SLEEP);
				} catch (InterruptedException ex) {

					System.out.println("ERROR: Thread " + individualNumber + " could not execute sleep");
				}

				leftChop.letGo(individualNumber);
				rightChop.letGo(individualNumber);
			}

		}

	}

	public void thinking() {

		state = false;
		System.out.println("Philosopher " + individualNumber + " is thinking");

		// Simulates philosopher thinking
		try {
			Thread.sleep(SLEEP);
		} catch (InterruptedException ex) {

			System.out.println("ERROR: Thread " + individualNumber + " could not execute sleep");
		}

	}

	public void run() {

		while (true) {

			tryToEat();
			thinking();
		}

	}

}
