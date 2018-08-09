package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
  		BSTNode<T> retorno = null;
  		if(node != null && !node.isEmpty()) {
  			BSTNode<T> pivot = (BSTNode<T>) node.getRight();
  			node.setRight(pivot.getLeft());
  			node.getRight().setParent(node);
  			pivot.setParent(node.getParent());
  			if(node.getParent() != null) {
  				if(node.getParent().getData().compareTo(pivot.getData()) > 0) {
  					node.getParent().setLeft(pivot);
  				}else {
  					node.getParent().setRight(pivot);
  				}
  			}
  			pivot.setLeft(node);
  			node.setParent(pivot);
  			
  			retorno = pivot;
  		}
  		return retorno;
  	}
  
  	/**
  	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
  	 * 
  	 * @param node
  	 * @return
  	 */
  	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
  		BSTNode<T> retorno = null;
  		if(node != null && !node.isEmpty()) {
  			BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
  			node.setLeft(pivot.getRight());
  			node.getLeft().setParent(node);
  			pivot.setParent(node.getParent());
  			if(node.getParent() != null) {
  				if(node.getParent().getData().compareTo(pivot.getData()) > 0) {
  					node.getParent().setLeft(pivot);
  				}else {
  					node.getParent().setRight(pivot);
  				}
  			}
  			pivot.setRight(node);
  			node.setParent(pivot);
  			
  			retorno = pivot;
  		}
  		return retorno;
  	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
