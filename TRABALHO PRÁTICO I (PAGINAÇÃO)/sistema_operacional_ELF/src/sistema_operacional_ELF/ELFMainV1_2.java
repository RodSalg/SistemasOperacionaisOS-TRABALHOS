package sistema_operacional_ELF;

import java.util.ArrayList;
import java.util.Scanner;

public class ELFMainV1_2 extends EscalonamentosAlg {
	
	

	public static void main(String[] args)    {
		System.out.println("\t-----------------------------------");
		System.out.println("\t-----------------------------------");
		System.out.println("\t-----------------------------------");
		System.out.println("\tBEM VINDO AO SISTEM OPERACIONAL ELF");
		System.out.println("\t-----------------------------------");
		System.out.println("\t-----------------------------------");
		System.out.println("\t-----------------------------------\n\n\n\n");
		
		ArrayList<Processo> processos = new ArrayList<Processo>();
		processos = setProcessos();
		
		//vamos organizar por tipo
		//tipo 1 -> pode ser FCFS E SJF
		//tipo 2 -> pode ser SJF e RR
		//tipo3 -> pode ser RR e Prioridade
		
		Scanner usuario = new Scanner(System.in);

		
		if(processos.get(0).tipo == 1) {
			
			System.out.println("\n\nIdentificamos que seus tipos de processos são de CPU Bound");
			System.out.println("Algoritmos de Escalonamento recomendados: ");
			System.out.println("(1) - FCFS ");
			System.out.println("(2) - SJF ");
			
			int escolha = usuario.nextInt();

			switch (escolha) {

			//para usar o FCFS
			case 1:

				FCFS(processos);

			break;

			//para usar o SJF
			case 2:

				SJF(processos);

			break;


			default:

			System.out.println("opção inválida");
			// bloco de código que será executado se nenhum dos cases for aceito

			}
			
		}else if(processos.get(0).tipo == 2) {
			
			System.out.println("\n\nIdentificamos que seus tipos de processos são de I/O Bound");
			System.out.println("Algoritmos de Escalonamento recomendados: ");
			System.out.println("(1) - Round Robin ");
			System.out.println("(2) - SJF (Menor trabalho primeiro) ");
			
			int escolha = usuario.nextInt();

			switch (escolha) {

			//para usar o FCFS
			case 1:
				
				System.out.println("\n Para continuarmos com o escalonamento Round Robin,\n digite o valor do Quantum");
				
				escolha = usuario.nextInt();
				RoundRobin(processos, escolha);

			break;

			//para usar o SJF
			case 2:

				SJF(processos);

			break;


			default:

			System.out.println("opção inválida");
			// bloco de código que será executado se nenhum dos cases for aceito

			}
			
		}else if(processos.get(0).tipo == 3) { //aqui vamos ter a prioridade
			
			System.out.println("\n\nIdentificamos que seus tipos de processos são de CPU e I/O bound");
			System.out.println("Algoritmos de Escalonamento recomendados: ");
			System.out.println("(1) - Round Robin ");
			System.out.println("(2) - Prioridade Cooperativa  ");
			System.out.println("(3) - Prioridade Preemptiva  ");
			
			int escolha = usuario.nextInt();

			switch (escolha) {

			//para usar o round robin
			case 1:
				
				System.out.println("\n Para continuarmos com o escalonamento Round Robin,\n digite o valor do Quantum");
				
				escolha = usuario.nextInt();
				RoundRobin(processos, escolha);

			break;

			//para usar o SJF
			case 2:
				PrioridadeCooperativa(processos);
				

			break;

			case 3:
				System.out.println("\n Como a Prioridade escolhida foi a com preempção, precisaremos do Quantum,\n digite o valor do Quantum");
				
				escolha = usuario.nextInt();
				PrioridadePreemptiva(processos, escolha);
				
				break;

			default:

			System.out.println("opção inválida");
			// bloco de código que será executado se nenhum dos cases for aceito

			}
			
		}

		
//	    C:Users\georg\OneDrive\Área de Trabalho\so\Trabalho Prático 1\TESTES\FCFS-SJF-6.txt
//		C:\Users\georg\OneDrive\Área de Trabalho\so\Trabalho Prático 1\TESTES\testeSJF.txt
//		C:Users\georg\OneDrive\Área de Trabalho\so\Trabalho Prático 1\TESTES\FCFS-SJF-6.txt
//		C:\Users\georg\OneDrive\Área de Trabalho\so\Trabalho Prático 1\TESTES\testeRR.txt
//		C:\Users\georg\OneDrive\Área de Trabalho\so\Trabalho Prático 1\TESTES\testeP.txt 
//		C:\Users\georg\OneDrive\Área de Trabalho\so\Trabalho Prático 1\TESTES\PrioridadeTeste.txt
//		C:\Users\georg\OneDrive\Área de Trabalho\so\Trabalho Prático 1\TESTES\txtPrioridadeRR2.txt
//		C:\Users\georg\OneDrive\Área de Trabalho\so\Trabalho Prático 1\TESTES\PrioridadeTeste2.txt
	}
	
}

