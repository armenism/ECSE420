package ca.mcgill.ecse420.a2.test;

import ca.mcgill.ecse420.a2.q1.BakeryLock;
import ca.mcgill.ecse420.a2.q1.CounterThread;
import ca.mcgill.ecse420.a2.q1.FilterLock;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Armen Stepanians, ID: 260586139
 * @author Karim El-Baba, ID: 260582332
 *         <p>
 *         This class is to test the lock implementations provided in part 1 and part 3.
 **/

public class TestLocks {

    private int numberOfThreads;
    private int counterLimit = 10000;

    private void printMethodName() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("\n================================ " + methodName + " ================================\n");
    }

    @Test
    public void testFilterLock_simpleTest() throws InterruptedException {

        printMethodName();

        numberOfThreads = 2;

        FilterLock filterLock = new FilterLock(2);
        CounterThread counterThread = new CounterThread(counterLimit, filterLock);

        Thread thread = new Thread(counterThread);
        Thread thread2 = new Thread(counterThread);

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();
    }

    @Test
    public void testBakeryLock_simpleTest() throws InterruptedException {

        printMethodName();

        numberOfThreads = 2;

        BakeryLock bakeryLock = new BakeryLock(numberOfThreads);
        CounterThread counterThread = new CounterThread(counterLimit, bakeryLock);

        Thread thread = new Thread(counterThread);
        Thread thread2 = new Thread(counterThread);

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();
    }

    @Test
    public void testFilterLock_threadCount5() {

        printMethodName();

        numberOfThreads = 5;

        FilterLock filterLock = new FilterLock(numberOfThreads);
        CounterThread counterThread = new CounterThread(counterLimit, filterLock);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int count = 0; count < numberOfThreads; count++) {
            executorService.execute(new Thread(counterThread));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) ;

    }

    @Test
    public void testBakeryLock_threadCount5() {

        printMethodName();

        numberOfThreads = 5;

        BakeryLock bakeryLock = new BakeryLock(numberOfThreads);
        CounterThread counterThread = new CounterThread(counterLimit, bakeryLock);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int count = 0; count < numberOfThreads; count++) {
            executorService.execute(new Thread(counterThread));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) ;

    }
}
