package ca.mcgill.ecse420.a3;

public class Chopsticks {

	public boolean inUse; // True if in use, false otherwise
	public int number; // Used to identify the chop stick

	public Chopsticks(int num) {
		number = num;

	}

	public synchronized boolean take(int num) {
		if(inUse){
			
		System.out.println("Phil " + num + " cannot take chop stick " + number);
		return false;
		
		}else{
			
		inUse = true;
		System.out.println("Chopstick " + number + " in use by philosopher " + num);
		return true;
		}
	}

	public synchronized void letGo(int num) {

		inUse = false;
		System.out.println("Chopstick " + number + " released by philosopher " + num);

	}

}
