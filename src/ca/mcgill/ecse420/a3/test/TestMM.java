package ca.mcgill.ecse420.a3.test;

import ca.mcgill.ecse420.a3.q4.Matrix;
import ca.mcgill.ecse420.a3.q4.ParallelMM;
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
    private static Matrix B;

    @BeforeClass
    public static void generateMatrices() {
        A = new Matrix(4);
        B = new Matrix(4);
    }

    @Test
    public void testMultiply() {

        A.generateMatrix();
        B.generateMatrix();

        printMatrix(A);
        printMatrix(B);

        try {
            Matrix c = ParallelMM.multiply(A, B);
            printMatrix(c);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {

        A.generateMatrix();
        B.generateMatrix();

        printMatrix(A);
        printMatrix(B);

        try {
            Matrix c = ParallelMM.add(A, B);
            printMatrix(c);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printMatrix(Matrix matrix) {
        for (double[] doubles: matrix.getData()) {
            for (double num: doubles) {
                System.out.print((int)num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
