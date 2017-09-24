package ca.mcgill.ecse420.a1;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestQuestion1 {
	
	Question1 q1;
	
	@Test
	public void testSequentialMultiplyMatrix2000x2000() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 1);

		long startTime = System.currentTimeMillis();
		Question1.sequentialMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tSequential method time: " + (endTime - startTime));
	}
	
	@Test
	public void testParallelMultiplyMatrix2000x2000_1Thread() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 1);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}
	
	@Test
	public void testParallelMultiplyMatrix2000x2000_2Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 2);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}
	
	@Test
	public void testParallelMultiplyMatrix2000x2000_5Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 5);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}
	
	@Test
	public void testParallelMultiplyMatrix2000x2000_10Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 10);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}
	
	@Test
	public void testParallelMultiplyMatrix2000x2000_25Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 25);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}
	
	@Test
	public void testParallelMultiplyMatrix2000x2000_50Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 50);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}
	
	@Test
	public void testParallelMultiplyMatrix2000x2000_100Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 100);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}
	
	@Test
	public void testParallelMultiplyMatrix2000x2000_250Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 250);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}

	@Test
	public void testParallelMultiplyMatrix2000x2000_500Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 500);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}

	@Test
	public void testParallelMultiplyMatrix2000x2000_1000Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 1000);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}
	
	@Test
	public void testParallelMultiplyMatrix2000x2000_1500Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 1500);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}
	
	@Test
	public void testParallelMultiplyMatrix2000x2000_maximumThreads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1 = new Question1(2000, 2000);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1.a, q1.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}
	
}
