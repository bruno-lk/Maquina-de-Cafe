package troco;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// baseado nos codigos dessa fonta: http://www.devmedia.com.br/calculando-o-troco-em-uma-aplicacao-java/23617

public class Bandeja {
	// atriutos
	String fileName = "C:/Users/Bruno/workspace/Cafe/src/troco/BD.txt"; // endereço da base de dados do troco
	int vetorUnidades[] = new int[5]; // [.50],[1],[2],[5],[10]
	int reais[] = {10,5,2,1};
	int centavos[] = {50};
	

	// metodos
	public void init(){
		int i = 0;
		
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
			
		}catch (FileNotFoundException e) {
			System.err.printf("Erro " + e.getMessage());
		}catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",e.getMessage());
		}
	}
	
	public void atualizaBD(int vetor[]){
		try {
			FileWriter arq = new FileWriter(this.fileName);
			PrintWriter gravarArq = new PrintWriter(arq);
			
			for(int i = 0;i<5;i++){
				gravarArq.println(vetor[i]);		
			}
			arq.close();

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostraTrocos(int vetor[]){
		
		System.out.printf("%d moedas de 50 centavos\n", vetor[0]);
		System.out.printf("%d moedas de 1 real\n", vetor[1]);
		System.out.printf("%d cedulas de 2 reais\n", vetor[2]);
		System.out.printf("%d cedulas de 5 reais\n", vetor[3]);
		System.out.printf("%d cedulas de 10 reais\n", vetor[4]);
	}

	public int[] exibe(double troco, int vetorResult[]){
		int valor, i = 0, ct;
		// int vetorResult[] = new int[5]; // para calcular quanto sai da bandeja de troco 
		
		valor = (int) troco; // definindo notas do troco (parte inteira)
		
		// moeda de 1 real fica classificado como cedula por ser um valor não fracioando
		// e é mais facil de calcular assim
		
		while (valor != 0){
			ct = valor / this.reais[i]; // calculando a quantidade de notas
			if (ct != 0) {
		           System.out.println(ct + " unidade(s) de R$ " + this.reais[i]);
		           vetorResult[i+1] = ct;
		           valor = valor % this.reais[i]; // sobra
		        }
			i++; //proxima nota 
		}
		
		// definindo os centavos do troco (parte fracionária)
		valor = (int)Math.round((troco - (int)troco) * 100);
	    i = 0;
	    
	    while (valor != 0) { // não é necessaria
	    	ct = valor / this.centavos[i]; // calculando a qtde de moedas
	    	if (ct != 0) {
	    		System.out.println(ct +" unidade(s) de "+ this.centavos[i] + " centavo(s)");
	        	vetorResult[i] = ct;
	        	valor = valor % this.centavos[i]; // sobra
	        }
	        i++; // próximo centavo
	    }
	    
	    return vetorResult;
	    
	}

	public void verificaDisponibilidade(int[] vResult){
		int i;
		
		for(i=0;i<5;i++){
			if(this.vetorUnidades[i] < vResult[i]){
				System.out.println("\nNão há troco disponivel na bandeja");
				return;
			}
		}
		
		for(i=0;i<5;i++){
			this.vetorUnidades[i] -= vResult[i];
			atualizaBD(this.vetorUnidades);
		}
	}
}
