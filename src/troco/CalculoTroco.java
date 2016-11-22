package troco;

import troco.Bandeja;

public class CalculoTroco {

	public static void main(String[] args) {
		Bandeja bandeja = new Bandeja();
		
		double troco, recebido, conta;
		int vetorResult[] = new int[5]; // para calcular quanto sai da bandeja de troco 
		
		bandeja.init(); // inicia metodo principal
//		mostraTrocos(bandeja.vetorUnidades);
//		System.out.println();
		
		// inializa os valores p/ teste
		recebido = 10;
		conta = 2.5;
		troco = 0;
		
		if (recebido < conta) {
			System.out.println("Pagamento insulficiente\nFalta " + (conta - recebido));
		} else {
			troco = recebido - conta;
			System.out.println("Troco: " + troco);
		}
		
		vetorResult =  bandeja.exibe(troco,bandeja.reais,bandeja.centavos,vetorResult);
		
		bandeja.verificaDisponibilidade(bandeja.vetorUnidades, vetorResult);
		
//		System.out.println();
//		mostraTrocos(bandeja.vetorUnidades);
		// TODO: função p/ alterar bse dedados
		
	}
	
//	public static void mostraTrocos(int vetor[]){
//		
//		System.out.printf("%d moedas de 50 centavos\n", vetor[0]);
//		System.out.printf("%d moedas de 1 real\n", vetor[1]);
//		System.out.printf("%d cedulas de 2 reais\n", vetor[2]);
//		System.out.printf("%d cedulas de 5 reais\n", vetor[3]);
//		System.out.printf("%d cedulas de 10 reais\n", vetor[4]);
//	}
//
//	public static int[] exibe(double troco, int notas[], int centavos[], int vetorResult[]){
//		int valor, i = 0, ct;
////		int vetorResult[] = new int[5]; // para calcular quanto sai da bandeja de troco 
//		
//		valor = (int) troco; // definindo notas do troco (parte inteira)
//		
//		// moeda de 1 real fica classificado como cedula por ser um valor não fracioando
//		// e é mais facil de calcular assim
//		
//		while (valor != 0){
//			ct = valor / notas[i]; // calculando a quantidade de notas
//			if (ct != 0) {
//		           System.out.println(ct + " unidade(s) de R$ " + notas[i]);
//		           vetorResult[i+1] = ct;
//		           valor = valor % notas[i]; // sobra
//		        }
//			i++; //proxima nota 
//		}
//		
//		// definindo os centavos do troco (parte fracionária)
//		valor = (int)Math.round((troco - (int)troco) * 100);
//	    i = 0;
//	    
//	    while (valor != 0) { // não é necessaria
//	    	ct = valor / centavos[i]; // calculando a qtde de moedas
//	    	if (ct != 0) {
//	    		System.out.println(ct +" unidade(s) de "+ centavos[i] + " centavo(s)");
//	        	vetorResult[i] = ct;
//	        	valor = valor % centavos[i]; // sobra
//	        }
//	        i++; // próximo centavo
//	    }
//	    
//	    return vetorResult;
//	    
//	}
//
//	public static void verificaDisponibilidade(int[] vUnit, int[] vResult){
//		int i;
//		
//		for(i=0;i<5;i++){
//			if(vUnit[i] < vResult[i]){
//				System.out.println("Não há troco disponivel na bandeja\n");
//				return;
//			}
//		}
//		
//		for(i=0;i<5;i++){
//			vUnit[i] -= vResult[i];
//		}
//	}
}
