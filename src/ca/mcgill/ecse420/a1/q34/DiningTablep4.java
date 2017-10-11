/**
 * @author Karim El-Baba, ID: 260582332
 * @author Armen Stepanians 
 * 
 * This is the class containing the main method for question 3.2
 *Starvation is taken care of by forcing each philosopher to take
 * the smallest index chop stick first. That is, for all philosopher, 
 * except the last one, the left chop stick.
 * 
 **/
package ca.mcgill.ecse420.a1.q34;

import ca.mcgill.ecse420.a1.q33.*;
import java.util.Scanner;

public class DiningTablep4 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please enter the number of philosopher/chop sticks for this simulation: \nNumber: ");
		
		// Number of chop sticks is always equal to number of philosophers
		final int CHOPSTICKS = sc.nextInt();
		final int PHILOSOPHERS = CHOPSTICKS;
		final int LAST_PHILOSOPHER = PHILOSOPHERS - 1;

		Philosopherp3[] philosophers = new Philosopherp3[PHILOSOPHERS];
		Chopsticksp3[] chopsticks = new Chopsticksp3[CHOPSTICKS];

		for (int i = 0; i < CHOPSTICKS; i++) { // Initialize chop sticks

			chopsticks[i] = new Chopsticksp3(i, CHOPSTICKS, LAST_PHILOSOPHER);

		}

		for (int i = 0; i < LAST_PHILOSOPHER; i++) { // Initialize philosophers

			philosophers[i] = new Philosopherp3(i, chopsticks[i], chopsticks[i + 1]);

		}

		/*
		 * Flip left & right chop sticks for LAST philosopher so the smallest
		 * index chop stick is checked first
		 */
		philosophers[LAST_PHILOSOPHER] = new Philosopherp3(LAST_PHILOSOPHER, chopsticks[0],
				chopsticks[LAST_PHILOSOPHER]);

		System.out.println("*************QUESTION 3.3 - STARTING PHILOSOPHERS*****************");
		for (int i = 0; i < PHILOSOPHERS; i++) { // Start philosopher threads

			Thread t = new Thread(philosophers[i]);
			t.start();
		}

	}

}
