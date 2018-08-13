package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
   		this.head = new SingleLinkedListNode<T>();
   	}
  
  	@Override
  	public boolean isEmpty() {
  		return this.head.isNIL();
  	}
  
  	@Override
  	public int size() {
  		int tamanho = 0;
  		SingleLinkedListNode<T> aux = this.head;
  		while(!aux.isNIL()) {
  			tamanho++;
  			aux = aux.next;
  		}
  		return tamanho;
  	}
  
  	@Override
  	public T search(T element) {
  		SingleLinkedListNode<T> procurado = this.head;
  		while(!procurado.isNIL() && !procurado.data.equals(element)) {
  			procurado = procurado.next;
  		}
  		return procurado.data;
  	}
  
  	@Override
  	public void insert(T element) {
  		SingleLinkedListNode<T> auxHead = this.head;
  		if(this.head.isNIL()) {
  			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
  			newHead.next = this.head;
  			this.head = newHead;
  		}else {
  			while(!auxHead.next.isNIL()) {
  				auxHead = auxHead.next;
  			}
  			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
  			newNode.next = auxHead.next;
  			auxHead.next = newNode;
  		}
  	}
  
  	@Override
  	public void remove(T element) {
  		if(this.head.data.equals(element)) {
  			this.head = this.head.next;
  		}else {
  			SingleLinkedListNode<T> aux = this.head;
  			SingleLinkedListNode<T> previous = new SingleLinkedListNode<T>();
  			while(!aux.isNIL() && !aux.data.equals(element)) {
  				previous = aux;
  				aux = aux.next;
  			}
  			if(!aux.isNIL()) {
  				previous.next = aux.next;
  			}
  		}
  	}
  
  	@Override
  	public T[] toArray() {
  		T[] array = (T[]) new Object[this.size()];
  		SingleLinkedListNode<T> aux = this.head;
  		int indice = 0;
  		while(!aux.isNIL()) {
  			array[indice] = aux.data;
  			aux = aux.next;
  			indice++;
  		}
  		return array;
  	}
  
  	public SingleLinkedListNode<T> getHead() {
  		return head;
  	}
  
  	public void setHead(SingleLinkedListNode<T> head) {
  		this.head = head;
  	}

}
