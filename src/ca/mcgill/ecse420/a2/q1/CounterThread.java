package ca.mcgill.ecse420.a2.q1;

import java.util.concurrent.locks.Lock;

/**
 * @author Armen Stepanians, ID: 260586139
 * @author Karim El-Baba, ID: 260582332
 *         <p>
 *         This class is an implementation of a counter object to demonstrate the implemented locks.
 **/

public class CounterThread implements Runnable {

    private int counter;
    private int counterLimit;
    private Lock lock;

    public CounterThread(int counterLimit, Lock lock) {

        // initialize
        this.counter = 1;
        this.counterLimit = counterLimit;
        this.lock = lock;
    }

    /**
     * Increments the counter using a locking mechanism for synchronicity
     *
     * @return counter
     */
    private int incrementCounter() {

        int temp;
        lock.lock();

        try {
            if (counter > counterLimit) {
                return counter;
            }
            System.out.println(this.toString());
            temp = counter;
            counter = temp + 1;
        } finally {
            lock.unlock();
        }
        return temp;

    }

    public String toString() {
        return "CounterThread " + getThreadId() + " ------------- " + counter;
    }

    @Override
    public void run() {

        System.out.println("CounterThread " + getThreadId() + " has entered the thread pool");

        // do this until counter is incremented all the way to reach counterLimit
        while (incrementCounter() < counterLimit) ;

        System.out.println("CounterThread " + getThreadId() + " has exited the thread pool");
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
