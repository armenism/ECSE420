package ca.mcgill.ecse420.a3.q4;

import java.util.concurrent.*;

/**
 * @author Armen Stepanians, ID: 260586139
 * @author Karim El-Baba, ID: 260582332
 *         <p>
 *         This class is an implementation of the parallel matrix multiplication.
 **/

public class ParallelMM {

    static ExecutorService exec = Executors.newCachedThreadPool();

    public static Vector add(Vector a, Vector b) throws ExecutionException, InterruptedException {
        int n = a.getDim();
        Vector c = new Vector(n);
        Future<?> future = exec.submit(new AddTask(a, b, c));
        future.get();
        return c;
    }

    public static Vector multiply(Matrix a, Vector b) throws ExecutionException, InterruptedException {
        int n = a.getDim();
        Vector c = new Vector(n);
        Future<?> future = exec.submit(new MulTask(a, b, c));
        future.get();
        return c;
    }

    static class AddTask implements Runnable {
        Vector a, b, c;

        public AddTask(Vector myA, Vector myB, Vector myC) {
            a = myA;
            b = myB;
            c = myC;
        }

        public void run() {
            try {
                int n = a.getDim();

                if (n == 1) {
                    c.set(a.get() + b.get());
                } else {

                    Vector[] aa = a.split(), bb = b.split(), cc = c.split();
                    Future<?>[] future = (Future<?>[]) new Future[2];

                    for (int i = 0; i < 2; i++) {
                        future[i] = exec.submit(new AddTask(aa[i], bb[i], cc[i]));
                    }
                    for (int i = 0; i < 2; i++) {
                        future[i].get();
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    static class MulTask implements Runnable {
        Matrix a;
        Vector b, c, lhs, rhs;

        public MulTask(Matrix myA, Vector myB, Vector myC) {
            a = myA;
            b = myB;
            c = myC;
            lhs = new Vector(b.getDim());
            rhs = new Vector(b.getDim());
        }

        public void run() {
            try {
                if (a.getDim() == 1) {
                    c.set(a.get() * b.get());
                } else {

                    Matrix[][] aa = a.split();
                    Vector[] bb = b.split();
                    Vector[] ll = lhs.split(), rr = rhs.split();
                    Future<?>[][] future = (Future<?>[][]) new Future[2][2];

                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            future[i][0] = exec.submit(new MulTask(aa[j][0], bb[0], ll[j]));
                            future[i][1] = exec.submit(new MulTask(aa[j][1], bb[1], rr[j]));
                        }
                    }

                    for (int i = 0; i < 2; i++)
                        for (int k = 0; k < 2; k++)
                            future[i][k].get();

                    Future<?> done = exec.submit(new AddTask(lhs, rhs, c));
                    done.get();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
