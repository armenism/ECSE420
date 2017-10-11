package ca.mcgill.ecse420.a1.q1;

import java.util.Random;
import java.util.concurrent.*;

public class Question1 {

	double[][] a;
	double[][] b;
	static int threads = 0;

	public Question1(int size, int numberOfThreads) {

		a = generateMatrix(size, size);
		b = generateMatrix(size, size);

		threads = numberOfThreads;
	}

	public static double[][] generateMatrix(int row, int column) {

		double[][] generatedMatrix = new double[row][column];
		Random random = new Random();

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < column; c++) {
				generatedMatrix[r][c] = random.nextInt(10);
			}
		}

		return generatedMatrix;
	}

	public static double[][] sequentialMultiplyMatrix(double[][] a, double[][] b) {

		double[][] result = new double[a.length][b[0].length];
		int iterationResult = 0;

		for (int rowA = 0; rowA < a.length; rowA++) {
			for (int columnB = 0; columnB < b[0].length; columnB++) {
				for (int columnA = 0; columnA < a[0].length; columnA++) {
					iterationResult += a[rowA][columnA] * b[columnA][columnB];
				}
				result[rowA][columnB] = iterationResult;
				iterationResult = 0;
			}
		}

		return result;
	}

	public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) {

		if (threads > a.length) {
			System.out.println("\n\tCannot have more than " + a.length + " threads for this matrix");
			System.out.println("\tChanging thread numbers to " + a.length);
			System.out.println();
			threads = a.length;
		}

		double[][] result = new double[a.length][b[0].length];

		ExecutorService executorService = Executors.newFixedThreadPool(a.length);

		for (int threadIndex = 0; threadIndex < threads; threadIndex++) {
			int startRow = (int) ((threadIndex * 1.0 / threads) * a.length);
			int endRow = (int) (((threadIndex * 1.0 + 1) / threads) * a.length);
			executorService.execute(new ParallelMultiply(a, b, result, startRow, endRow));
		}

		executorService.shutdown();

		while (!executorService.isTerminated()) {
		}

		return result;
	}

	public static class ParallelMultiply implements Runnable {

		private double[][] a;
		private double[][] b;
		private double[][] result;
		private int startRow;
		private int endRow;

		public ParallelMultiply(double[][] a, double[][] b, double[][] result, int startRow, int endRow) {
			this.a = a;
			this.b = b;
			this.result = result;
			this.startRow = startRow;
			this.endRow = endRow;
		}

		private void multiplyMatrix() {
			int iterationResult = 0;

			for (int rowA = startRow; rowA < endRow; rowA++) {
				for (int columnB = 0; columnB < b[0].length; columnB++) {
					for (int columnA = 0; columnA < a[0].length; columnA++) {
						iterationResult += a[rowA][columnA] * b[columnA][columnB];
					}
					result[rowA][columnB] = iterationResult;
					iterationResult = 0;
				}
			}
		}

		@Override
		public void run() {
			multiplyMatrix();
		}
	}
}
