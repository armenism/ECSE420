package ca.mcgill.ecse420.a1.q1;

import java.util.Random;
import java.util.concurrent.*;

public class Question1 {

	double[][] a;	// matrix a
	double[][] b;	// matrix b
	private static int threads;	// number of threads to be used

	/**
	 * Constructor
	 * @param size				matrix size to be used
	 * @param numberOfThreads	number of threads to be used for parallel multiplication
	 */
	public Question1(int size, int numberOfThreads) {

		a = generateMatrix(size, size);	// generate a square matrix of size x size as a
		b = generateMatrix(size, size);	// generate a square matrix of size x size as b

		threads = numberOfThreads;
	}

	/**
	 * Generates a matrix of the specified given size population by random numbers between 0 and 10
	 * @param row		row size of the matrix
	 * @param column	column size of the matrix
	 * @return			returns the generated matrix
	 */
	public static double[][] generateMatrix(int row, int column) {

		double[][] generatedMatrix = new double[row][column];
		Random random = new Random();

		for (int r = 0; r < row; r++) {			// for each position of matrix
			for (int c = 0; c < column; c++) {	// generate a number between 0 and 10 and store it
				generatedMatrix[r][c] = random.nextInt(10);
			}
		}

		return generatedMatrix;
	}

	/**
	 * Multiplies the two given matrices sequentially without any parallelization
	 * @param a	first matrix
	 * @param b	second matrix
	 * @return	returns the resulting matrix
	 */
	public static double[][] sequentialMultiplyMatrix(double[][] a, double[][] b) {

		double[][] result = new double[a.length][b[0].length];	// make matrix of size row a x column b
		int iterationResult = 0;

		for (int rowA = 0; rowA < a.length; rowA++) {
			for (int columnB = 0; columnB < b[0].length; columnB++) {			// from the first row to the last
				for (int columnA = 0; columnA < a[0].length; columnA++) {		// row in matrix a multiply every row of
					iterationResult += a[rowA][columnA] * b[columnA][columnB];	// matrix b by the entire column of a
				}
				result[rowA][columnB] = iterationResult;	// store the final result of current row x column
				iterationResult = 0;						// reset result of row x column for next iteration
			}
		}

		return result;
	}

	/**
	 * Multiplies the two given matrices using the number of threads specified in the constructor
	 * @param a	first matrix
	 * @param b	second matrix
	 * @return	returns the resulting matrix
	 */
	public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) {

		if (threads > a.length) {
			System.out.println("\n\tCannot have more than " + a.length + " threads for this matrix");
			System.out.println("\tChanging thread numbers to " + a.length);
			System.out.println();
			threads = a.length;
		}

		double[][] result = new double[a.length][b[0].length];	// make matrix of size row a x column b

		ExecutorService executorService = Executors.newFixedThreadPool(threads);

		// for each thread to be used, divide the two matrices into equal parts
		for (int threadIndex = 0; threadIndex < threads; threadIndex++) {
			int startRow = (int) ((threadIndex * 1.0 / threads) * a.length);
			int endRow = (int) (((threadIndex * 1.0 + 1) / threads) * a.length);
			executorService.execute(new ParallelMultiply(a, b, result, startRow, endRow)); // initiate thread here
		}

		executorService.shutdown();	// shutdown all the executing threads

		// wait for every thread to shutdown then return the resulting matrix
		while (!executorService.isTerminated()) {
		}

		return result;
	}

	/**
	 * Runnable class that multiplies fraction of the two given matrices
	 */
	public static class ParallelMultiply implements Runnable {

		private double[][] a;	// matrix a
		private double[][] b;	// matrix b
		private double[][] result;	// resulting matrix
		private int startRow;	// starting multiplication row
		private int endRow;		// ending multiplication row

		/**
		 * Constructor
		 * @param a			matrix a
		 * @param b			matrix b
		 * @param result	resulting matrix
		 * @param startRow	stating multiplication row
		 * @param endRow	ending multiplication row
		 */
		public ParallelMultiply(double[][] a, double[][] b, double[][] result, int startRow, int endRow) {
			this.a = a;
			this.b = b;
			this.result = result;
			this.startRow = startRow;
			this.endRow = endRow;
		}

		/**
		 * Multiply matrix a by matrix b from the specified starting row up until the specified ending row, store the
		 * result in the proper place inside the resulting matrix
		 */
		private void multiplyMatrix() {
			int iterationResult = 0;

			for (int rowA = startRow; rowA < endRow; rowA++) {					// from given starting row to given ending
				for (int columnB = 0; columnB < b[0].length; columnB++) {		// row in matrix a multiply every row of
					for (int columnA = 0; columnA < a[0].length; columnA++) {	// matrix b by the entire column of a
						iterationResult += a[rowA][columnA] * b[columnA][columnB];	// add result of each multiplication
					}
					result[rowA][columnB] = iterationResult;	// store the final result of current row x column
					iterationResult = 0;						// reset result of row x column for next iteration
				}
			}
		}

		@Override
		public void run() {
			multiplyMatrix();
		}
	}
}
