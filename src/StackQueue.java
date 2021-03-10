/**
 * Realization of a queue as an adaptation of Java built-in stacks.
 * size(), isEmpty(), enqueue(T x) are performed in constant time.
 * first() and dequeue() are performed in O(n).
 *
 * @see Java built-in Stack class(https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/Stack.html)
 */

import java.util.Stack;

class StackQueue<T> implements Queue<T>{
	private Stack<T> inStack;
	private Stack<T> outStack;
	private int size;

	public StackQueue(){
		inStack = new Stack<T> ();
		outStack = new Stack<T> ();//PART A
		size = 0;
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size==0;
	}

	public T first(){
		if (size == 0) return null;;
		if (outStack.empty())
			while (!inStack.empty())
				outStack.push(inStack.pop());
		return outStack.peek(); //return the element at the top of the outStack without removing it from the stack PART B
	}

	public void enqueue(T x){
		inStack.push(x); //push x to inStack PART C
		size++;
	}

	public T dequeue(){
		if (size == 0) return null;//PART D
		if (outStack.empty())
			while (!inStack.empty())
				outStack.push(inStack.pop());
		size--;
		return outStack.pop(); //Removes and returns the element at the top of the outStack PART E
	}

	public String toString(){
		Stack<T> temp = new Stack<>();
		String ans = "StackQueue: ";
		if (isEmpty()) return ans;;
		if (outStack.empty())
			while (!inStack.empty())
				outStack.push(inStack.pop());

		while (!outStack.empty()){
                        T x = outStack.pop();
                        ans += (x + " -> ");
                        temp.push(x);
                }

		while (!inStack.empty())
			outStack.push(inStack.pop());
		while (!outStack.empty()){
                        T x = outStack.pop();
                        ans += (x + " -> ");
                        temp.push(x);
                }

		while (!temp.empty())
			outStack.push(temp.pop());

		return ans;
	}

	public static void main(String[] args){
		StackQueue<Integer> q = new StackQueue<>();
		q.enqueue(0);
		System.out.println("first element: " + q.first());
		q.enqueue(1);
		q.enqueue(2);
		q.dequeue();
		System.out.println(q.toString());
		System.out.println("first element: " + q.first());
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		System.out.println(q.toString());
		q.dequeue();
		System.out.println(q.toString());
		System.out.println("first element: " + q.first());
	}

}

/**
 * Interface for a queue: a collection of elements that are inserted
 * and removed according to the first-in first-out principle.
 */
interface Queue<T> {

  /**
   * Inserts an element at the rear of the queue.
   * @param e  the element to be inserted
   */
  void enqueue(T e);

  /**
   * Removes and returns the first element of the queue.
   * @return element removed (or null if empty)
   */
  T dequeue();

  /**
   * Returns the number of elements in the queue.
   * @return number of elements in the queue
   */
  int size();

  /**
   * Tests whether the queue is empty.
   * @return true if the queue is empty, false otherwise
   */
  boolean isEmpty();


  /**
   * Returns, but does not remove, the first element of the queue.
   * @return the first element of the queue (or null if empty)
   */
  T first();

}
