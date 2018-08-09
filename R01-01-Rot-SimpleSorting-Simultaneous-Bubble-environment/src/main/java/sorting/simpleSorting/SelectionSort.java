package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for(int i = 0; i <= rightIndex - leftIndex; i++) {
  			int min = leftIndex + i;
  			for(int j = leftIndex + i; j <= rightIndex - leftIndex; j++) {
  				if(array[j].compareTo(array[min]) < 0) {
  					min = j;
  				}
  			}
  			Util.swap(array, min, i);
  		}
	}
}
