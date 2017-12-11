package ca.mcgill.ecse420.a3.q4;

import java.util.Random;

/**
 * @author Armen Stepanians, ID: 260586139
 * @author Karim El-Baba, ID: 260582332
 *         <p>
 *         This class is an implementation of a matrix
 **/

public class Matrix {

    private int dim;
    private double[][] data;
    private int rowDisplace, colDisplace;

    public Matrix(int d) {
        dim = d;
        rowDisplace = colDisplace = 0;
        data = new double[d][d];
    }

    public Matrix(double[][] matrix, int x, int y, int d) {
        data = matrix;
        rowDisplace = x;
        colDisplace = y;
        dim = d;
    }

    public double[][] getData() {
        return data;
    }

    double get() {
        return data[rowDisplace][colDisplace];
    }

    double get(int row, int col) {
        return data[row][col];
    }

    void set(double value) {
        data[rowDisplace][colDisplace] = value;
    }

    int getDim() {
        return dim;
    }

    Matrix[][] split() {
        Matrix[][] result = new Matrix[2][2];
        int newDim = dim / 2;

        result[0][0] = new Matrix(data, rowDisplace, colDisplace, newDim);
        result[0][1] = new Matrix(data, rowDisplace, colDisplace + newDim, newDim);
        result[1][0] = new Matrix(data, rowDisplace + newDim, colDisplace, newDim);
        result[1][1] = new Matrix(data, rowDisplace + newDim, colDisplace + newDim, newDim);

        return result;

    }

    /**
     * Generates a matrix of random numbers between 0 and 10
     */
    public void generateMatrix() {

        Random random = new Random();

        for (int r = 0; r < dim; r++) {
            for (int c = 0; c < dim; c++) {
                data[r][c] = random.nextInt(10);
            }
        }
    }

}

