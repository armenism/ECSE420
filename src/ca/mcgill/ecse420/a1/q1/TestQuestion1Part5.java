package ca.mcgill.ecse420.a1.q1;

import org.junit.Test;

public class TestQuestion1Part5 {

	Question1 q1p5;

	@Test
	public void testSequentialMultiplyMatrix10x10() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(10, 1);

		long startTime = System.nanoTime();
		Question1.sequentialMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.nanoTime();

		System.out.println("\tSequential method time: " + (endTime - startTime));
	}

	@Test
	public void testSequentialMultiplyMatrix20x20() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(20, 1);

		long startTime = System.nanoTime();
		Question1.sequentialMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.nanoTime();

		System.out.println("\tSequential method time: " + (endTime - startTime));
	}

	@Test
	public void testSequentialMultiplyMatrix50x50() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(50, 1);

		long startTime = System.currentTimeMillis();
		Question1.sequentialMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tSequential method time: " + (endTime - startTime));
	}

	@Test
	public void testSequentialMultiplyMatrix100x100() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(100, 1);

		long startTime = System.currentTimeMillis();
		Question1.sequentialMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tSequential method time: " + (endTime - startTime));
	}

	@Test
	public void testSequentialMultiplyMatrix200x200() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(200, 1);

		long startTime = System.currentTimeMillis();
		Question1.sequentialMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tSequential method time: " + (endTime - startTime));
	}

	@Test
	public void testSequentialMultiplyMatrix400x400() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(400, 1);

		long startTime = System.currentTimeMillis();
		Question1.sequentialMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tSequential method time: " + (endTime - startTime));
	}

	@Test
	public void testSequentialMultiplyMatrix1000x1000() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(1000, 1);

		long startTime = System.currentTimeMillis();
		Question1.sequentialMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tSequential method time: " + (endTime - startTime));
	}

	@Test
	public void testParallelMultiplyMatrix10x10_10Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(10, 10);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}

	@Test
	public void testParallelMultiplyMatrix20x20_10Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(20, 10);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}

	@Test
	public void testParallelMultiplyMatrix50x50_10Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(50, 10);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}

	@Test
	public void testParallelMultiplyMatrix100x100_10Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(100, 10);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}

	@Test
	public void testParallelMultiplyMatrix200x200_10Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(200, 10);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}

	@Test
	public void testParallelMultiplyMatrix400x400_10Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(400, 10);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}

	@Test
	public void testParallelMultiplyMatrix1000x1000_10Threads() {

		System.out.println("\n" + Thread.currentThread().getStackTrace()[1] + ":");

		q1p5 = new Question1(1000, 10);

		long startTime = System.currentTimeMillis();
		Question1.parallelMultiplyMatrix(q1p5.a, q1p5.b);
		long endTime = System.currentTimeMillis();

		System.out.println("\tParallel method time: " + (endTime - startTime));
	}

}
