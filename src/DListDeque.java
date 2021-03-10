/**
 * 
 * Realization of a deque as an adaptation of a doubly linked list.
 *
 * @see DList
 */

class DListDeque<T> implements Deque<T>{
	private DList<T> dlist;

	public DListDeque(){
		dlist = new DList <T> ();//PART F
	}

	public int size(){
		return dlist.size();//PART G
	}

	public boolean isEmpty(){
		return dlist.isEmpty();
	}

	public T first(){
		return dlist.getFirst().getData();
	}

	public T last(){
		return dlist.getLast().getData();//PART H
	}

	public void addFirst(T x){
		dlist.addFirst(x);
	}

	public void addLast(T x){
		dlist.addLast(x);//PART I
	}

	public T removeFirst(){
		return dlist.remove(dlist.getFirst());//PART J
	}

	public T removeLast(){
		return dlist.remove(dlist.getLast());
	}

	public String toString(){
		return dlist.toString();
	}

	public static void main(String[] args){
		DListDeque<Integer> d = new DListDeque<>();
		d.addLast(5);
		d.addFirst(3);
		d.addFirst(7);
		System.out.println("first element of deque: " + d.first());
		System.out.println(d.toString()); // 7 <-> 3 <-> 5
		System.out.println("last element of deque: " + d.last());
		d.removeLast();
		System.out.println(d.toString()); // 7 <-> 3
	}
}

/**
 * Interface for a double-ended queue: a collection of elements that can be inserted
 * and removed at both ends; this interface is a simplified version of java.util.Deque.
 *
 */
interface Deque<T> {

  /**
   * Returns the number of elements in the deque.
   * @return number of elements in the deque
   */
  int size();

  /**
   * Tests whether the deque is empty.
   * @return true if the deque is empty, false otherwise
   */
  boolean isEmpty();

  /**
   * Returns (but does not remove) the first element of the deque.
   * @return first element of the deque (or null if empty)
   */
  T first();

  /**
   * Returns (but does not remove) the last element of the deque.
   * @return last element of the deque (or null if empty)
   */
  T last();

  /**
   * Inserts an element at the front of the deque.
   * @param e   the new element
   */
  void addFirst(T e);

  /**
   * Inserts an element at the back of the deque.
   * @param e   the new element
   */
  void addLast(T e);

  /**
   * Removes and returns the first element of the deque.
   * @return element removed (or null if empty)
   */
  T removeFirst();

  /**
   * Removes and returns the last element of the deque.
   * @return element removed (or null if empty)
   */
  T removeLast();
}

class DList<T> {
    private DNode<T> header, trailer;
    private int size;
    
    public DList() {
        size = 0;
        header = new DNode<T>(null, null, null);
        trailer = new DNode<T>(null, header, null);
        header.setNext(trailer);
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public DNode<T> getFirst(){
        if(isEmpty()) return null;
        return header.getNext();
    }
    
    public DNode<T> getLast(){
        if(isEmpty()) return null;
        return trailer.getPrev();
    }
    
    public DNode<T> getNext(DNode<T> v){
        DNode<T> next = v.getNext();
        if(next == null || next == trailer) return null;
        return next;
    }

    public DNode<T> getPrev(DNode<T> v){
        DNode<T> prev = v.getPrev();
        if(prev == null || prev == header) return null;
        return prev;
    }
    
    //size changing methods.

    //insert after DNode v    
    public void insertAfter(T d, DNode<T> v) {
        DNode<T> w = v.getNext();
        DNode<T> x = new DNode<T>(d, v, w);
        v.setNext(x);
        w.setPrev(x);
        size++;
    }
    
    // insert before DNode v
    public void insertBefore(T d, DNode<T> v) {
        DNode<T> u = v.getPrev();
        DNode<T> x = new DNode<T>(d, u, v);
        u.setNext(x);
        v.setPrev(x);
        size++;
    }
    
    public void addFirst(T d){
        insertAfter(d, header);
    }
    
    public void addLast(T d){
        insertBefore(d, trailer);
    }
    
    public T remove(DNode<T> v){
        if(v == header || v == trailer) return null;
        DNode<T> u = v.getPrev();
        DNode<T> w = v.getNext();
        u.setNext(w);
        w.setPrev(u);
        size--;
        return v.getData();
    }
    
    //List testing methods
    public String toString() {
        String ans = "";
        DNode<T> h = header;
        ans += "(H) <-> ";
        do {
            h = h.getNext();
            if(h == trailer)
                ans += "(T)";
            else {
                ans += h.getData();
                ans += " <-> ";
            }
        } while( h!= trailer);
        return ans;
    }
}

class DNode<T> {
    private DNode<T> prev, next;
    private T data;
    
    public DNode() {
        prev = next = null;
        data = null;
    }
    
    public DNode(T d, DNode<T> p, DNode<T> n) {
        data = d;
        prev  = p;
        next = n;
    }
    
    public T getData() {
        return data;
    }

    public DNode<T> getNext(){
        return next;
    }

    public DNode<T> getPrev(){
        return prev;
    }

    public void setData(T d) {
        data = d;
    }
    
    public void setNext(DNode<T> n) {
        next = n;
    }
    
    
    public void setPrev(DNode<T> p) {
        prev = p;
    }
}