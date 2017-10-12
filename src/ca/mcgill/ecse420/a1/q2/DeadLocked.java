package ca.mcgill.ecse420.a1.q2;

public class DeadLocked {

	public static void main(String[] args) {

		// Initialize users and resources
		Resource res1 = new Resource(1);
		Resource res2 = new Resource(2);

		User user1 = new User(1, res1, res2);
		User user2 = new User(2, res2, res1); //user2 will attempt to get the second resource first

		user1.start();
		user2.start();

	}
}

class Resource {

	boolean inUse;
	public int resIndex;

	public Resource(int resIndex) {

		inUse = false;
		this.resIndex = resIndex;

	}

	// try to obtain resource
	public synchronized boolean getResource() {

		if (inUse) {

			return false;

		}
		inUse = true;
		return inUse;

	}

	// Let go of resource
	public synchronized void releaveResource() {

		inUse = false;

	}

}

class User extends Thread {

	private boolean taskComplete;
	private int userIndex;
	private int count;
	Resource firstResource;
	Resource secondResource;

	public User(int userIndex, Resource firstResource, Resource secondResource) {
		this.userIndex = userIndex;
		this.firstResource = firstResource;
		this.secondResource = secondResource;
		taskComplete = false;
		count = 0;
	}

	private void completeTask() {

		while (!taskComplete) {
			// Get both resources to complete task
			System.out.println("User " + userIndex + " is attempting to get resrouce " + firstResource.resIndex);

			if (firstResource.getResource()) {
				System.out.print("User " + userIndex + "has obtained resrouce " + firstResource.resIndex);

				while (!secondResource.getResource()) {
					System.out.println("User " + userIndex + " is attempting to get resrouce " + secondResource.resIndex);

				}
				System.out.println("User " + userIndex + "has obtained resrouce " + secondResource.resIndex);

				taskComplete = true;

			}

		}
		// let go of both resources once task is complete
		firstResource.releaveResource();
		secondResource.releaveResource();

	}

	public void run() {

		while (true) {
			taskComplete = false;
			completeTask();
			count++;
			System.out.println("User " + userIndex + "completed his task " + count + "times!");

		}

	}

}
