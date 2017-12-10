package ca.mcgill.ecse420.a3.q2;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FineGrainList<T> {
	class Node<T> {
		private Lock lock = new ReentrantLock();
		private T item;
		private int key;
		private Node next;

		Node(T item) {
			key = item.hashCode();
			this.item = item;
		}

	}

	private Node<Integer> head;

	public FineGrainList() {
		head = new Node<Integer>(Integer.MIN_VALUE);
		head.next = new Node<Integer>(Integer.MAX_VALUE);
	}

	public boolean add(T item) {
		int key = item.hashCode();
		head.lock.lock();
		Node pred = head;
		try {
			Node curr = pred.next;
			curr.lock.lock();
			try {
				while (curr.key < key) {
					pred.lock.unlock();
					pred = curr;
					curr = curr.next;
					curr.lock.lock();
				}
				if (curr.key == key) {
					return false;
				}
				Node newNode = new Node(item);
				newNode.next = curr;
				pred.next = newNode;
				return true;
			} finally {
				curr.lock.unlock();
			}
		} finally {
			pred.lock.unlock();
		}
	}

	public boolean remove(T item) {
		Node pred = null, curr = null;
		int key = item.hashCode();
		head.lock.lock();
		try {
			pred = head;
			curr = pred.next;
			curr.lock.lock();
			try {
				while (curr.key < key) {
					pred.lock.unlock();
					pred = curr;
					curr = curr.next;
					curr.lock.lock();
				}
				if (curr.key == key) {
					pred.next = curr.next;
					return true;
				}
				return false;
			} finally {
				curr.lock.unlock();
			}
		} finally {
			pred.lock.unlock();
		}
	}

	public boolean contains(T item) {
		// Get key value
		int key = item.hashCode();
		// Lock and et head node to be predessor
		head.lock.lock();
		Node pred = head;

		try {
			// Set and lock next node as current node
			Node curr = pred.next;
			curr.lock.lock();
			try {
				/*
				 * In this section a thread only searches the content of a node
				 * (checks the key value) if it has the lock for the node in
				 * question AND it's predecessor
				 */
				while (curr.key < key) {
					pred.lock.unlock();
					pred = curr;
					curr = curr.next;
					curr.lock.lock();
				}
				// Retrun result of search
				return ((curr.key == key) ? true : false);

			} finally { // unlock current node
				curr.lock.unlock();
			}
		} finally { // unlock predecessor node
			pred.lock.unlock();
		}
	}

}