package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
   	protected int size;
  
  	public StackDoubleLinkedListImpl(int size) {
  		this.size = size;
  		this.top = new DoubleLinkedListImpl<T>();
  	}
  
  	@Override
  	public void push(T element) throws StackOverflowException {
  		if(!this.isFull()) {
  			this.top.insert(element);
  		}else {
  			throw new StackOverflowException();
  		}
  	}
  
  	@Override
  	public T pop() throws StackUnderflowException {
  		if(this.isEmpty()) {
  			throw new StackUnderflowException();
  		}else {
  			T removido = this.top();
  			this.top.removeLast();
  			return removido;
  		}
  	}
  
  	@Override
  	public T top() {
  		T top;
  		if(this.isEmpty()) {
  			top = null;
  		}else {
  			top = ((DoubleLinkedListImpl<T>) this.top).getLast().getData();
  		}
  		return top;
  	}
  
  	@Override
  	public boolean isEmpty() {
  		return this.top.isEmpty();
  	}
  
  	@Override
  	public boolean isFull() {
  		return this.size == this.top.size();
  	}


}
