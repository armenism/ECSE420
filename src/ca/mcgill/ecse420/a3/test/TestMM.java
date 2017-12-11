package ca.mcgill.ecse420.a3.test;

import ca.mcgill.ecse420.a3.q4.Matrix;
import ca.mcgill.ecse420.a3.q4.ParallelMM;
import ca.mcgill.ecse420.a3.q4.SequentialMM;
import ca.mcgill.ecse420.a3.q4.Vector;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * @author Armen Stepanians, ID: 260586139
 * @author Karim El-Baba, ID: 260582332
 *         <p>
 *         This class is for unit-testing the implemented MM methods.
 **/

public class TestMM {

    private static Matrix A;
    private static Vector B;
    private static Vector C;

    private void printTestName() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("\n================================ " + methodName + " ================================\n");
    }

    @BeforeClass
    public static void generateMatrices() {
        A = new Matrix(4);
        B = new Vector(4);
        C = new Vector(4);
    }

    @Test
    public void testParallelMultiply() {

        printTestName();

        A.generateMatrix();
        B.generateVector();

        printMatrix(A);
        printVector(B);

        try {
            long startTime = System.nanoTime();
            Vector c = ParallelMM.multiply(A, B);
            long endTime = System.nanoTime();
            printVector(c);
            System.out.println("Computation time " + (endTime - startTime) * 1.0 / 1000000000 + " seconds");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSequentialMultiply() {

        printTestName();

        A.generateMatrix();
        B.generateVector();

        printMatrix(A);
        printVector(B);

        long startTime = System.nanoTime();
        Vector c = SequentialMM.multiply(A, B);
        long endTime = System.nanoTime();
        printVector(c);
        System.out.println("Computation time " + (endTime - startTime) * 1.0 / 1000000000 + " seconds");
    }

    @Test
    public void testAdd() {

        B.generateVector();
        C.generateVector();

        printVector(B);
        printVector(C);

        try {
            Vector c = ParallelMM.add(B, C);
            printVector(c);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printMatrix(Matrix matrix) {
        for (double[] doubles : matrix.getData()) {
            for (double num : doubles) {
                System.out.print((int) num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printVector(Vector vector) {
        for (int i = 0; i < vector.getVector().length; i++) {
            System.out.println((int) vector.getVector()[i]);
        }
        System.out.println();
    }
}
