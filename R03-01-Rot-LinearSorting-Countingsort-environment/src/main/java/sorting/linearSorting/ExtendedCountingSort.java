package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
  	public void sort(Integer[] array, int leftIndex, int rightIndex) {
  		
  		if(leftIndex >= 0 && rightIndex <= array.length - 1 && array != null && leftIndex < rightIndex) {
  			
  			int menorNegativo = menorNegativo(array, leftIndex, rightIndex);
  			
  			//valor que deve ser adicionado para cada elemento de array para que todos sejam positivos
  			int valorAdicionado = adicionaValor(menorNegativo, array, leftIndex, rightIndex);
  			
  			//arrayAdicionado será utilizado no lugar de array, seus valores são todos positivos
  			Integer[] arrayAdicionado = adicionaNoVetor(valorAdicionado, array, leftIndex, rightIndex);
  			
  			int maior = arrayAdicionado[leftIndex];
  			int menor = arrayAdicionado[leftIndex];
  			for(int i = leftIndex + 1; i <= rightIndex; i++) {
  				if(arrayAdicionado[i] > maior) {
  					maior = arrayAdicionado[i];
  				}
  				if(arrayAdicionado[i] < menor) {
  					menor = arrayAdicionado[i];
  				}
  			}
  			
  			//tamanho do vetor temporário otimizado
  			int tamanho = maior - menor + 2;
  			
  			Integer[] vetorAux = new Integer[array.length];
  			Integer[] vetorTemp = new Integer[tamanho];
  			
  			for(int i = 0; i < tamanho; i++) {
  				vetorTemp[i] = 0;
  			}
  			for(int j = leftIndex; j <= rightIndex; j++) {
  				vetorTemp[arrayAdicionado[j] - menor + 1] = vetorTemp[arrayAdicionado[j] - menor + 1] + 1;
  			}
  			for(int k = 1; k < tamanho; k++) {
  				vetorTemp[k] = vetorTemp[k] + vetorTemp[k - 1];
  			}
  			for(int i = leftIndex; i <= rightIndex; i++) {
  				vetorAux[vetorTemp[arrayAdicionado[i] - menor + 1] - 1 + leftIndex] = arrayAdicionado[i];
  				vetorTemp[arrayAdicionado[i] - menor + 1]--;
  			}
  			
  			for(int i = leftIndex; i <= rightIndex; i++) {
  				array[i] = vetorAux[i] - valorAdicionado;
  			}
  		}
  	}
  	
  	private Integer menorNegativo(Integer[] array, int leftIndex, int rightIndex) {
  		Integer menorNeg = 0;
  		for(int i = leftIndex; i <= rightIndex; i++) {
  			if(array[i] < menorNeg) {
  				menorNeg = array[i];
  			}
  		}
  		return menorNeg;
  	}
  	
  	private Integer adicionaValor(int menorNegativo, Integer[]array, int left, int right) {
  		Integer valor = 0;
  		if(menorNegativo < 0) {
  			valor = 1 - menorNegativo;
  		}
  		return valor;
  	}
  	
  	private Integer[] adicionaNoVetor(int valorAdicionado, Integer[] array, int left, int right) {
  		Integer[] novoVetor = new Integer[array.length];
  		for(int i = left; i <= right; i++) {
  			novoVetor[i] = array[i] + valorAdicionado;
  		}
  		return novoVetor;
  	}

}
