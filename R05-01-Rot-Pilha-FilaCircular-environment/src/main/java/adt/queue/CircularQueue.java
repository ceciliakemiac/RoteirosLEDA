package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
  		array = (T[]) new Object[size];
  		head = -1;
  		tail = -1;
  		elements = 0;
  	}
  
  	@Override
  	public void enqueue(T element) throws QueueOverflowException {
  		if(this.isFull()) {
  			throw new QueueOverflowException();
  		}else {
  			this.tail = (this.tail + 1) % this.array.length;
  			this.array[this.tail] = element;
  			this.elements++;
  		}
  		
  		if(this.head == -1) {
  			this.head++;
  		}
  	}
  
  	@Override
  	public T dequeue() throws QueueUnderflowException {
  		if(this.isEmpty()) {
  			throw new QueueUnderflowException();
  		}else {
  			T retorno = this.array[this.head];
  			if(this.elements == 1) {
  				this.head = -1;
  				this.tail = -1;
  			}else {
  				this.head = (this.head + 1) % this.array.length;
  			}
  			this.elements--;
  			return retorno;
  		}
  	}
  
  	@Override
  	public T head() {
  		T retorno;
  		if(this.isEmpty()) {
  			retorno = null;
  		}else {
  			retorno = this.array[this.head];
  		}
  		return retorno;
  	}
  
  	@Override
  	public boolean isEmpty() {
  		return this.elements == 0;
  	}
  
  	@Override
  	public boolean isFull() {
  		return this.elements == this.array.length;
  	}

}
