package ca.mcgill.ecse420.a2.test;

import ca.mcgill.ecse420.a2.q1.BakeryLock;
import ca.mcgill.ecse420.a2.q1.CounterThread;
import ca.mcgill.ecse420.a2.q1.FilterLock;
import org.junit.Assert;
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
    private final int counterLimit = 10000;
    private final int TIMEOUT = 1000000;

    private void printTestName() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("\n================================ " + methodName + " ================================\n");
    }

    @Test
    public void testFilterLock_simpleTest() throws InterruptedException {

        printTestName();

        numberOfThreads = 3;

        // initiate filter lock and counter object
        FilterLock filterLock = new FilterLock(numberOfThreads);
        CounterThread counterThread = new CounterThread(counterLimit, filterLock);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        Long startTime = System.currentTimeMillis();
        for (int count = 0; count < numberOfThreads; count++) {
            executorService.execute(new Thread(counterThread));
        }

        executorService.shutdown();

        // wait for every thread to finish execution
        while (!executorService.isTerminated()) {
            if (System.currentTimeMillis() - startTime > TIMEOUT) {
                System.out.println("\nTimed out");
                Assert.fail();
            }
        }
        Long endTime = System.currentTimeMillis();

        System.out.println("\nTime " + (endTime - startTime) + " milliseconds");
    }

    @Test
    public void testBakeryLock_simpleTest() throws InterruptedException {

        printTestName();

        numberOfThreads = 3;

        // initiate bakery lock and counter object
        BakeryLock bakeryLock = new BakeryLock(numberOfThreads);
        CounterThread counterThread = new CounterThread(counterLimit, bakeryLock);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        Long startTime = System.currentTimeMillis();
        for (int count = 0; count < numberOfThreads; count++) {
            executorService.execute(new Thread(counterThread));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            if (System.currentTimeMillis() - startTime > TIMEOUT) {
                System.out.println("\nTimed out");
                Assert.fail();
            }
        }
        Long endTime = System.currentTimeMillis();

        System.out.println("\nTime " + (endTime - startTime) + " milliseconds");
    }

    @Test
    public void testFilterLock_threadCount5() {

        printTestName();

        numberOfThreads = 5;

        // initiate filter lock and counter object
        FilterLock filterLock = new FilterLock(numberOfThreads);
        CounterThread counterThread = new CounterThread(counterLimit, filterLock);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        Long startTime = System.currentTimeMillis();
        for (int count = 0; count < numberOfThreads; count++) {
            executorService.execute(new Thread(counterThread));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            if (System.currentTimeMillis() - startTime > TIMEOUT) {
                System.out.println("\nTimed out");
                Assert.fail();
            }
        }
        Long endTime = System.currentTimeMillis();

        System.out.println("\nTime " + (endTime - startTime) + " milliseconds");

    }

    @Test
    public void testBakeryLock_threadCount5() {

        printTestName();

        numberOfThreads = 5;

        // initiate bakery lock and counter object
        BakeryLock bakeryLock = new BakeryLock(numberOfThreads);
        CounterThread counterThread = new CounterThread(counterLimit, bakeryLock);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        Long startTime = System.currentTimeMillis();
        for (int count = 0; count < numberOfThreads; count++) {
            executorService.execute(new Thread(counterThread));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            if (System.currentTimeMillis() - startTime > TIMEOUT) {
                System.out.println("\nTimed out");
                Assert.fail();
            }
        }
        Long endTime = System.currentTimeMillis();

        System.out.println("\nTime " + (endTime - startTime) + " milliseconds");

    }

    @Test
    public void testFilterLock_threadCount10() {

        printTestName();

        numberOfThreads = 10;

        // initiate filter lock and counter object
        FilterLock filterLock = new FilterLock(numberOfThreads);
        CounterThread counterThread = new CounterThread(counterLimit, filterLock);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        Long startTime = System.currentTimeMillis();
        for (int count = 0; count < numberOfThreads; count++) {
            executorService.execute(new Thread(counterThread));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) ;
        Long endTime = System.currentTimeMillis();

        System.out.println("\nTime " + (endTime - startTime) + " milliseconds");

    }

    @Test
    public void testBakeryLock_threadCount10() {

        printTestName();

        numberOfThreads = 10;

        // initiate bakery lock and counter object
        BakeryLock bakeryLock = new BakeryLock(numberOfThreads);
        CounterThread counterThread = new CounterThread(counterLimit, bakeryLock);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        Long startTime = System.currentTimeMillis();
        for (int count = 0; count < numberOfThreads; count++) {
            executorService.execute(new Thread(counterThread));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            if (System.currentTimeMillis() - startTime > TIMEOUT) {
                System.out.println("\nTimed out");
                Assert.fail();
            }
        }
        Long endTime = System.currentTimeMillis();

        System.out.println("\nTime " + (endTime - startTime) + " milliseconds");

    }
}
