package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		if(!this.isEmpty()) {
			return height(this.root) - 1;
		}
		return -1;
	}
	
	private int height(BSTNode<T> node) {
		int retorno = 0;
		if(!node.isEmpty()) {
			int left = height((BSTNode<T>) node.getLeft());
			int right = height((BSTNode<T>) node.getRight());
			
			if(left > right) {
				retorno = height((BSTNode<T>) node.getLeft()) + 1;
			}else {
				retorno = height((BSTNode<T>) node.getRight()) + 1;
			}
		}
		return retorno;
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> retorno = new BSTNode<T>();
		if(element != null) {
			retorno = search(this.root, element);
		}
		return retorno;
		
		//vers�o iterativa, s� pra treinar mesmo
		/*BSTNode<T> aux = new BSTNode<T>();
		if(element != null) {
			aux = this.root;
			while(!aux.isEmpty() && aux.getData().compareTo(element) != 0) {
				if(aux.getData().compareTo(element) > 0) {
					aux = (BSTNode<T>) aux.getLeft();
				}else {
					aux = (BSTNode<T>) aux.getRight();
				}
			}
		}
		return aux;*/
	}
	
	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> retorno = new BSTNode<T>();
		if(!node.isEmpty()) {
			if(node.getData().compareTo(element) == 0) {
				retorno = node;
			}else {
				if(node.getData().compareTo(element) > 0) {
					retorno = search((BSTNode<T>) node.getLeft(), element);
				}else {
					retorno = search((BSTNode<T>) node.getRight(), element);
				}
			}
		}
		return retorno;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
		     insert(this.root, element);
		}
		
		//iterativo, s� pra testar mesmo...etc etc
		/*if(element != null) {
			BSTNode<T> aux = this.root;
			while(!aux.isEmpty()) {
				if(aux.getData().compareTo(element) != 0) {
					if(aux.getData().compareTo(element) > 0) {
						aux = (BSTNode<T>) aux.getLeft();
					}else {
						aux = (BSTNode<T>) aux.getRight();
					}
				}
			}
			aux.setData(element);
			
			BSTNode<T> left = new BSTNode<T>();
			left.setParent(aux);
			
			BSTNode<T> right = new BSTNode<T>();
			right.setParent(aux);
			
			aux.setLeft(left);
			aux.setRight(right);
		}*/
	}
	
	private void insert(BTNode<T> node, T element) {
        if (node.isEmpty()) {
           node.setData(element);
  
           BSTNode<T> left = new BSTNode<T>();
           left.setParent(node);
  
           BSTNode<T> right = new BSTNode<T>();
           right.setParent(node);
  
           node.setLeft(left);
           node.setRight(right);
        }else {
      	  if(node.getData().compareTo(element) != 0) {
      		  if (node.getData().compareTo(element) > 0) {
      	            insert(node.getLeft(), element);
      	         }else {
      	            insert(node.getRight(), element);
      	         }
      	  }
        }
     }

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> retorno = null;
		if(!this.isEmpty()) {
			retorno = maximum(this.root);
		}
		return retorno;
	}
	
	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> retorno = node;
		if(!node.getRight().isEmpty()) {
			retorno = maximum((BSTNode<T>) node.getRight());
		}
		return retorno;
	}

	@Override
	public BSTNode<T> minimum() {
		/*BSTNode<T> retorno = null;
		if(!this.isEmpty()) {
			retorno = this.root;
			while(!retorno.getLeft().isEmpty()) {
				retorno = (BSTNode<T>) retorno.getLeft();
			}
		}
		return retorno;*/
		
		if(!this.isEmpty()) {
			return minimum(this.root);
		}
		return null;
	}
	
	private BSTNode<T> minimum(BSTNode<T> node) {
		if(!node.getLeft().isEmpty()) {
			return minimum((BSTNode<T>) node.getLeft());
		}
		return node;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if(element != null) {
			BSTNode<T> node = this.search(element);
			if(!node.isEmpty()) {
				return sucessor(node);
			}
		}
		return null;
	}
	
	private BSTNode<T> sucessor(BSTNode<T> node) {
		if(!node.getRight().isEmpty()) {
			return minimum((BSTNode<T>) node.getRight());
		}else {
			BSTNode<T> pai = (BSTNode<T>) node.getParent();
			while(pai != null && !pai.isEmpty() && !pai.getLeft().equals(node)) {
				node = pai;
				pai = (BSTNode<T>) node.getParent();
			}
			return pai;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> retorno = null;
		if(element != null) {
			BSTNode<T> node = this.search(element);
			if(!node.isEmpty()) {
				retorno = predecessor(node);
			}
		}
		return retorno;
	}
	
	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> retorno = null;
		if(!node.getLeft().isEmpty()) {
			retorno = maximum((BSTNode<T>) node.getLeft());
		}else {
			BSTNode<T> pai = (BSTNode<T>) node.getParent();
			if(pai != null && !pai.isEmpty() && pai.getRight().equals(node)) {
				retorno = pai;
			}else if(pai != null && !pai.isEmpty()) {
				retorno = predecessorR((BSTNode<T>) pai);
			}
		}
		return retorno;
	}
	
	private BSTNode<T> predecessorR(BSTNode<T> node) {
		BSTNode<T> retorno = null;
		BSTNode<T> pai = (BSTNode<T>) node.getParent();
		if(pai != null && !pai.isEmpty() && pai.getRight().equals(node)) {
			retorno = pai;
		}else if(pai != null && !pai.isEmpty()) {
			retorno = predecessorR(pai);
		}
		return retorno;
	}

	@Override
	public void remove(T element) {
		if(element != null) {
			BSTNode<T> node = this.search(element);
			if(!node.isEmpty()) {
				remove(node);
			}
		}
	}
	
	private void remove(BSTNode<T> node) {
		if(node.isLeaf()) {
	 		   node.setData(null);
	 		   node.setLeft(null);
	 		   node.setRight(null);
	 	   }else if(!node.getRight().isEmpty()) {
	 		   BSTNode<T> sucessor = sucessor(node);
	 		   T aux = node.getData();
	 		   node.setData(sucessor.getData());
	 		   sucessor.setData(aux);
	 		   remove(sucessor);
	 	   }else if(!node.getLeft().isEmpty()) {
	 		   BSTNode<T> predecessor = predecessor(node);
	 		   T aux = node.getData();
	 		   node.setData(predecessor.getData());
	 		   predecessor.setData(aux);
	 		   remove(predecessor);
	 	   }
	}

	@Override
	public T[] preOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		if(!this.isEmpty()) {
			int indice = 0;
			preOrder(array, indice, this.root);
		}
		return array;
	}
	
	private int preOrder(T[] array, int indice, BSTNode<T> node) {
		if(!node.isEmpty()) {
			array[indice++] = node.getData();
			indice = preOrder(array, indice, (BSTNode<T>) node.getLeft());
			indice = preOrder(array, indice, (BSTNode<T>) node.getRight());
		}
		return indice;
	}

	@SuppressWarnings("unchecked")
    @Override
    public T[] order() {
 	   T[] array = (T[]) new Comparable[this.size()];
 	   if(!this.isEmpty()) {
 		   int indice = 0;
 		   order(this.root, array, indice);
 	   }
 	   return array;
    }
    
    private int order(BSTNode<T> node, T[] array, int indice) {
 	   if(!node.isEmpty()) {
 		   indice = order((BSTNode<T>) node.getLeft(), array, indice);
 		   array[indice++] = node.getData();
 		   indice = order((BSTNode<T>) node.getRight(), array, indice);
 	   }
 	   return indice;
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public T[] postOrder() {
 	   T[] array = (T[]) new Comparable[this.size()];
 	   if(!this.isEmpty()) {
 		   int indice = 0;
 		   postOrder(this.root, array, indice);
 	   }
 	   return array;
    }
    
    private int postOrder(BSTNode<T> node, T[] array, int indice) {
 	   if(!node.isEmpty()) {
 		   indice = postOrder((BSTNode<T>) node.getLeft(), array, indice);
 		   indice = postOrder((BSTNode<T>) node.getRight(), array, indice);
 		   array[indice++] = node.getData();
 	   }
 	   return indice;
    }
    
    public T[] descendingOrder() {
    	@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
    	if(!this.isEmpty()) {
    		int indice = 0;
    		descendingOrder(array, indice, this.root);
    	}
    	return array;
    }
    
    private int descendingOrder(T[] array, int indice, BSTNode<T> node) {
    	if(!node.isEmpty()) {
    		indice = descendingOrder(array, indice, (BSTNode<T>) node.getRight());
    		array[indice++] = node.getData();
    		indice = descendingOrder(array, indice, (BSTNode<T>) node.getLeft());
    	}
    	return indice;
    }
    
	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}
	
	public boolean ehBinaria() {
		if(!this.isEmpty()) {
			return ehBinaria(this.root);
		}
		return true;
	}
	
	private boolean ehBinaria(BSTNode<T> node) {
		boolean retorno;
		
		if(node.isEmpty()) {
			retorno = true;
			
		}else if(node.isLeaf()) {
			retorno = true;
			
		}else if(node.getLeft().isEmpty() &&
				node.getRight().getData().compareTo(node.getData()) > 0) {
			retorno = ehBinaria((BSTNode<T>) node.getRight());
			
		}else if(node.getRight().isEmpty() && 
				node.getLeft().getData().compareTo(node.getData()) < 0) {
			retorno = ehBinaria((BSTNode<T>) node.getLeft());
			
		}else if(node.getLeft().getData().compareTo(node.getData()) < 0 && 
				node.getRight().getData().compareTo(node.getData()) > 0) {
			boolean retorno1 = ehBinaria((BSTNode<T>) node.getLeft());
			boolean retorno2 = ehBinaria((BSTNode<T>) node.getRight());
			retorno = retorno1 && retorno2;
			
		}else {
			retorno = false;
		}
		
		return retorno;
	}
	
	public int totalFolhas() {
		return totalFolhas(this.root);
	}
	
	private int totalFolhas(BSTNode<T> node) {
		int retorno = 0;
		if(!node.isEmpty()) {
			if(!node.isLeaf()) {
				retorno = totalFolhas((BSTNode<T>) node.getLeft()) + 
						totalFolhas((BSTNode<T>) node.getRight());
			}else {
				retorno = 1;
			}
		}
		return retorno;
	}
	
	public boolean isDecendent(T d, T p) {
		boolean retorno = false;
		if(d != null && p != null) {
			BSTNode<T> node = this.search(p);
			if(!node.isLeaf() && !node.isEmpty()) {
				if(node.getData().compareTo(d) < 0 && !node.getRight().isEmpty()) {
					if(node.getRight().getData().compareTo(d) == 0) {
						retorno = true;
					}else {
						retorno = this.isDecendent(d, node.getRight().getData());
					}
				}else if(node.getData().compareTo(d) > 0 && !node.getLeft().isEmpty()) {
					if(node.getLeft().getData().compareTo(d) == 0) {
						retorno = true;
					}else {
						retorno = this.isDecendent(d, node.getLeft().getData());
					}
				}
			}
		}
		return retorno;
	}

}
