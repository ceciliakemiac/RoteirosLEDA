package teste;

import java.util.Arrays;

import adt.skipList.SkipListImpl;

public class teste {
	
	public static void main(String[] args) {
		SkipListImpl<String> skip = new SkipListImpl<String>(4);
		skip.insert(10, "A", 1);
		skip.insert(20, "B", 2);
		skip.insert(0, "C", 2);
		skip.insert(15, "D", 3);
		skip.insert(5, "E", 1);
		skip.insert(-10, "F", 1);
		skip.insert(30, "G", 3);
		skip.insert(9, "H", 2);
		skip.insert(17, "I", 2);
		skip.insert(-2, "J", 1);
		System.out.println(Arrays.toString(skip.nodesComAltura(4)));
	}
	
}
