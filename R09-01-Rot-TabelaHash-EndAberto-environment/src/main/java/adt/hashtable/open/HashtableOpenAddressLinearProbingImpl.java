package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
  			HashFunctionClosedAddressMethod method) {
  		super(size);
  		hashFunction = new HashFunctionLinearProbing<T>(size, method);
  		this.initiateInternalTable(size);
  	}
  
  	@Override
  	public void insert(T element) {
  		if(this.isFull()) {
  			throw new HashtableOverflowException();
  		}
  		
  		if(element != null && this.search(element) == null) {
  			int probe = 0;
  			int index = -1;
  			while(probe < this.capacity()) {
  				if(this.hashFunction instanceof HashFunctionLinearProbing) {
  					index = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
  				}
  				
  				if(this.table[index] == null || this.table[index].equals(new DELETED())) {
  					this.table[index] = element;
  					this.elements++;
  					break;
  				}
  				probe++;
  				this.COLLISIONS++;
  			}
  		}
  	}
  
  	@Override
  	public void remove(T element) {
  		if(element != null && !this.isEmpty()) {
  			T elemento = this.search(element);
  			
  			if(elemento != null) {
  				this.table[this.indexOf(elemento)] = new DELETED();
  				this.elements--;
  			}
  		}
  	}
  
  	@Override
  	public T search(T element) {
  		T retorno = null;
  		if(element != null && !this.isEmpty()) {
  			if(this.indexOf(element) != -1) {
  				retorno = element;
  			}
  		}
  		return retorno;
  	}
  
  	@Override
  	public int indexOf(T element) {
  		int retorno = -1;
  		if(element != null && !this.isEmpty()) {
  			int probe = 0;
  			int index = -1;
  			while(probe < this.capacity()) {
  				if(this.hashFunction instanceof HashFunctionLinearProbing) {
  					index = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
  				}
  				
  				if(this.table[index] == null) {
  					break;
  				}
  				if(this.table[index].equals(element)) {
  					retorno = index;
  					break;
  				}
  				probe++;
  			}
  		}
  		return retorno;
  	}

}
