package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
  	public T head() {
  		T retorno;
  		if(this.isEmpty()) {
  			retorno = null;
  		}else {
  			retorno = this.array[0];
  		}
  		return retorno;
  	}
  
  	@Override
  	public boolean isEmpty() {
  		return this.tail == -1;
  	}
  
  	@Override
  	public boolean isFull() {
  		return this.tail == this.array.length - 1;
  	}
  
  	private void shiftLeft() {
  		for(int i = 0; i < this.tail; i++) {
  			this.array[i] = this.array[i + 1];
  		}
  	}
  
  	@Override
  	public void enqueue(T element) throws QueueOverflowException {
  		if(this.isFull()) {
  			throw new QueueOverflowException();
  		}else {
  			this.array[++this.tail] = element;
  		}
  	}
  
  	@Override
  	public T dequeue() throws QueueUnderflowException {
  		T retorno;
  		if(this.isEmpty()) {
  			throw new QueueUnderflowException();
  		}else {
  			retorno = this.array[0];
  			shiftLeft();
  			this.tail--;
  			return retorno;
  		}
  	}

}
