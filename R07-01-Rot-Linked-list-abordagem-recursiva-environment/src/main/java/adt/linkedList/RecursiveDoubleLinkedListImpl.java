package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;
	   
   	public RecursiveDoubleLinkedListImpl() {
   
  	}
  
  	public RecursiveDoubleLinkedListImpl(T data,
  			RecursiveSingleLinkedListImpl<T> next,
  			RecursiveDoubleLinkedListImpl<T> previous) {
  		super(data, next);
  		this.previous = previous;
  	}
  	
  	public void insert(T element) {
  		if(element != null) {
  			if(this.isEmpty()) {
  				this.data = element;
  				RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<T>();
  				this.next = node;
  				node.previous = new RecursiveDoubleLinkedListImpl<T>();
  				if(this.previous == null) {
  					RecursiveDoubleLinkedListImpl<T> previa = new RecursiveDoubleLinkedListImpl<T>();
  					this.previous = previa;
  					previa.next = this;
  				}
  			}else {
  				this.next.insert(element);
  			}
  		}
  	}
  
  	@Override
  	public void insertFirst(T element) {
  		if(element != null) {
  			if(this.isEmpty()) {
  				insert(element);
  			}else {
  				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<T>();
  				newNode.data = this.data;
  				newNode.next = this.next;
  				if(this.next instanceof RecursiveDoubleLinkedListImpl<?>) {
  					((RecursiveDoubleLinkedListImpl<T>) this.next).previous = newNode;
  				}
  				this.next = newNode;
  				this.data = element;
  			}
  		}
  	}
  
  	@Override
  	public void removeFirst() {
  		if(!this.isEmpty()) {
  			if(this.previous.isEmpty() && this.next.isEmpty()) {
  				this.data = null;
  			}else {
  				this.data = this.next.data;
  				this.next = this.next.next;
  				if(this.next instanceof RecursiveDoubleLinkedListImpl<?>) {
  					((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this;
  				}
  			}
  		}
  	}
  
  	@Override
  	public void removeLast() {
  		if(!this.isEmpty()) {
  			if(this.next.isEmpty()) {
  				this.data = null;
  				this.next = null;
  			}else {
  				if(this.next instanceof RecursiveDoubleLinkedListImpl<?>) {
  					((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
  				}
  			}
  		}
  	}
  
  	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
  		return previous;
  	}
  
  	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
  		this.previous = previous;
  	}

}
