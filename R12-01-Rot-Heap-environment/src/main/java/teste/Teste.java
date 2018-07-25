package teste;

import java.util.Arrays;
import java.util.Comparator;

import adt.heap.Heap;
import adt.heap.HeapImpl;

public class Teste {
	
	public static <T> void main(String[] args) {
		HeapImpl<Integer> heap;
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
			
		};
		heap = new HeapImpl<Integer>(comparator);
		heap.insert(8);
		heap.insert(12);
		heap.insert(-2);
		heap.insert(7);
		heap.insert(8);
		heap.insert(-5);
		heap.insert(14);
		heap.insert(3);
		heap.insert(-10);
		heap.insert(0);
		T[] array = (T[]) heap.elementsByLevel(3);
		System.out.println(Arrays.toString(array));
	}

}
