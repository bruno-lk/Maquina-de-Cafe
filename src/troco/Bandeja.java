package troco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// baseado nos codigos dessa fonta: http://www.devmedia.com.br/calculando-o-troco-em-uma-aplicacao-java/23617

public class Bandeja {
	// atriutos
	String fileName = "C:/Users/Bruno/workspace/Cafe/src/troco/BD.txt"; // endereço da base de dados do troco
	int vetorUnidades[] = new int[5]; // [.50],[1],[2],[5],[10]
	int reais[] = {10,5,2,1};
	int centavos[] = {50};
	int i = 0;

	// metodos
	public void init(){
		try{
			FileReader arq = new FileReader(fileName);
			BufferedReader lerArq = new BufferedReader(arq);
			
			String linha = lerArq.readLine();
			
			while(linha != null){
				this.vetorUnidades[i] = Integer.parseInt(linha);
				linha = lerArq.readLine();
				i++;
			}
			
			arq.close();
			
		}catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",e.getMessage());
		}
	}
	
	public int adiciona(int opcao, int qtd){
		this.vetorUnidades[opcao] += qtd;
		return this.vetorUnidades[opcao];
	}
	
	public int retira(int opcao, int qtd){
		if(qtd <= this.vetorUnidades[opcao]){
			this.vetorUnidades[opcao] -= qtd;
			return this.vetorUnidades[opcao];
		}
		return -1;
	}

	public void mostraTrocos(int vetor[]){
		
		System.out.printf("%d moedas de 50 centavos\n", vetor[0]);
		System.out.printf("%d moedas de 1 real\n", vetor[1]);
		System.out.printf("%d cedulas de 2 reais\n", vetor[2]);
		System.out.printf("%d cedulas de 5 reais\n", vetor[3]);
		System.out.printf("%d cedulas de 10 reais\n", vetor[4]);
	}

	public int[] exibe(double troco, int notas[], int centavos[], int vetorResult[]){
		int valor, i = 0, ct;
		// int vetorResult[] = new int[5]; // para calcular quanto sai da bandeja de troco 
		
		valor = (int) troco; // definindo notas do troco (parte inteira)
		
		// moeda de 1 real fica classificado como cedula por ser um valor não fracioando
		// e é mais facil de calcular assim
		
		while (valor != 0){
			ct = valor / notas[i]; // calculando a quantidade de notas
			if (ct != 0) {
		           System.out.println(ct + " unidade(s) de R$ " + notas[i]);
		           vetorResult[i+1] = ct;
		           valor = valor % notas[i]; // sobra
		        }
			i++; //proxima nota 
		}
		
		// definindo os centavos do troco (parte fracionária)
		valor = (int)Math.round((troco - (int)troco) * 100);
	    i = 0;
	    
	    while (valor != 0) { // não é necessaria
	    	ct = valor / centavos[i]; // calculando a qtde de moedas
	    	if (ct != 0) {
	    		System.out.println(ct +" unidade(s) de "+ centavos[i] + " centavo(s)");
	        	vetorResult[i] = ct;
	        	valor = valor % centavos[i]; // sobra
	        }
	        i++; // próximo centavo
	    }
	    
	    return vetorResult;
	    
	}

	public void verificaDisponibilidade(int[] vUnit, int[] vResult){
		int i;
		
		for(i=0;i<5;i++){
			if(vUnit[i] < vResult[i]){
				System.out.println("Não há troco disponivel na bandeja\n");
				return;
			}
		}
		
		for(i=0;i<5;i++){
			vUnit[i] -= vResult[i];
		}
	}

}
