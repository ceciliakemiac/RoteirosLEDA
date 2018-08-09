package teste;

import adt.rbtree.RBTreeImpl;

public class Teste {
	
	public static void main(String[] args) {
		RBTreeImpl<Integer> tree = new RBTreeImpl<Integer>();
		tree.insert(41);
		tree.insert(38);
		tree.insert(31);
		tree.insert(12);
		tree.insert(19);
		tree.insert(8);
		System.out.println(tree.nosPretos());
	}
	
}
