
import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] parkinglots;
	private int head;
	private int tail;
	private int size;

	public RandomizedQueue() {
		parkinglots = (Item[]) new Object[4];
		head = 0;
		tail = 0;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	private void refreshQueue() {
		Item[] subarray = (Item[]) new Object[size];
		for (int i = 0; i < size; i++) {
			subarray[i] = parkinglots[head + i];
		}
		StdRandom.shuffle(subarray);
		for (int i = 0; i < size; i++) {
			parkinglots[head + i] = subarray[i];
		}
	}

	private void doubleSizeOfTail() {
		int capacity = parkinglots.length;
		Item[] copy = (Item[]) new Object[capacity * 2];
		for (int i = head; i <= tail; i++) {
			copy[i] = parkinglots[i];
		}
		parkinglots = copy;
	}

	private void halfSizeOfHead() {
		int capacity = parkinglots.length / 2;
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = head; i <= tail; i++) {
			copy[i - capacity] = parkinglots[i];
		}
		head -= capacity;
		tail -= capacity;
		parkinglots = copy;
	}

	public void enqueue(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		} else {
			parkinglots[tail] = item;

			tail++;
			size++;
			if (parkinglots.length - 3 < tail) {
				doubleSizeOfTail();
			}
		}
	}

	public Item dequeue() {
		if (size == 0) {
			throw new java.util.NoSuchElementException();
		} else {
			refreshQueue();
			Item item = parkinglots[head];
			parkinglots[head] = null;
			size--;
			head++;
			if (head > 50) {
				halfSizeOfHead();
			}
			return item;
		}
	}

	public Item sample() {
		if (size == 0) {
			throw new java.util.NoSuchElementException();
		} else {
			refreshQueue();
			Item item = parkinglots[head];
			return item;
		}
	}

	public Iterator<Item> iterator() {
		refreshQueue();
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private int index = head;

		public boolean hasNext() {
			return parkinglots[index] != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			} else {
				Item item = parkinglots[index];
				index++;
				return item;
			}
		}
	}

//    public String toString() {
//        String result="";
//        for(Item i:parkinglots) {
//            result+=i;
//        }
//        return result;
//    }
	// unit testing (required)
	public static void main(String[] args) {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		d.enqueue(" ");
		System.out.println(d.size());
		System.out.println(d.sample());
		System.out.println(d.dequeue());
		System.out.println(d.isEmpty());
	}

}