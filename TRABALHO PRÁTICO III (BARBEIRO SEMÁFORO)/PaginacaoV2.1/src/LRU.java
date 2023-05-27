import java.util.LinkedList;

class LRU{

	public int numFrames;
	public int pageFaults;
	LinkedList<String> frames;
 
	public LRU(int numeroDeQuadros) {
  
		this.numFrames = numeroDeQuadros;
		this.frames = new LinkedList<String>();
 
	}

	public void inserir(String pageNumber) {

  
		int posicaoAtual = frames.indexOf(pageNumber); //se houver retorna a posicao, caso contrario devolve -1
//		System.out.println("minha posicao atual: "+posicaoAtual); 
  
		//System.out.println("\t aconteceu na posição: " +  posicaoAtual);
  
		if (posicaoAtual == -1) { //significa que eu nao encontrei ele la dentro 

   
			if (frames.size() < numFrames) { // verificando se ja preenchi todos os quadros

				//	   System.out.println("entrei nesse caso para o "+ pageNumber);
    
				frames.add(pageNumber); //se ainda houver memoria, apenas adiciono um frame.	
   		
			} else { 
				//caso contrario, isso significa que todos as memorias estao preenchidas e 
				//como esse elemento nao está inserido, precisaremos realocá-lo no lugar
				//de algum outro elemento
				
				//	   System.out.println("entrei nesse caso2 para o "+ pageNumber);
    
				frames.remove(0); //removendo o mais antigo menos utilizado
    
				frames.add(pageNumber);
   		
			}
			
			pageFaults++; //nesse caso havera pageFault
			
		
		} else {
			
			//	  System.out.println("entrei nesse caso3 para o "+ pageNumber);
   
			frames.remove(posicaoAtual); //agora eu vou remove-lo
			frames.add(pageNumber); //adicionando novamente ele so que no final (para que os menos utilizados sejam removidos no primeiro)
  
		}
		
//		System.out.println("meu LRU: " + this.numeroDeFalhas);
 
	}
	
	public int getNumeroDeFalhas()
	{
		return pageFaults;
	}
	
}