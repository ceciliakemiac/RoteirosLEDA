package teste;

import adt.bst.BSTImpl;

public class Teste {
	static BSTImpl<Integer> tree = new BSTImpl<Integer>();
	
	public static void main(String[] args) {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for(Integer valor : array) {
			tree.insert(valor);
		}
		System.out.println(tree.isDecendent(23, 23));
	}
	
}
