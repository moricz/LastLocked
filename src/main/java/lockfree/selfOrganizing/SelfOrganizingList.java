package lockfree.selfOrganizing;

import java.util.concurrent.atomic.AtomicStampedReference;

import common.interfaces.SelfOrganizingListInterface;
import lockfree.base.Node;

public class SelfOrganizingList implements SelfOrganizingListInterface<Node> {

	private final int NONE = 0;
	private final int ADD = 1;
	private final int SEARCH = 2;
	private final int STOPSEARCH = 12;
	private final int REMOVE = 3;
	private final int BEFOREREMOVE = 6;
	private final int AFTERREMOVE = 7;
	public AtomicStampedReference<Node> head;
	public AtomicStampedReference<Node> tail;

	public SelfOrganizingList() {

		tail = new AtomicStampedReference<Node>(new Node(Node.INIT_INT, null), 0);
		head = new AtomicStampedReference<Node>(new Node(Node.INIT_INT, tail.getReference()), 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lockfree.selfOrganizing.SelfOrganizingListInterface#add(int) get to
	 * tail and insert element
	 */
	public boolean add(int value) {
		back: while (true) {
			AtomicStampedReference<Node> current = head;
			AtomicStampedReference<Node> next = current.getReference().next;

			while (true) {
				// next is the tail
				Node oldCurrent = current.getReference();
				
				if (next.getReference() == tail.getReference()) {
					// element before tail is not stamped
					if (current.getStamp() != NONE && next.getStamp() != NONE)
						continue back;

					// stamp tail
					Node newCurrent = new Node(oldCurrent.value, new Node(value, next.getReference()));

					if (current.attemptStamp(oldCurrent, ADD)) {
						// place new node with value to tail
						if (current.compareAndSet(oldCurrent, newCurrent, ADD, NONE))
							return true;
					}

					continue back;
				}

				current = next;
				next = next.getReference().next;

			}
		}

	}
	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see lockfree.selfOrganizing.SelfOrganizingListInterface#remove(int)
	 * 
	 * we have in the list -> first -> second -> third-> -> first -> third ->
	 */

	public boolean remove(int value) {

		back: while (true) {
			AtomicStampedReference<Node> first = head;
			AtomicStampedReference<Node> second = first.getReference().next;
			AtomicStampedReference<Node> third = second.getReference().next;

			 while (true) {
				Node oldFirst = first.getReference();
				Node oldSecond = second.getReference();
				Node oldThird = third.getReference();
				// if the removable node is the second element
				if (second.getReference().getValue() == value) {

					// if the mask is not stamped by others
					if (first.getStamp() != NONE && second.getStamp() != NONE && third.getStamp() != NONE)
						continue back;

					// try stamp to begin removal
					if (first.attemptStamp(oldFirst, BEFOREREMOVE) && second.attemptStamp(oldSecond, REMOVE)
							&& third.attemptStamp(oldSecond.next(), AFTERREMOVE)) {

						/*
						 * create new node with value of first and next
						 * reference of second to replace the first node
						 */

						Node afterRemove = new Node(oldFirst.getValue(), oldThird);

						// replace the node
						if (first.compareAndSet(oldFirst, afterRemove, BEFOREREMOVE, NONE))
							return true;
					}
					continue back;
				}
				
				if (third.getReference() == null)
					return false;
				first = second;
				second = third;
				third = third.getReference().next;

			}
		}

	}

	public Node search(int value) {
		again: while (true) {

			AtomicStampedReference<Node> before = head;
			AtomicStampedReference<Node> swapA = head.getReference().next;
			AtomicStampedReference<Node> swapB = swapA.getReference().next;

			if (swapA.getReference().value == value)
				return swapA.getReference();

			
			while (true) {
				Node oldPredecessor = before.getReference();
				Node oldReplacable = swapA.getReference();
				Node oldSearchable = swapB.getReference();
				// verify if it is the node with the searched value
				if (swapB.getReference().value == value) {

					// check if the mask is stamped anywhere

					if (before.getStamp() != NONE && swapB.getStamp() != NONE && swapA.getStamp() != NONE)
						continue again;

					// stamp the mask
					/* before.attemptStamp(oldPredecessor, STOPSEARCH) && */

					if (before.attemptStamp(oldPredecessor, STOPSEARCH) && swapA.attemptStamp(oldReplacable, SEARCH)
							&& swapB.attemptStamp(oldSearchable, SEARCH)) {

						// create the swaped construction from pred-a-b ->
						// pred-b-a
						Node swappedNode = new Node(oldSearchable.value,
								new Node(oldReplacable.value, oldSearchable.next()));

						// try to set the node
						if (before.compareAndSet(oldPredecessor, new Node(oldPredecessor.getValue(), swappedNode),
								STOPSEARCH, NONE)) {
							return before.getReference();

						}
					}
					continue again;
				}

				// if end of the list and it was not found
				if (swapB.getReference().next == tail) {
					return null;
				}
				before = swapA;
				swapA = swapB;
				swapB = swapB.getReference().next;

			}

		}
	}

	public boolean contains(int value) {

		Node pred = head.getReference();
		while (pred!=null) {

			if (pred.value == value)
				return true;
			pred = pred.next();
		}
		return false;
	}

	public boolean list() {
		Node node = head.getReference();
		System.out.println("The List: ");
		System.out.print("[ ");
		int k = 1, row = 0;
		System.out.print(row + ": ");
		while (node != null) {
			if (k == 10) {

				System.out.println(node.value);
				row++;
				k = 1;
				System.out.print(row + "0: ");
			} else {
				k++;
				System.out.print(node.value + " ");
			}

			node = node.next.getReference();
		}
		System.out.println(" ]");
		return false;
	}
	
	public void listFromTo(int start, int end){
		Node node = head.getReference().next();
		System.out.println();
		int k=0, row=0;

		
		
		System.out.print(row + ": ");
		while(node.getValue() != end){
			if(k==9){
				System.out.println(node.value);
				row++;
				k = 0;
				System.out.print(row + "0: ");
			}
			else {
				k++;
				System.out.print(node.value+" ");
			}
			node = node.next();
			
		}
		System.out.println(" ");
	}

	public int failedRemove(int remove){
		Node node = head.getReference().next();
		int count = 0;
		while(node!=tail.getReference() ){
			if(node.value<=remove){
				count++;
			
			}
			node=node.next();
		}
		return count;
	}
	
	
	public int size() {
		int size = 1;
		Node node = head.getReference().next();
		while (node != tail.getReference()) {

			size++;

			node = node.next.getReference();

		}
		return size;
	}

}
