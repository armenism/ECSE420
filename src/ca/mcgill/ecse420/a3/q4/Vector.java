package ca.mcgill.ecse420.a3.q4;

import java.util.Random;

/**
 * @author Armen Stepanians, ID: 260586139
 * @author Karim El-Baba, ID: 260582332
 *         <p>
 *         This class is an implementation of a matrix
 **/

public class Vector {

    private int dim;
    private double[] vector;
    private int rowDisplace;

    public Vector(int d) {
        dim = d;
        rowDisplace = 0;
        vector = new double[d];
    }

    public Vector(double[] v, int x, int d) {
        vector = v;
        rowDisplace = x;
        dim = d;
    }

    public double[] getVector() {
        return vector;
    }

    int getDim() {
        return dim;
    }

    double get() {
        return vector[rowDisplace];
    }

    void set(double value) {
        vector[rowDisplace] += value;
    }

    Vector[] split() {
        Vector[] result = new Vector[2];
        int newDim = dim / 2;

        result[0] = new Vector(vector, rowDisplace, newDim);
        result[1] = new Vector(vector, rowDisplace + newDim, newDim);

        return result;

    }


    /**
     * Generates a matrix of random numbers between 0 and 10
     */
    public void generateVector() {

        Random random = new Random();

        for (int r = 0; r < dim; r++) {
            for (int c = 0; c < dim; c++) {
                vector[c] = random.nextInt(3) + 1;
            }
        }
    }
}
