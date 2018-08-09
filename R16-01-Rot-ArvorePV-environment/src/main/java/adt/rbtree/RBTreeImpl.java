package adt.rbtree;

import java.util.ArrayList;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		int retorno = 0;
		if(!this.isEmpty()) {
			retorno = blackHeightLeft((RBNode<T>) this.root);
		}
		return retorno;
	}
	
	private int blackHeightLeft(RBNode<T> node) {
		if(!node.getLeft().isEmpty()) {
			if(((RBNode<T>) node.getLeft()).getColour().equals(Colour.BLACK)) {
				return 1 + blackHeightLeft((RBNode<T>) node.getLeft());
			}else {
				return 0 + blackHeightLeft((RBNode<T>) node.getLeft());
			}
		}else {
			return 1;
		}
	}
	
	private int blackHeightRight(RBNode<T> node) {
		if(!node.getLeft().isEmpty()) {
			if(((RBNode<T>) node.getLeft()).getColour().equals(Colour.BLACK)) {
				return 1 + blackHeightRight((RBNode<T>) node.getLeft());
			}else {
				return 0 + blackHeightRight((RBNode<T>) node.getLeft());
			}
		}else {
			return 1;
		}
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		boolean retorno = true;
		if(!this.isEmpty()) {
			retorno = verifyChildrenOfRedNodes((RBNode<T>) this.root);
		}
		return retorno;
     }
	
	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		if(!node.isEmpty()) {
			if(node.getColour().equals(Colour.RED)) {
				if(((RBNode<T>) node.getLeft()).getColour().equals(Colour.RED) || 
						((RBNode<T>) node.getRight()).getColour().equals(Colour.RED)) {
					return false;
				}
			}
			return verifyChildrenOfRedNodes((RBNode<T>) node.getLeft()) &&
					verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
		}
		return true;
	}
 
    /**
     * Verifies the black-height property from the root. The method blackHeight
     * returns an exception if the black heights are different.
     */
    private boolean verifyBlackHeight() {
       return verifyBlackHeight((RBNode<T>) this.root);
    }
 
    private boolean verifyBlackHeight(RBNode<T> node) {
       if (!node.isEmpty()) {
          if (blackHeightLeft(node) != blackHeightRight(node)) {
             return false;
          } else {
             verifyBlackHeight((RBNode<T>) node.getLeft());
             verifyBlackHeight((RBNode<T>) node.getRight());
          }
       }
       return true;
    }
 
    @Override
    public void insert(T value) {
       if (value != null) {
          insert((RBNode<T>) this.root, value);
       }
    }
 
    private void insert(RBNode<T> node, T value) {
       if (node.isEmpty()) {
          node.setData(value);
 
          RBNode<T> left = new RBNode<T>();
          left.setParent(node);
 
          RBNode<T> right = new RBNode<T>();
          right.setParent(node);
 
          node.setLeft(left);
          node.setRight(right);
 
          node.setColour(Colour.RED);
          fixUpCase1(node);
       } else {
          if (node.getData().compareTo(value) != 0) {
             if (node.getData().compareTo(value) > 0) {
                insert((RBNode<T>) node.getLeft(), value);
             } else {
                insert((RBNode<T>) node.getRight(), value);
             }
          }
       }
    }
 
    @Override
    public RBNode<T>[] rbPreOrder() {
       RBNode<T>[] preOrder = (RBNode<T>[]) new RBNode[this.size()];
       ArrayList<RBNode<T>> aux = new ArrayList();
 
       rbPreOrder(aux, (RBNode<T>) this.root);
 
       for (int i = 0; i < aux.size(); i++)
          preOrder[i] = aux.get(i);
 
       return preOrder;
    }
 
    private void rbPreOrder(List<RBNode<T>> array, RBNode<T> node) {
       if (!node.isEmpty()) {
          array.add(node);
          rbPreOrder(array, (RBNode<T>) node.getLeft());
          rbPreOrder(array, (RBNode<T>) node.getRight());
       }
    }
 
    // FIXUP methods
    protected void fixUpCase1(RBNode<T> node) {
       if (node.equals(this.root)) {
          node.setColour(Colour.BLACK);
       } else {
          fixUpCase2(node);
       }
    }
 
    protected void fixUpCase2(RBNode<T> node) {
       if (((RBNode<T>) node.getParent()).getColour().equals(Colour.RED)) {
          fixUpCase3(node);
       }
    }
 
    protected void fixUpCase3(RBNode<T> node) {
       RBNode<T> parent = (RBNode<T>) node.getParent();
       RBNode<T> grandFather = (RBNode<T>) parent.getParent();
       RBNode<T> uncle = getUncle(node);
 
       if (uncle.getColour().equals(Colour.RED)) {
          uncle.setColour(Colour.BLACK);
          parent.setColour(Colour.BLACK);
          grandFather.setColour(Colour.RED);
          this.fixUpCase1(grandFather);
       } else {
          fixUpCase4(node);
       }
    }
 
    private RBNode<T> getUncle(RBNode<T> node) {
       RBNode<T> parent = (RBNode<T>) node.getParent();
       RBNode<T> uncle;
       if (isLeftChild(parent)) {
          uncle = (RBNode<T>) parent.getParent().getRight();
       } else {
          uncle = (RBNode<T>) parent.getParent().getLeft();
       }
       return uncle;
    }
 
    private boolean isLeftChild(RBNode<T> node) {
       return node.getParent().getLeft().equals(node);
    }
 
    protected void fixUpCase4(RBNode<T> node) {
       RBNode<T> next = node;
       RBNode<T> parent = (RBNode<T>) node.getParent();
 
       if (!isLeftChild(node) && isLeftChild(parent)) {
          leftRotation(parent);
          next = (RBNode<T>) node.getLeft();
       } else if (isLeftChild(node) && !isLeftChild(parent)) {
          rightRotation(parent);
          next = (RBNode<T>) node.getRight();
       }
       fixUpCase5(next);
    }
 
    protected void fixUpCase5(RBNode<T> node) {
       RBNode<T> parent = (RBNode<T>) node.getParent();
       RBNode<T> grandFather = (RBNode<T>) parent.getParent();
 
       parent.setColour(Colour.BLACK);
       grandFather.setColour(Colour.RED);
       if (isLeftChild(node)) {
          rightRotation(grandFather);
       } else {
          leftRotation(grandFather);
       }
    }
 
    private void leftRotation(RBNode<T> node) {
       RBNode<T> aux = (RBNode<T>) Util.leftRotation(node);
       if (node.equals(this.root)) {
          this.root = aux;
       }
    }
 
    private void rightRotation(RBNode<T> node) {
       RBNode<T> aux = (RBNode<T>) Util.rightRotation(node);
       if (node.equals(this.root)) {
          this.root = aux;
       }
    }
    
    public int nosPretos() {
    	int retorno = 0;
    	if(!this.isEmpty()) {
    		retorno = nosPretos((RBNode<T>) this.root);
    	}
    	return retorno;
    }
    
    private int nosPretos(RBNode<T> node) {
    	int retorno = 0;
    	if(!node.isEmpty()) {
    		if(node.getColour().equals(Colour.BLACK)) {
    			retorno = 1 + nosPretos((RBNode<T>) node.getLeft()) +
    					nosPretos((RBNode<T>) node.getRight());
    		}else {
    			retorno = 0 + nosPretos((RBNode<T>) node.getLeft()) +
    					nosPretos((RBNode<T>) node.getRight());
    		}
    	}
    	return retorno;
    }
    
}
