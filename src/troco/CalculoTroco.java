package troco;

import troco.Bandeja;

public class CalculoTroco {

	public static void main(String[] args) {
		Bandeja bandeja = new Bandeja();
		
		double troco, recebido, conta;
		int vetorResult[] = new int[5]; // para calcular quanto sai da bandeja de troco 
		
		bandeja.init(); // inicia metodo principal
//		mostraTrocos(bandeja.vetorUnidades);
		
		// inializa os valores p/ teste
		recebido = 10;
		conta = 2.5;
		troco = 0;
		
		if (recebido < conta) {
			System.out.println("Pagamento insulficiente\nFalta " + (conta - recebido));
		} else {
			troco = recebido - conta;
			System.out.println("Troco: " + troco + "\n");
		}
		
		vetorResult =  bandeja.exibe(troco,vetorResult);
		
		bandeja.verificaDisponibilidade(vetorResult);
		
//		mostraTrocos(bandeja.vetorUnidades);
		// TODO: função p/ alterar base dedados
		
	}
}
