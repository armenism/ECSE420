package ca.mcgill.ecse420.a3.q2;
import java.util.concurrent.ThreadLocalRandom;

public class Test extends Thread {
	final int MIN = 1;
	final int MAX = 5;
	// Generate a random value to store
	Integer item;
	Integer check;
	int id;

	FineGrainList<Integer> list;

	Test(int id, FineGrainList<Integer> list) {
		this.id = id + 1;
		this.list = list;
		item = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
		check = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
	}

	@Override
	public void run() {

		if (list.add(item)) {
			System.out.println("Thread " + id + " added " + item);

		} else {
			System.out.println("Thread " + id + " could NOT add " + item);

		}

		// Checks a random item in the list
		if (list.contains(check)) {
			System.out.println("Thread " + id + " found " + check);
		} else {
			System.out.println("Thread " + id + " could NOT find " + check);
		}

		if (list.remove(item)) {
			System.out.println("Thread " + id + " removed " + item);

		} else {
			System.out.println("Thread " + id + " could NOT remove " + item);

		}

		if (list.contains(check)) {
			System.out.println("Thread " + id + " found" + check);
		} else {
			System.out.println("Thread " + id + " could NOT find " + check);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final int NUM_THREADS = 10;

		FineGrainList<Integer> myList = new FineGrainList();

		Test[] myThreads = new Test[NUM_THREADS];

		System.out.println("Initializing All Threads");

		for (int i = 0; i < NUM_THREADS; i++) {
			myThreads[i] = new Test(i, myList);
		}
		System.out.println("All Threads Initialized");

		System.out.println("Populating List with Add()");
		for (int i = 0; i < NUM_THREADS; i++) {
			myThreads[i].start();
		}

	}

}
