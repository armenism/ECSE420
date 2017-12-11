
package ca.mcgill.ecse420.a3.q3;
import java.lang.reflect.Array;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;


public class BoundedLockBasedQueue<T> {

    private T[] itemArray; //Array based queue
    private int head; //Head index
    private int tail; //Tail index
    private AtomicInteger size; //Size queue

    //Head and tail locks used to allow parallelism 
    private Lock headLock;
    private Lock tailLock;

    //Lock conditions 
    private Condition notEmpty; 
    private Condition notFull ;

    public BoundedLockBasedQueue(int capacity) {
        //Initialize queue variables
        itemArray = (T[]) new Object[capacity]; 
        size = new AtomicInteger(0);
        head = 0; 
        tail = 0;

        //Initialize locks and respective conditions
        headLock = new ReentrantLock();
        tailLock = new ReentrantLock();
        notEmpty = headLock.newCondition();
        notFull = tailLock.newCondition();
    }

    public void Enqueue(T item) throws InterruptedException{

        boolean mustWakeDequeuers = false;
        
        tailLock.lock();
        try {
            //check and wait till there is space in queue
            while(size.get() == itemArray.length) {
                try {
                    notFull.await();
                } catch(InterruptedException e) {
                    System.out.println(e);
                }
            }

                /*Add item to queue. Wrap around needed to 
                add item to proper index once queue is almost full*/
                itemArray[(tail)%itemArray.length] = item;
                tail++;

                //Increment size and check if it used to be empty
                if(size.getAndIncrement() == 0){
                    mustWakeDequeuers = true;
                }

            } finally {
                tailLock.unlock();
            }
            if(mustWakeDequeuers){
                //Acquire head lock and attempt to signal all
                headLock.lock();
                try{
                  notEmpty.signal();

              }finally{ 
                headLock.unlock();
            }
        }
    }


    public T Dequeue() throws InterruptedException {
        boolean mustWakeEnueuers = false;
        headLock.lock();
        T result 

        try {
            //Check if queue is empty and wait until something is in it
            while(size.get() == 0) {
                try {
                    notEmpty.await();
                } catch(InterruptedException e) {
                    System.out.println(e);

                }
            }

            //Increment head index and return previous head value
            result = itemArray[head%itemArray.length;];
            head++;

            if (size.getAndDecrement() == capacity) {
                mustWakeEnueuers = true;      
            }

        }finally {
         headLock.unlock();
        }
        //If queue is no longer full singal all waiting enqueuers
        if(mustWakeEnueuers){
            tailLock.lock();
            try{ 

                 notFull.signal();

            }finally{
                tailLock.unlock();
            }
        }
            //return previous value of head
        return result; 
    }
}
