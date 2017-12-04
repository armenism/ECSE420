package ca.mcgill.ecse420.a3.q4;


/**
 * @author Armen Stepanians, ID: 260586139
 * @author Karim El-Baba, ID: 260582332
 *         <p>
 *         This class is an implementation of the sequential matrix multiplication.
 **/

public class SequentialMM {

    private Matrix A;
    private Matrix B;

    public SequentialMM(Matrix a, Matrix b) {

        // generate matrices of appropriate size
        A = a;
        B = b;
    }

    /**
     * Multiplies the two matrices sequentially without any parallelization
     *
     * @return returns the resulting matrix
     */
    public double[][] multiply() {

        double[][] result = new double[A.getDim()][B.getDim()];
        int iterationResult = 0;

        // sequential multiplication
        for (int rowA = 0; rowA < A.getDim(); rowA++) {
            for (int columnB = 0; columnB < B.getDim(); columnB++) {
                for (int columnA = 0; columnA < A.getDim(); columnA++) {
                    iterationResult += A.get(rowA, columnA) * B.get(columnA, columnB);
                }
                // store the final result of current row x column
                result[rowA][columnB] = iterationResult;
                iterationResult = 0;
            }
        }

        return result;
    }
}
