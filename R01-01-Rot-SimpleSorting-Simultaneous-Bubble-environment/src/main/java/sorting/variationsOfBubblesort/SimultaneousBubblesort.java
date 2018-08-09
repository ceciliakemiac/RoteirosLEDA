package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends
		AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int i = 0;
  		boolean troca = true;
  		while(i < rightIndex - leftIndex && troca) {
  			troca = false;
  			
  			for(int j = leftIndex; j < rightIndex - i; j++) {
  				if(array[j].compareTo(array[j + 1]) > 0) {
  					troca = true;
  					Util.swap(array, j + 1, j);
  				}
  			}
  			for(int k = rightIndex; k > leftIndex + i; k--) {
  				if(array[k].compareTo(array[k - 1]) < 0) {
  					troca = true;
  					Util.swap(array, k, k - 1);
  				}
  			}
  			i++;
  		}
	}
}
