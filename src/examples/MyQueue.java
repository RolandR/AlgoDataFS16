package examples;

/**
 * @author ps
 * Cylic array buffer implementation of a Queue
 * @param <E> the type of the objects to be stored in this Queue
 */
public class MyQueue<E> implements Queue<E> { 
	
	private E[] stor = (E[]) new Object[100]; 
	private int inPtr, outPtr, size; 
	
	@Override
	public void enqueue(E o) {
		if (size==stor.length) expand();
		if (inPtr==stor.length) inPtr=0;
		stor[inPtr++]=o;
		size++;
	}

	private void expand() {
		E[] tmp = (E[]) new Object[stor.length*2];
		for (int i=0;i<size;i++){
			if (outPtr==stor.length) outPtr=0;
			tmp[i]=stor[outPtr++];
		}
		inPtr = size;
		outPtr= 0;
		stor = tmp;
	}

	@Override
	public E dequeue() {
		if (size==0) throw new RuntimeException("Empty queue!");
		if (outPtr==stor.length) outPtr=0;
		size--;
		return stor[outPtr++];
	}

	@Override
	public E head() {
		return stor[outPtr];
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
		Queue<String> q = new MyQueue<>();
		q.enqueue("hans");
		q.enqueue("susi");
		q.enqueue("urs");
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
	}

}
