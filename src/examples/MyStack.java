package examples;

import java.util.ArrayList;
import java.util.Arrays;

public class MyStack<E> implements Stack<E> {

	private E[] stor = (E[]) new Object[256];// = new E[];
	private int stackPtr; // points to the next free position in stor
	
	private void expand(){
		// System.out.println("..expanding");
		stor = Arrays.copyOf(stor,stor.length*2);
	}
	
	@Override
	public void push(E o) {
		if (stackPtr==stor.length) expand();
		stor[stackPtr++]=o;
	}

	@Override
	public E pop() {
		return stor[--stackPtr];
	}

	@Override
	public E top() {
		return stor[stackPtr-1];
	}

	@Override
	public int size() {
		return stackPtr;
	}

	@Override
	public boolean isEmpty() {
		return stackPtr==0;
	}

	public static void main(String[] args) {
		Stack<String> s = new MyStack<>();
		int N = 1000000;
		long t1 = System.nanoTime();
		for (int i=0;i<N;i++) s.push(""+i);
		long t2 = System.nanoTime();
		System.out.println("time: "+(t2-t1)*1e-6+" ms");
	}

}