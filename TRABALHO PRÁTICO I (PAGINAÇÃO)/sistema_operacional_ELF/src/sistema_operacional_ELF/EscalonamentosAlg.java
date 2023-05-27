package sistema_operacional_ELF;

import java.util.ArrayList;

public class EscalonamentosAlg extends Processo {
	
	
	public static void FCFS(ArrayList<Processo> processos) {

		
		
		int numProc = processos.get(0).numProcessos; //gambiarra
        float mediaTempoVida = 0;
        float mediaTempoEspera = 0;

        
	    // achando o momento em que finalizam e somando todos para achar o tempo medio e 
        //tempo de médio de espera
	    for(int  i = 0 ; i < numProc; i++) // um laço de repetição para o número de processos
	    {

	    	
	        if( i == 0) // primeiro caso (primeiro elemento)
	        {
	            processos.get(i).setTempoCompletou( processos.get(i).tempoIngresso + processos.get(i).duracao ); 
	        }
	        else //agora para casos posteriores
	        {
	        	if( processos.get(i).tempoIngresso > processos.get(i - 1).tempoCompletou ) // se ele entrar em um tempo depois que o processo anterior terminou
	        
	        	{
	        	
	        		// o processo vai ser alocado na próxima posição sem problema algum com o tempo que terminou do tempo que entrou + sua duração;
	        		processos.get(i).setTempoCompletou( processos.get(i).tempoIngresso + processos.get(i).duracao ); 
	            
	        
	        	}
	        
	        	else { //caso  ele entre e o último ainda não tenha sido completado...
	        			        		
	        		//vamos pegar o tempo que o anterior terminou + a duração total do processo que estamos
	        		processos.get(i).setTempoCompletou( processos.get(i - 1).tempoCompletou + processos.get(i).duracao );
	        	}     		
	        
	        }
	        
	        
	        //vamos guardar em um vetor em cada posição o tempo de vida (ou seja, o tempo que cada um dos processos durou processando que consiste em: 
	        // tempoTotal - tempoIngresso
	        processos.get(i).tempoVida = processos.get(i).tempoCompletou - processos.get(i).tempoIngresso ;
	        
	        //o mesmo acontece para o tempoMedio que consiste em: tempo de vida - duracaoTotal
	        processos.get(i).tempoEspera =  processos.get(i).tempoVida - processos.get(i).duracao;
	        
	        				//////////////// media ////////////////
	        mediaTempoVida +=   processos.get(i).tempoVida; // somatório do tempo de vida dividido pelo numero de processos
	        mediaTempoEspera +=  processos.get(i).tempoEspera; // somatório do tempo de espera dividido pelo numero de processos
	        
	    }

		
	    System.out.println("------------------------");
	    System.out.println("ORDEM DE PROCESSAMENTO: ");
	    for(int i = 0; i < processos.get(0).numProcessos; i++) {
	    	
	    	System.out.printf(" -> %s ",  processos.get(i).PID);
	    	
	    }
	    System.out.println("\n------------------------");
	    
	    
	    System.out.printf("\n Tempo médio de vida: %.3f \n", mediaTempoVida / numProc);    
	    System.out.printf("\n Tempo médio de espera: %.3f \n ", mediaTempoEspera / numProc); 
	   
	}

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void SJF(ArrayList<Processo> processos) {
			
		
		int numProc = processos.get(0).numProcessos; //gambiarra
		System.out.println("\n-----------------------------------------------\n");
		System.out.println (" --------------- Escalonador SJF -------------");
		System.out.println("\n-----------------------------------------------\n\n ");

		
		int relogioProcessos = 0;
		int total = 0;
		float mediaVida = 0; 
		int mediaEspera = 0;
		 

		
		System.out.println(" ORDEM DE PROCESSAMENTO:  ");
		System.out.println("----------------------------------------------- ");
		while(true)
		{
			int auxNumProc = numProc;
			int relogio = 1000; //so incializo mas ele vai ser resetado
			if (total == numProc) {
				break;
			} // se encher acabei de processar todos
			for (int i=0; i < numProc; i++)
			{
					
						
				if ((processos.get(i).tempoIngresso <= relogioProcessos) && (processos.get(i).tempoCompletou == 0) && (processos.get(i).duracao < relogio))
				{
					relogio = processos.get(i).duracao;
					auxNumProc = i;
				}
		
			}

			if (auxNumProc == numProc) {
				relogioProcessos++;
			}else{
				processos.get(auxNumProc).tempoCompletou = relogioProcessos + processos.get(auxNumProc).duracao;
				
				relogioProcessos += processos.get(auxNumProc).duracao;
				
				processos.get(auxNumProc).tempoVida = processos.get(auxNumProc).tempoCompletou - processos.get(auxNumProc).tempoIngresso;
				processos.get(auxNumProc).tempoEspera = processos.get(auxNumProc).tempoVida - processos.get(auxNumProc).duracao;
				processos.get(auxNumProc).tempoCompletou = 1;
				
				System.out.printf(" -> %s ", processos.get(auxNumProc).PID);

				
				total++;
			}
		
		
		}//final do while
		
		for(int i=0; i < numProc; i++)
		{
			mediaVida += processos.get(i).tempoEspera;
			mediaEspera += processos.get(i).tempoVida;
			
		}
		
		float numerador = mediaEspera;
		float denominador = numProc;
		float resultadoDivisao = numerador / denominador;
		
		System.out.println("\n-----------------------------------------------\n ");
		
		
		System.out.println("MEDIAS DE TEMPO DE VIDA E TEMPO DE ESPERA:");
		System.out.println("-----------------------------------------------");
		System.out.printf ("Tempo de Vida %.2f\n" , resultadoDivisao);
		
		numerador = mediaVida;
		denominador = numProc;
		resultadoDivisao = numerador / denominador;
		
		System.out.printf ("Tempo de Espera %.2f\n", resultadoDivisao);
		System.out.println("-----------------------------------------------\n\n ");

	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		public static void RoundRobin(ArrayList<Processo> processos, int quantum) {
		System.out.println("\n--------------------------------------------------\n");
		System.out.println (" ------------ Escalonador Round Robin -------------");
		System.out.println("\n--------------------------------------------------\n\n ");
		
		
		int numProc = processos.get(0).numProcessos; //gambiarra
		int relogio;

		int temposFinais[];
		int quantidadeProcessos;
		int temposExecucao[];
		
		
		
		double mediaVida = 0; 
		double mediaEspera = 0;
		
		//vou criar copias para não atrapalhar o meu processo final que eu precisaria dos originais
		//vou trabalhr utilizando a ferramenta .remove
		ArrayList<Integer> auxTempoIngresso = new ArrayList<>();
		ArrayList<Integer> auxDuracao = new ArrayList<>();
		ArrayList<Integer> ProcessosRemove = new ArrayList<>();

		quantidadeProcessos = numProc;
		temposFinais = new int[numProc];
		temposExecucao = new int[numProc];
		
		
		
		System.out.println(" ORDEM DE PROCESSAMENTO:  ");
		System.out.println("----------------------------------------------- ");
		
		for(int i = 0; i < numProc; i++) {

			auxTempoIngresso.add(processos.get(i).tempoIngresso);
			auxDuracao.add(processos.get(i).duracao);
			
		} //fim do for


		relogio = (int) auxTempoIngresso.get(0);
	
		while(quantidadeProcessos > 0) {
			
			for(int i = 0; i < numProc; i++) {
				
				if( (int) auxTempoIngresso.get(i) != -1 && (int) auxTempoIngresso.get(i) <= relogio) {
					
					ProcessosRemove.add(i); //somente para serem removidos depois (eu vou saber qual é o indice dele
					//exemplo vetor [0, 1, 2, 3]
					//caso eu adicione o 1 para o final eu vou retornar o numero 1 e eu sei que ele é 
					// a duração na posição 1
					
					auxTempoIngresso.set(i, -1); //flag

				}
				
			}
			
			if(ProcessosRemove.isEmpty()) { // vou zerar o numero de processos para que eu saber que eles acabaram
				
				relogio++; //relogio 
				
			}else {
				
				
				
				int processando = (int) ProcessosRemove.remove(0); //removo o processo que vou aplicar o quantum
				//mas ao mesmo tempo eu guardo ele
				System.out.printf(" -> %s", processos.get(processando).PID );
				int auxQuantum = quantum;
				
				
				while(auxQuantum > 0 && (int) auxDuracao.get(processando) > 0) {
					relogio++;
					auxQuantum--;
					auxDuracao.set(processando,  (int) auxDuracao.get(processando) - 1);
					
				}
				if((int) auxDuracao.get(processando) > 0) { //ainda n zerou, resta duracao

					for(int i = 0; i < numProc; i++) { //significa que ele ainda n acabou

						if( (int) auxTempoIngresso.get(i) != -1 && (int) auxTempoIngresso.get(i) <= relogio) //verificar se há outro processo para entrar
						{
							ProcessosRemove.add(i); //adiciono no final
							auxTempoIngresso.set(i, -1); //apago o tempo ingresso
							
						}						
						
					
					}

					ProcessosRemove.add(processando); //adiciono ele no final da fila caso ele não tenha acabado
				}else {
					//caso contrário ele zerou, logo não me é mais útil
					//pode permanecer zerado e como ele ja foi removido não será mais inserido
					temposFinais[processando] = relogio;
					quantidadeProcessos--;

				}
				
				
				
				
			
			}
			
		}	
		

		
		
		
		
		
		for(int i = 0; i < numProc; i++) {
			
			temposExecucao[i] = temposFinais[i] - (int) processos.get(i).tempoIngresso;
			//System.out.println(temposExecucao[i]);
			mediaVida += temposExecucao[i];
			mediaEspera += temposExecucao[i] - (int) processos.get(i).duracao;	
			
		}
		

		mediaVida = mediaVida / numProc;
		mediaEspera = mediaEspera / numProc;
		System.out.println("\n-----------------------------------------------\n ");
		System.out.println();
		
		
		System.out.println("MEDIAS DE TEMPO DE VIDA E TEMPO DE ESPERA:");
		System.out.println("----------------------------------------------- ");
		System.out.printf("TT medio: %.2f\n", mediaVida);
		System.out.printf("TA medio: %.2f", mediaEspera);
		
		System.out.println();
		
	}	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void PrioridadePreemptiva(ArrayList<Processo> processos, int quantum) {
		System.out.println("\n-------------------------------------------------------------\n");
		System.out.println (" ------------ Escalonador Prioridade Preemptiva -------------");
		System.out.println("\n---------------------------------------------------------------\n\n ");
		
		
		int numProc = processos.get(0).numProcessos; //gambiarra

		int tempoAtual;

		int temposFinais[];
		int quantidadeProcessos;
		int temposExecucao[];
		
		
		
		double mediaVida = 0; 
		double mediaEspera = 0;
		
		//funciona da mesma forma do round robin a diferença é que ele n faz ate acabar ele vai alternando de acordo com o quantum e a prioridade, ele não faz o rodizio
		//acabou o quantum verifica se ha um de maior prioridade se não houver, prossegue
		//vou criar copias para não atrapalhar o meu processo final que eu precisaria dos originais
		//vou trabalhr utilizando a ferramenta .remove
		
		ArrayList<Integer> auxTempoIngresso = new ArrayList<>();
		ArrayList<Integer> auxDuracao = new ArrayList<>();
		ArrayList<Integer> ProcessosRemove = new ArrayList<>();
		ArrayList<Integer> auxPrioridades = new ArrayList<>();

		quantidadeProcessos = numProc;
		temposFinais = new int[numProc];
		temposExecucao = new int[numProc];
		
		System.out.println(" ORDEM DE PROCESSAMENTO:  ");
		System.out.println("----------------------------------------------- ");
		
		for(int i = 0; i < numProc; i++) {

			auxTempoIngresso.add(processos.get(i).tempoIngresso);
			auxDuracao.add(processos.get(i).duracao);
			auxPrioridades.add(processos.get(i).prioridade);
			
			
		} //fim do for


		tempoAtual =  auxTempoIngresso.get(0);
	
		while(quantidadeProcessos > 0) {
			
			for(int i = 0; i < numProc; i++) {
				
				if( (int) auxTempoIngresso.get(i) != -1 && (int) auxTempoIngresso.get(i) <= tempoAtual) {
					ProcessosRemove.add(i); //somente para serem removidos depois
					auxTempoIngresso.set(i, -1);		
				}
				
			}
			
			
			
			if(ProcessosRemove.isEmpty()) { // vou zerar o numero de processos para que eu saber que eles acabaram
				
				tempoAtual++;
				
			}else {
				int prioridadeAtual = 0;

				
				prioridadeAtual = 0;
				int comparando = 0;
				int auxGuarda = 0;
				
				if(ProcessosRemove.size() == 1) {
					prioridadeAtual = 0;
				}else {
					for(int i = 0; i < ProcessosRemove.size(); i++) {

						comparando = (int) auxPrioridades.get(i); //vou comparar a prioridade nessa posicao com a proxima
						
						if( (int) auxPrioridades.get(prioridadeAtual) <= comparando) { //aqui eu preciso do ROUND ROBIN??
							
							prioridadeAtual = i;
						}
					}
				}

				auxGuarda = prioridadeAtual;
				prioridadeAtual = (int) ProcessosRemove.get(auxGuarda);
				System.out.printf(" -> " + processos.get(prioridadeAtual).PID) ;
				int auxQuantum = quantum;
				
				
				while(auxQuantum > 0 && (int) auxDuracao.get(prioridadeAtual) > 0) { // vou diminuindo o tempo
					tempoAtual++;
					auxQuantum--;
					auxDuracao.set(prioridadeAtual,  (int) auxDuracao.get(prioridadeAtual) - 1);
					
				}
				
				
				if((int) auxDuracao.get(prioridadeAtual) > 0) { //caso ainda tenha tempo nele e ele ainda n tenha acabado eu jogo ele para o final
					
					
					for(int i = 0; i < numProc; i++) {
						
						if( auxTempoIngresso.get(i) != -1 && (int) auxTempoIngresso.get(i) <= tempoAtual) //verificar se há outro processo para entrar
						{
							ProcessosRemove.add(i);
							auxTempoIngresso.set(i, -1);
							
						}							
					
					}

				}else {
					
					ProcessosRemove.remove(auxGuarda);
					auxPrioridades.remove(auxGuarda);
					temposFinais[prioridadeAtual] = tempoAtual;
					quantidadeProcessos--;
					
				}
				
			
			}
			
		}	
		

		for(int i = 0; i < numProc; i++) {
			
			temposExecucao[i] = temposFinais[i] - (int) processos.get(i).tempoIngresso;
			mediaVida += temposExecucao[i];
			mediaEspera += temposExecucao[i] - (int) processos.get(i).duracao;
			
		}
		

		mediaVida = mediaVida / numProc;
		mediaEspera = mediaEspera / numProc;
		System.out.println("\n-----------------------------------------------\n ");
		System.out.println();
		
		
		System.out.println("MEDIAS DE TEMPO DE VIDA E TEMPO DE ESPERA:");
		System.out.println("----------------------------------------------- ");
		//System.out.println("teste se esta chegando ao final");
		System.out.printf("TT medio: %.2f\n", mediaVida);
		System.out.printf("TA medio: %.2f", mediaEspera);
		
	}
		
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void PrioridadeCooperativa(ArrayList<Processo> processos1) { //alterar ainda
		
		
		int numProc = processos1.get(0).numProcessos; //gambiarra

		int tempoAtual; 
		int indiceAtual;
		int idProcessoAtual;
		int quantidadeProcessos;
		
		double tempoExecucao = 0;
		double tempoEspera = 0;
		
		ArrayList<Integer> auxTempoIngresso = new ArrayList<Integer>();
		ArrayList<Integer> auxDuracao = new ArrayList<Integer>();
		ArrayList<Integer> prioridades = new ArrayList<Integer>();
		
//		ArrayList<Integer> cpentrada = new ArrayList<Integer>();

		
		
//		ArrayList entrada_1, burst, prioridades, processos, cpentrada;
		int [] temposFinais, temposIniciais;

		numProc = processos1.get(0).numProcessos;

		
		for(int i = 0; i< numProc; i++) {
			
			auxTempoIngresso.add(processos1.get(i).tempoIngresso);
			
			auxDuracao.add(processos1.get(i).duracao);

			prioridades.add(processos1.get(i).prioridade);
			
			
		}
		
		temposIniciais = new int [numProc];
		temposFinais = new int [numProc];
		tempoAtual = ((int) auxTempoIngresso.get(0));
		
		quantidadeProcessos = numProc;
		
		System.out.println(" ORDEM DE PROCESSAMENTO:  ");
		System.out.println("----------------------------------------------- ");
		
		while(quantidadeProcessos > 0) {
			ArrayList<Integer> processos = new ArrayList<Integer>();
			
			for(int i = 0; i < numProc; i++) {
				if((int) auxTempoIngresso.get(i) != -1 && (int)auxTempoIngresso.get(i) <= tempoAtual ) {
					processos.add(i);
				}
			}
			
			if(processos.isEmpty()) {
				tempoAtual++;
				
			}else {
				
				indiceAtual = (int) processos.get(0);
				
				for(int i = 0; i < processos.size(); i++) {
					idProcessoAtual = (int) processos.get(i);
					
					if((int)prioridades.get(idProcessoAtual) > (int) prioridades.get(indiceAtual)) { //aqui eu preciso do ROUND ROBIN?? -> precisarei na preemptiva
						indiceAtual = (int) processos.get(i); //guardando o maior
					}
				}
				
				temposIniciais[indiceAtual] = tempoAtual;
				tempoAtual += (int) auxDuracao.get(indiceAtual);
				temposFinais[indiceAtual] = tempoAtual;
				auxTempoIngresso.set(indiceAtual,  -1);
				
				System.out.printf("  -> %s", processos1.get(indiceAtual).PID);
				quantidadeProcessos--;
				
			}
		}
		
		System.out.println("\n----------------------------------------------- ");

		
		
		System.out.println();
		for(int i = 0; i < numProc; i++) {
			tempoExecucao += temposFinais[i] - processos1.get(i).tempoIngresso;
			tempoEspera += temposIniciais[i] - processos1.get(i).tempoIngresso;
					
		}
		
		System.out.printf("media execucao: %.2f\n", tempoExecucao/numProc);
		System.out.printf("media espera: %.2f", tempoEspera/numProc);
		
	}
}
