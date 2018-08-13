package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
   		this.head = new DoubleLinkedListNode<T>();
  		this.last = (DoubleLinkedListNode<T>) this.head;
  	}
  
  	@Override
  	public void insertFirst(T element) {
  		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element,
  				new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
  		newHead.next = this.head;
  		((DoubleLinkedListNode<T>) this.head).previous = newHead;
  		if(this.head.isNIL()) {
  			this.last = newHead;
  		}
  		this.head = newHead;
  	}
  	
  	public void insert(T element) {
  		DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element, 
  				new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
  		newLast.previous = this.last;
  		this.last.next = newLast;
  		if(this.last.isNIL()) {
  			this.head = newLast;
  		}
  		this.last = newLast;
  	}
  
  	@Override
  	public void removeFirst() {
  		if(!this.head.isNIL()) {
  			this.head = this.head.next;
  			if(this.head.isNIL()) {
  				this.last = (DoubleLinkedListNode<T>) this.head;
  			}
  			((DoubleLinkedListNode<T>) this.head).previous = new DoubleLinkedListNode<T>();
  		}
  	}
  
  	@Override
  	public void removeLast() {
  		if(!this.last.isNIL()) {
  			this.last = this.last.previous;
  			if(this.last.isNIL()) {
  				this.head = this.last;
  			}
  			this.last.next = new DoubleLinkedListNode<T>();
  		}
  	}
  	
  	public void remove(T element) {
  		if(this.head.data.equals(element)) {
  			removeFirst();
  		}else {
  			DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
  			while(!aux.isNIL() && !aux.data.equals(element)) {
  				aux = (DoubleLinkedListNode<T>) aux.next;
  			}
  			if(!aux.isNIL()) {
  				aux.previous.next = aux.next;
  				((DoubleLinkedListNode<T>) aux.next).previous = aux.previous;
  			}
  		}
  	}
  
  	public DoubleLinkedListNode<T> getLast() {
  		return last;
  	}
  
  	public void setLast(DoubleLinkedListNode<T> last) {
  		this.last = last;
  	}

}
