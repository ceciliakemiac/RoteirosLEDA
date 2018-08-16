package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
   	protected RecursiveSingleLinkedListImpl<T> next;
   
   	public RecursiveSingleLinkedListImpl() {
   
  	}
  
  	public RecursiveSingleLinkedListImpl(T data,
  			RecursiveSingleLinkedListImpl<T> next) {
  		this.data = data;
  		this.next = next;
  	}
  
  	@Override
  	public boolean isEmpty() {
  		return this.data == null;
  	}
  
  	@Override
  	public int size() {
  		if(this.isEmpty()) {
  			return 0;
  		}else {
  			return 1 + this.next.size();
  		}
  	}
  
  	@Override
  	public T search(T element) {
  		if(this.isEmpty()) {
  			return null;
  		}else {
  			if(element.equals(this.data)) {
  				return element;
  			}else {
  				return this.next.search(element);
  			}
  		}
  	}
  
  	@Override
  	public void insert(T element) {
  		if(element != null) {
  			if(this.isEmpty()) {
  				this.data = element;
  				this.next = new RecursiveSingleLinkedListImpl<T>();
  			}else {
  				this.next.insert(element);
  			}
  		}
  	}
  
  	@Override
  	public void remove(T element) {
  		if(element != null) {
  			if(!this.isEmpty()) {
  				if(element.equals(this.data)) {
  					this.data = this.next.data;
  					this.next = this.next.next;
  				}else {
  					this.next.remove(element);
  				}
  			}
  		}
  	}
  
  	@Override
  	public T[] toArray() {
  		T[] array = (T[]) new Object[this.size()];
  		toArray(array, this, 0);
  		return array;
  	}
  	
  	private void toArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int i) {
  		if(!node.isEmpty()) {
  			array[i] = node.data;
  			i++;
  			toArray(array, node.next, i);
  		}
  	}
  
  	public T getData() {
  		return data;
  	}
  
  	public void setData(T data) {
  		this.data = data;
  	}
  
  	public RecursiveSingleLinkedListImpl<T> getNext() {
  		return next;
  	}
  
  	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
  		this.next = next;
 	}

}
