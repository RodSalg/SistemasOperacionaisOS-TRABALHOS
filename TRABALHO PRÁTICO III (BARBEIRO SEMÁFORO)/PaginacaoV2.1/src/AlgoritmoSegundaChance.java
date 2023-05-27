import java.util.LinkedList;

class AlgoritmoSegundaChance{
		
	 	LinkedList quadros;
		LinkedList chance;
		protected int numeroDeQuadros;
		protected int numeroDeFalhas;
		public int PONTEIRO = 0;
	
		public AlgoritmoSegundaChance(int numeroDeQuadros) {
			
			 this.numeroDeQuadros = numeroDeQuadros;
			 this.quadros = new LinkedList();
			 this.chance = new LinkedList();
		
		}
		
	
	 public void inserir(String pageNumber) {

		 int tmp = quadros.indexOf(pageNumber);
	
		 // caso a pagina ainda nao esteja na memoria
		 if (tmp == -1) {
			 if (quadros.size() < numeroDeQuadros) {
				 quadros.add(pageNumber);
				 chance.add(0);
				 
			 } else {
				 
				 
				 while ((int)chance.get(PONTEIRO) == 1) { //comentei int
					 chance.set(PONTEIRO, 0);
					 PONTEIRO++;
				   // ponteiro voltando ao inicio
				   if (PONTEIRO == numeroDeQuadros) {
					   PONTEIRO = 0;
					   }
				 }
	   
				 // substituicao
	    
				 quadros.remove(PONTEIRO);
				 chance.remove(PONTEIRO);
				 quadros.add(PONTEIRO, pageNumber);
				 chance.add(PONTEIRO, 0);
				 PONTEIRO++;
	    
				 // ponteiro voltando ao inicio
				 if (PONTEIRO == numeroDeQuadros) {
	    
					 PONTEIRO = 0;
					 
				 }
				 
			 }
	  
			 numeroDeFalhas++;
			 } else {
				 // se a pagina ja esta na memoria
				 chance.set(tmp, 1);
	  
			 }
	 
	 }
	 
	 public int getPageFaults() {
		 
		  return numeroDeFalhas;
		  
	 }
	
	 	 
}







