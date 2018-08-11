package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {
 	   
 	  if(leftIndex >= 0 && rightIndex <= array.length - 1 && array != null && leftIndex < rightIndex) {
 	      Integer max = maior(array, leftIndex, rightIndex);
 	      Integer[] vetorAux = new Integer[array.length];
 	      Integer[] vetorTemp = new Integer[max + 1];
 
 	      for (int i = 0; i <= max; i++) {
 	         vetorTemp[i] = 0;
 	      }
 	      for (int i = leftIndex; i <= rightIndex; i++) {
 	         vetorTemp[array[i]] = vetorTemp[array[i]] + 1;
 	      }
 	      for (int i = 1; i <= max; i++) {
 	         vetorTemp[i] = vetorTemp[i] + vetorTemp[i - 1];
 	      }
 	      for (int i = leftIndex; i <= rightIndex; i++) {
 	         vetorAux[vetorTemp[array[i]] - 1 + leftIndex] = array[i];
 	         vetorTemp[array[i]]--;
 	      }
 
 	      for (int i = leftIndex; i <= rightIndex; i++) {
 	         array[i] = vetorAux[i];
 	      }
 	  }
 
    }
 
    private Integer maior(Integer[] array, int leftIndex, int rightIndex) {
       Integer maior = array[leftIndex];
       for (int i = leftIndex; i <= rightIndex; i++) {
          if (array[i] > maior) {
             maior = array[i];
          }
       }
       return maior;
    }

}
