import java.util.Scanner;

/**
 * 
 * Thiago Rodrigo Monteiro Salgado - 21954456
 *  TRABALHO PRÁTICO 3 SOBRE PAGINAÇÃO 
 *  professor: Eduardo Luzeiro
 *  Disciplina: Sistemas Operacionais
 * 
 * */

public class Main {


	public static void main(String[] args) {
		
		System.out.println("\n Seja bem vindo ao Sistema de Paginação\n --------------------------------------"
				+ "---\nAluno: Thiago Rodrigo Monteiro Salgado (21954456)\nProfessor: Eduardo Luzeiro\n"
				+ "Disciplina: Sistemas Operacionais\n\n\n");

		System.out.println("digite o número de páginas");
		Scanner scanner = new Scanner(System.in);
		int numPaginas = scanner.nextInt();
		
		String arquivoNome ="";
		System.out.println("Digite o endereço do arquivo");
		arquivoNome = scanner.next();
		
		scanner.close();
		
		LeituraArquivo arquivo = new LeituraArquivo(arquivoNome);
		String texto = arquivo.getTextoCompleto();
		int tamanho; //para corrigir um bug

		String[] stringReferencia = texto.split(";");
	  
		tamanho = stringReferencia.length;
		if(stringReferencia[ stringReferencia.length-2].equals("0,0")) //teste para retirar
		{
			//System.out.println("é uma boa alternativa");
			tamanho = stringReferencia.length - 2;
		}else {
			tamanho = stringReferencia.length;
		}
		
//		System.out.println("meu tamanho: " + tamanho);
	  
	  


//	  int numPaginas = 8000;
	  
	  
	  
	  // FIFO

	  
	  FIFO ff = new FIFO(numPaginas);
	  for (int i = 0; i < tamanho; i++) 
	  {
//		  System.out.println(stringReferencia[i]);
		  ff.inserir(stringReferencia[i]);
	  }
	  
	  System.out.println("\nPage Faults no meu algoritmo FIFO -> "+ ff.getNumeroDeFalhas());
	  

	  //LRU
		

	  LRU ll = new LRU(numPaginas);
	  for (int i = 0; i < tamanho; i++) 
	  {
//		  System.out.println(stringReferencia[i]);
		  ll.inserir(stringReferencia[i]);
	  }
	  
	  System.out.println("\nPage Faults no meu algoritmo LRU -> "+ 	ll.getNumeroDeFalhas());
	  
	  
	  // SEGUNDA CHANCE
//	  SegundaChance sc = new SegundaChance(numPaginas);
//
//	  for (int i = 0; i < tamanho; i++) {
////		  System.out.println(stringReferencia[i]);
//	   sc.inserir(stringReferencia[i]);
//	//   sc.imprimirQuadros();
//
//	  }
//	  System.out.println("\nPage Faults do SEGUNDA CHANCE: " + sc.getPageFaults());
	  
	  ////////////////////teste
	  
	  
	  AlgoritmoSegundaChance sc2 = new AlgoritmoSegundaChance(numPaginas);

	  for (int i = 0; i < tamanho; i++) {
//		  System.out.println(stringReferencia[i]);
	   sc2.inserir(stringReferencia[i]);
	//   sc.imprimirQuadros();

	  }
	  System.out.println("\nPage Faults do SEGUNDA CHANCE modelo ponteiro2: " + sc2.getPageFaults());

	

	}
	

	

	
}
