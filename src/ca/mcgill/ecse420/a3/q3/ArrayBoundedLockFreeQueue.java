
package ca.mcgill.ecse420.a3.q3;

import java.lang.reflect.Array;

public class BoundedLockBasedQueue<T> {

    private T[] itemArray; //Array based queue
    private AtomicInteger head; //Head index
    private AtomicInteger tail; //Tail index
    private AtomicInteger size; //Size queue


    public BoundedLockBasedQueue(int capacity) {
        //Initialize queue variables
        itemArray = (T[]) new Object[capacity]; 
        size = new AtomicInteger(0);
        head = new AtomicInteger(0); 
        tail = new AtomicInteger(0);
    }

    public void Enqueue(T item) throws InterruptedException{

        //check and wait till there is space in queue
        while(size.get() == itemArray.length);

        /*Add item to queue. Wrap around needed to 
        add item to proper index once queue is almost full*/
        itemArray[tail.getAndIncrement()%itemArray.length] = item;
        size.getAndIncrement();

    }

    public T Dequeue() throws InterruptedException {

        T result 
        while(size.get() == 0);

        //Increment head index and return previous head value
        result = itemArray[head.getAndIncrement()%itemArray.length];
        size.getAndDecrement()
        
        //return previous value of head
        return result; 
    }
}
