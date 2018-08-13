package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
public void sort(T[] array, int leftIndex, int rightIndex) {
  		
  		if(leftIndex >= 0 && rightIndex <= array.length - 1 && array != null && leftIndex < rightIndex) {
  			
  			int pivot = leftIndex + 1;
  			
  			while(pivot <= rightIndex) {
  				if(pivot > leftIndex && array[pivot].compareTo(array[pivot - 1]) >= 0) {
  					pivot++;
  				}else {
  					if(pivot == leftIndex) {
  						pivot++;
  					}else {
  						Util.swap(array, pivot, pivot - 1);
  						pivot--;
  					}
  				}
  			}
  		}
  	}
}
