package adt.skipList;

import java.util.LinkedList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void insert(int key, T newValue, int height) {
		if(height > this.maxHeight) {
			throw new RuntimeException();
		}
		
		SkipListNode<T> node = this.root;
		SkipListNode<T>[] update = new SkipListNode[height];
		for(int i = height - 1; i >= 0; i--) {
			while(node.forward[i].key < key) {
				node = node.forward[i];
			}
			update[i] = node;
		}
		node = node.forward[0];
		
		if(node.key == key) {
			node.setValue(newValue);
		}else {
			SkipListNode<T> novo = new SkipListNode<T>(key, height, newValue);
			for(int i = 0; i <= height - 1; i++) {
				novo.forward[i] = update[i].forward[i];
				update[i].forward[i] = novo;
			}
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void remove(int key) {
		if(this.size() > 0) {
			SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
			SkipListNode<T> node = this.root;
			for(int i = this.maxHeight - 1; i >= 0; i--) {
				while(node.forward[i].key < key) {
					node = node.forward[i];
				}
				update[i] = node;
			}
			node = node.forward[0];
			
			if(node.key == key) {
				for(int i = 0; i <= node.height(); i++) {
					if(!update[i].forward[i].equals(node)) {
						break;
					}
					update[i].forward[i] = node.forward[i];
				}
			}
		}
	}

	@Override
	public int height() {
		int retorno = 0;
		if(this.size() > 0) {
			SkipListNode<T> node = this.root;
			int i = this.maxHeight - 1;
			while(node.forward[i].equals(this.NIL)) {
				i--;
			}
			retorno = i + 1;
		}
		return retorno;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> retorno = null;
		SkipListNode<T> node = this.root;
		for(int i = this.maxHeight - 1; i >= 0; i--) {
			while(node.forward[i].key < key) {
				node = node.forward[i];
			}
		}
		node = node.forward[0];
		
		if(node.getKey() == key) {
			retorno = node;
		}
		return retorno;
	}

	@Override
	public int size() {
		int retorno = 0;
		SkipListNode<T> node = this.root.forward[0];
		while(!node.equals(NIL)) {
			node = node.forward[0];
			retorno++;
		}
		return retorno;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] array = new SkipListNode[this.size() + 2];
		SkipListNode<T> aux = this.root;
		int indice = 0;
		while(!aux.equals(this.NIL)) {
			array[indice] = aux;
			aux = aux.forward[0];
			indice++;
		}
		array[indice] = this.NIL;
		return array;
	}
	
	public SkipListNode<T> searchR(int key) {
		SkipListNode<T> retorno = null;
		if(this.size() > 0) {
			return searchR(key, this.root, this.height());
		}
		return retorno;
	}
	
	private SkipListNode<T> searchR(int key, SkipListNode<T> node, int altura) {
		if(node.forward[altura].key < key) {
			return searchR(key, node.forward[altura], altura);
		}else if(altura > 0) {
			altura--;
			return searchR(key, node, altura);
		}else {
			if(node.forward[altura].key == key) {
				return node.forward[altura];
			}else {
				return null;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public SkipListNode<T>[] nodesComAltura(int altura) {
		LinkedList<SkipListNode<T>> lista = new LinkedList<SkipListNode<T>>();
		if(this.size() > 0) {
			nodesComAltura(altura, this.root, lista);
		}
		
		SkipListNode<T>[] array = new SkipListNode[lista.size()];
		for(int i = 0; i < lista.size(); i++) {
			array[i] = lista.get(i);
		}
		return array;
	}
	
	private void nodesComAltura(int altura, SkipListNode<T> node,
			LinkedList<SkipListNode<T>> lista) {
		if(!node.forward[altura - 1].equals(NIL)) {
			if(node.forward[altura - 1].height() == altura) {
				lista.add(node.forward[altura - 1]);
			}
			node = node.forward[altura - 1];
			nodesComAltura(altura, node, lista);
		}
	}

}
