package ca.mcgill.ecse420.a2.q1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Armen Stepanians, ID: 260586139
 * @author Karim El-Baba, ID: 260582332
 *         <p>
 *         This class is an implementation of the Filter lock discussed in chapter 2.
 **/

public class FilterLock implements Lock {

    private int n;
    private AtomicInteger[] level;    // level[i] for thread i
    private AtomicInteger[] victim;    // victim[L] for level L

    public FilterLock(int n) {

        // initialize
        this.n = n;
        level = new AtomicInteger[n];
        victim = new AtomicInteger[n];

        for (int i = 0; i < n; i++) {
            level[i] = new AtomicInteger();
            victim[i] = new AtomicInteger();
        }
    }

    @Override
    public void lock() {

        int threadId = this.getThreadId();
        for (int L = 1; L < n; L++) {

            // enter level
            level[threadId].set(L);

            // set victim of level as this thread
            victim[L].set(threadId);

            for (int thread = 0; thread < n; thread++) {
                //spin wait
                while ((thread != threadId) && (level[thread].get() >= L && victim[L].get() == threadId)) ;
            }
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
        level[threadId].set(0);
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
}
