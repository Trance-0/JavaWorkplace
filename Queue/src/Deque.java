import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int size;

	private class Node {
		Item item;
		Node next;
		Node previous;
	}

	// construct an empty deque
	public Deque() {
		first = new Node();
		last = new Node();
		first.next = last;
		last.previous = first;
		size = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item != null) {
			Node newNode = new Node();
			Node second = first.next;
			first.next = newNode;
			newNode.previous = first;
			second.previous = newNode;
			newNode.next = second;
			newNode.item = item;
			size++;
		} else {
			throw new IllegalArgumentException("input with a null argument");
		}

	}

	// add the item to the back
	public void addLast(Item item) {
		if (item != null) {
			Node newNode = new Node();
			Node second = last.previous;
			last.previous = newNode;
			newNode.next = last;
			second.next = newNode;
			newNode.previous = second;
			newNode.item = item;
			size++;
		} else {
			throw new IllegalArgumentException("input with a null argument");
		}
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (!isEmpty()) {
			Item item = first.next.item;
			Node second = first.next.next;
			first.next = null;
			first.next = second;
			second.previous = first;
			size--;
			return item;
		} else {
			throw new java.util.NoSuchElementException();
		}
	}

	// remove and return the item from the back
	public Item removeLast() {
		if (!isEmpty()) {
			Item item = last.previous.item;
			Node second = last.previous.previous;
			last.previous = null;
			last.previous = second;
			second.next = last;
			size--;
			return item;
		} else {
			throw new java.util.NoSuchElementException();
		}
	}

	// return an iterator over items in order from front to back
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

//	public String toString() {
//		String result = "";
//		Node index = first.next;
//		while (index.item != null) {
//			result += index.item;
//			index = index.next;
//		}
//		return result;
//	}

	private class DequeIterator implements Iterator<Item> {
		private Node current = first.next;

		public boolean hasNext() {
			return current.item != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (size == 0) {
				throw new java.util.NoSuchElementException();
			} else {
				Item item = current.item;
				current = current.next;
				return item;
			}
		}
	}

	// unit testing (required)
	public static void main(String[] args) {
		Deque<String> d = new Deque<String>();
		System.out.println(d.isEmpty());
		System.out.println(d.size());
		d.addLast("Hello ");
		System.out.println(d.removeFirst());
		d.addFirst("world.");
		System.out.println(d);
		System.out.println(d.isEmpty());
		System.out.println(d.removeLast());
	}

}