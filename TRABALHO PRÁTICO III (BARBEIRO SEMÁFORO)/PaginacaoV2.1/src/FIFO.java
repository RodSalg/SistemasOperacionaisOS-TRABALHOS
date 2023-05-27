import java.util.LinkedList;

/**
 * Algoritmo FIFO - First In First Out para Paginacao. 
 * */
public class FIFO 
{
	
	
	public  int ordemFila = 0; //
	public int numFrames;
	public int pageFault;
	LinkedList<String> frames = new LinkedList<String>(); ;
	
	public FIFO(int numFramesP) {

		  this.numFrames = numFramesP;
		  
	}
	
	public void inserir(String pageNumber) {
		
		// verificar se o elemento nao pertence a algum dos Frames
		//verificando se numero de 
		if (frames.contains(pageNumber)) { 
			
			//nao faz nada
			
			
		}else { //agora se ele ainda nao tiver la dentro 
			
			if (frames.size() < numFrames) //vamos verificar se ainda ha frames disponiveis para entrar
			{
				
				frames.add(pageNumber); //caso tenha eu apenas adiciono
				
			} else {
		    
				frames.remove(ordemFila); //vou remover seguindo a ordem da fila 
				frames.add(ordemFila, pageNumber);
				ordemFila++;
		    
				if (ordemFila == numFrames) //aqui eu vou resetar para reiniciar e começar a percorrer a fila novamento do mais antigo ate o ultimo
					
				{
		    	
					ordemFila = 0;
		    
				} 
			}
		   
		   //System.out.println("FIFO-> inserção atual: "+ pageNumber);
			pageFault++;
		   
		}
		 
	}
	
	
	public int getNumeroDeFalhas()
	{
		return this.pageFault;
	}

}
