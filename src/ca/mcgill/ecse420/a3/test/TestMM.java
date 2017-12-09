package ca.mcgill.ecse420.a3.test;

import ca.mcgill.ecse420.a3.q4.Matrix;
import ca.mcgill.ecse420.a3.q4.ParallelMM;
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

    @BeforeClass
    public static void generateMatrices() {
        A = new Matrix(3);
        B = new Vector(3);
        C = new Vector(3);
    }

    @Test
    public void testMultiply() {

        A.generateMatrix();
        B.generateVector();

        printMatrix(A);
        printVector(B);

        try {
            Vector c = ParallelMM.multiply(A, B);
            printVector(c);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {

        A.generateMatrix();
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
        for (double[] doubles: matrix.getData()) {
            for (double num: doubles) {
                System.out.print((int)num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printVector(Vector vector) {
        for (double num: vector.getVector()) {
                System.out.println((int)num);
            }
        System.out.println();
    }
}
