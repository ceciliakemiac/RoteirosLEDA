package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int retorno = 0;
		if(!node.isEmpty()) {
			retorno = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		}
		return retorno;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if(!this.isEmpty()) {
			int balance = this.calculateBalance(node);
			if(balance > 1) {
				int leftBalance = this.calculateBalance((BSTNode<T>) node.getLeft());
				if(leftBalance < 0) {
					this.leftRotation((BSTNode<T>) node.getLeft());
				}
				this.rightRotation(node);
			}else if(balance < -1) {
				int rightBalance = this.calculateBalance((BSTNode<T>) node.getRight());
				if(rightBalance > 0) {
					this.rightRotation((BSTNode<T>) node.getRight());
				}
				this.leftRotation(node);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> pai = (BSTNode<T>) node.getParent();
		while(pai != null) {
			this.rebalance(pai);
			pai = (BSTNode<T>) pai.getParent();
		}
	}
	
	private void leftRotation(BSTNode<T> node) {
		if(node != null) {
			BSTNode<T> aux = Util.leftRotation(node);
			if(node.equals(this.root)) {
				this.root = aux;
			}
		}
	}
	
	private void rightRotation(BSTNode<T> node) {
		if(node != null) {
			BSTNode<T> aux = Util.rightRotation(node);
			if(node.equals(this.root)) {
				this.root = aux;
			}
		}
	}
	
	@Override
	protected void insert(BTNode<T> node, T element) {
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
      	  this.rebalance((BSTNode<T>) node);
        }
	}
	
	@Override
	protected void remove(BSTNode<T> node) {
		if(node.isLeaf()) {
	 		   node.setData(null);
	 		   node.setLeft(null);
	 		   node.setRight(null);
	 		   this.rebalanceUp(node);
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
	
}
