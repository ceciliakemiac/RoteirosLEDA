package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
  		
  		if(leftIndex >= 0 && rightIndex <= array.length - 1 && array != null && leftIndex < rightIndex) {
  			
  			int tamanho = rightIndex - leftIndex + 1;
  			int gap = tamanho;
  			
  			boolean troca = true;
  			while(troca || gap > 1) {
  				gap = (int) (gap / 1.25);
  				if(gap < 1) {
  					gap = 1;
  				}
  				
  				int i = leftIndex;
  				troca = false;
  				
  				while(i + gap <= rightIndex) {
  					if(array[i].compareTo(array[i + gap]) > 0) {
  						Util.swap(array, i, i + gap);
  						troca = true;
  					}
  					i++;
  				}
  			}
  		}
  	}}
