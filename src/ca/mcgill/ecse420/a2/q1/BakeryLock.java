package ca.mcgill.ecse420.a2.q1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Armen Stepanians, ID: 260586139
 * @author Karim El-Baba, ID: 260582332
 *         <p>
 *         This class is an implementation of the Bakery lock discussed in chapter 2.
 **/

public class BakeryLock implements Lock {

    private int n;
    private AtomicBoolean[] flag;
    private AtomicInteger[] label;

    public BakeryLock(int n) {

        // initialize
        this.n = n;
        flag = new AtomicBoolean[n];
        label = new AtomicInteger[n];

        for (int i = 0; i < n; i++) {
            flag[i] = new AtomicBoolean();
            label[i] = new AtomicInteger();
        }
    }

    @Override
    public void lock() {

        int threadId = this.getThreadId();

        // express intention and get assigned a number
        flag[threadId].set(true);
        label[threadId].set(maxLabel(label) + 1);
        flag[threadId].set(false);

        // go through every thread
        for (int p = 0; p < n; p++) {

            // choosing
            while (flag[p].get()) ;

            // wait until thread's label becomes lowest in the pool
            while (label[p].get() != 0 && ((label[p].get() < label[threadId].get()) || (label[p].get() == label[threadId].get() && p < threadId)))
                ;
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        int threadId = this.getThreadId();
        label[threadId].set(0);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * Gets the relative thread ID
     *
     * @return thread ID
     */
    private int getThreadId() {
        String threadId = Thread.currentThread().getName();
        int id = Integer.parseInt(threadId.substring(threadId.lastIndexOf('-') + 1));

        return threadId.contains("pool") ? id - 1 : id;
    }

    /**
     * Finds maximum label number within the given array
     *
     * @param labels array of labels
     * @return maximum number in the array
     */
    private int maxLabel(AtomicInteger[] labels) {
        int max = 0;

        for (AtomicInteger label : labels) {
            if (label.get() > max) {
                max = label.get();
            }
        }
        return max;
    }
}
