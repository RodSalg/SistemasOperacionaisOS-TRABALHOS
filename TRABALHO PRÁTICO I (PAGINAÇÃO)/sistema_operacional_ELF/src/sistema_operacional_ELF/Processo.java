package sistema_operacional_ELF;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Processo {
	
	String PID;
	int tempoIngresso;
	int duracao;
	int prioridade;
	int tipo;
	int tempoCompletou;
	int tempoEspera;
	int tempoVida;
	int numProcessos;
	int tempoDuracao;
	
	Processo(){
		this("", 0, 0, 0, 0, 0);
	}


	public Processo(String PID, int tempoIngresso, int duracao, int prioridade, int tipo, int tempoCompletou) {
		
		this.PID = PID;
		this.tempoIngresso = tempoIngresso;
		this.prioridade = prioridade;
		this.duracao = duracao;
		this.tipo = tipo;
		this.tempoCompletou = tempoCompletou;
		
	}
	
	public void setPID(String novoPID) {

		this.PID = novoPID;
		
	}
	
	public void setTempoIngresso(int novoTempoIngresso) {

		this.tempoIngresso = novoTempoIngresso;
		
	}
	
	public void setDuracao(int novaDuracao) {

		this.duracao = novaDuracao;
		
	}
	
	public void setPrioridade(int novaPrioridade) {

		this.prioridade = novaPrioridade;
		
	}
	
	public void setTipo(int novoTipo) {

		this.tipo = novoTipo;
		
	}
	
	public void setTempoCompletou(int novoTempoCompletou) {

		this.tempoCompletou = novoTempoCompletou;
		
	}
	
	public void setNumProcessos(int novoNumProcessos) {
		
		this.numProcessos = novoNumProcessos;
		
	}
	
	
	public static ArrayList<Processo> setProcessos() {
		//variaveis
	    Scanner ler = new Scanner(System.in);
	    String[] textoSeparado;
	    ArrayList<Processo> processos =  new ArrayList<Processo>(); //uma lista com esse processo

	    System.out.printf("para começarmos, coloque o endereço do seu arquivo:\n");
	    String nome = ler.nextLine();

	    //System.out.printf("\nConteúdo do arquivo texto:\n");
	    int cont = 0;
	    
	    try {
	      FileReader arq = new FileReader(nome);
	      BufferedReader lerArq = new BufferedReader(arq);

	      String linha = lerArq.readLine(); 
	
	      while (linha != null) {
	    	  //System.out.printf("\n voltei aos mortos \n");  
	    	
	    	 	        
	        if(linha != null) {
	        	//System.out.printf("%s\n", linha);
			    textoSeparado = linha.split(" ");
			    Processo first = new Processo();
			    
			    first.setPID(textoSeparado[0]);
			    first.setTempoIngresso(Integer.parseInt(textoSeparado[1]));
			    first.setDuracao(Integer.parseInt(textoSeparado[2]));
			    first.setPrioridade(Integer.parseInt(textoSeparado[3]));
			    first.setTipo(Integer.parseInt(textoSeparado[4]));
			    
			    processos.add(first);
			    
		        linha = lerArq.readLine();
	        }
	        cont = cont + 1;
	        

	       // System.out.printf("\n saí do while\n");
	      }

	      processos.get(0).numProcessos = cont; //vou alocar sempre no primeiro
	      
	      arq.close();
	      
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	    //System.out.printf("\n vou retornar \n");
	    
	    //ler.close();
		return processos;

		
	}
	

}
