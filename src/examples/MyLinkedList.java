package examples;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements List<E> {
	// auxiliary class for our positions
	class LNode implements Position<E>{
		E elem;
		LNode prev,next;
		Object owner = MyLinkedList.this; //reference to the creator-object
		
		@Override
		public E element() {
			return elem;
		}
		
	}

	
	private LNode first,last;
	private int size; 
	
	private MyLinkedList<E>.LNode checkAndCast(Position<E> p) {
		LNode n;
		try {
			n = (LNode) p;
		} catch (ClassCastException e) {
			throw new RuntimeException("This is not a Position belonging to MyLinkedList"); 
		}
		if (n.owner == null) throw new RuntimeException("position was allready deleted!");
		if (n.owner != this) throw new RuntimeException("position belongs to another MyLinkedList instance!");			
		return n;
	}


	
	@Override
	public Position<E> first() {
		return first;
	}

	@Override
	public Position<E> last() {
		return last;
	}

	@Override
	public boolean isFirst(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLast(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Position<E> next(Position<E> p) {
		return checkAndCast(p).next;
	}


	@Override
	public Position<E> previous(Position<E> p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E replaceElement(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> insertFirst(E o) {
		LNode n = new LNode();
		n.elem = o;
		n.next = first;
		if (first == null){
			last = n;
		}
		else first.prev = n;
		first = n;
		size++;
		return n;
	}
	@Override
	public Position<E> insertLast(E o) {
		LNode n = new LNode();
		n.elem = o;
		n.prev = last;
		if (last == null){
			first = n;
		}
		else last.next = n;
		last = n;
		size++;
		return n;
	}

	@Override
	public Position<E> insertBefore(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> insertAfter(Position<E> p, E o) {
		LNode n = checkAndCast(p);

		//make new Node
		LNode newN = new LNode();
		newN.elem = o;
		
		// forward...
		newN.prev = n;
		newN.next = n.next;
		// bachward...
		
		n.next = newN;
		if (n==last){
			last = newN;
		}
		else newN.next.prev = newN;
		size++;
		return newN;
	}

	@Override
	public void remove(Position<E> p) {
		// p is removed (and invalidated) from this instance
		LNode n = checkAndCast(p);
		if (n != first)	n.prev.next = n.next;			
		else first = n.next;
		if (n != last) n.next.prev = n.prev;
		else last = n.prev;
		n.owner = null;
		size--;
	}

	@Override
	public Iterator<Position<E>> positions() {
		return new Iterator<Position<E>>() {
			// curent position:
			LNode curentNode = first;
			
			@Override
			public boolean hasNext() {
				return curentNode!=null;
			}

			@Override
			public Position<E> next() {
				LNode ret = curentNode;
				if (ret == null) throw new NoSuchElementException();
				curentNode = curentNode.next;
				return ret;
			}
		};

	}

	@Override
	public Iterator<E> elements() {
		return new Iterator<E>() {
			// curent position:
			LNode curentNode = first;
			
			@Override
			public boolean hasNext() {
				return curentNode!=null;
			}

			@Override
			public E next() {
				LNode ret = curentNode;
				if (ret == null) throw new NoSuchElementException();
				curentNode = curentNode.next;
				return ret.elem;
			}
		};
	}

	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	public static void main(String[] args) {
		MyLinkedList<String> li = new MyLinkedList<>(); 
		li.insertFirst("susi");
		li.insertFirst("beat");
		Position<String> p1 = li.insertFirst("urs");
		while (p1 != null) {
			System.out.println(p1.element());
			p1 = li.next(p1);
		}
		Iterator<Position<String>>  it = li.positions();
		while (it.hasNext()) li.remove(it.next());
		System.out.println(li.size());
 	}
}
