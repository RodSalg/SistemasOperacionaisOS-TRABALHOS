import java.util.ArrayList;

class Escovinha extends Thread {
	
	ArrayList<Cliente> oficiais = BarbeariaMain.oficiais;
	ArrayList<Cliente> sargentos = BarbeariaMain.sargentos;
	ArrayList<Cliente> cabos = BarbeariaMain.cabos;
	Barbeiro zeroOutro;
	Barbeiro  dentinhoOutro;
	Barbeiro ottoOutro;
	private static double tempo = 0;
	static double tempoAtdOficiais = 0;
	static double tempoAtdSargentos = 0;
	static double tempoAtdCabos = 0;
	static double tempoEspOficiais = 0;
	static double tempoEspSargentos = 0;
	static double tempoEspCabos = 0;
	static double tamFilaOficiais = 0;
	static double tamFilaSargentos = 0;
	static double tamFilaCabos = 0;
	int relatorios = 0;
	int acordado = 0;
	double totalTempo = 0;
	int oficiaisAtuais = 0;
	int sargentosAtuais = 0;
	int cabosAtuais = 0;
	double totalOficiais = 0.0;
	double totalSargentos = 0.0;
	double totalCabos = 0.0;

	
	
	Escovinha(Barbeiro zero, Barbeiro dentinho, Barbeiro otto) {
		super("Escovinha");
		this.zeroOutro = zero;
		this.dentinhoOutro = dentinho;
		this.ottoOutro = otto;
//		
		
	}
	
	public void showCategoriasZero()
	{
		this.oficiaisAtuais = this.zeroOutro.contOficiais;
		this.sargentosAtuais = this.zeroOutro.contSargentos;
		this.cabosAtuais = this.zeroOutro.contCabos ;
		
		System.out.println("Oficias :" + this.zeroOutro.contOficiais );
		System.out.println("Sargentos :" + this.zeroOutro.contSargentos );
		System.out.println("Cabos :" + this.zeroOutro.contCabos);
	}
	public void showCategoriasDentinho()
	{
		this.oficiaisAtuais = this.dentinhoOutro.contOficiais + this.zeroOutro.contOficiais;
		this.sargentosAtuais = this.dentinhoOutro.contSargentos + this.zeroOutro.contSargentos;
		this.cabosAtuais = this.dentinhoOutro.contCabos + this.zeroOutro.contCabos;
		
		System.out.println("Oficias :" + (this.dentinhoOutro.contOficiais + this.zeroOutro.contOficiais));
		System.out.println("Sargentos :" + (this.dentinhoOutro.contSargentos + this.zeroOutro.contSargentos ));
		System.out.println("Cabos :" + (this.dentinhoOutro.contCabos + this.zeroOutro.contCabos));
	}
	public void showCategoriasOtto()
	{
		this.oficiaisAtuais = this.ottoOutro.contOficiais + this.zeroOutro.contOficiais + this.dentinhoOutro.contOficiais;
		this.sargentosAtuais = this.ottoOutro.contSargentos + this.zeroOutro.contSargentos + this.dentinhoOutro.contSargentos;
		this.cabosAtuais = this.ottoOutro.contCabos + this.zeroOutro.contCabos + this.dentinhoOutro.contCabos;
		
		System.out.println("Oficias :" + (this.ottoOutro.contOficiais + this.zeroOutro.contOficiais + this.dentinhoOutro.contOficiais ));
		System.out.println("Sargentos :" + (this.ottoOutro.contSargentos + this.zeroOutro.contSargentos + this.dentinhoOutro.contSargentos  ));
		System.out.println("Cabos :" + (this.ottoOutro.contCabos + this.zeroOutro.contCabos + this.dentinhoOutro.contCabos));
	}
	
	public void setTempoAtdZero()
	{
		//System.out.println("EU DOU ERRO NISSO: "+  this.zeroOutro.cliente.categoria);
		if( zeroOutro.cliente != null)
		{
			showCategoriasZero();
			if(zeroOutro.cliente.categoria == 1)
			{
				
//				System.out.println("vou fazer a seguinte soma oficiais: "+ tempoAtdOficiais + " + " + this.zeroOutro.cliente.tempoCorte);
				tempoAtdOficiais = (this.zeroOutro.cliente.tempoCorte + tempoAtdOficiais);
				
			}else if ( zeroOutro.cliente.categoria == 2)
			{
//				System.out.println("vou fazer a seguinte soma sargentos: "+ tempoAtdSargentos + " + " + this.zeroOutro.cliente.tempoCorte);
				tempoAtdSargentos = (this.zeroOutro.cliente.tempoCorte + tempoAtdSargentos);
			}else if(zeroOutro.cliente.categoria == 3)
			{
//				System.out.println("vou fazer a seguinte soma cabos: "+ tempoAtdCabos + " + " + this.zeroOutro.cliente.tempoCorte);
				tempoAtdCabos = (this.zeroOutro.cliente.tempoCorte + tempoAtdCabos);
			}
		}

	}
	
	public void setTempoAtdDentinho()
	{
		if(this.dentinhoOutro.cliente != null)
		{
			if(dentinhoOutro.cliente.categoria == 1)
			{
				tempoAtdOficiais = (this.dentinhoOutro.cliente.tempoCorte + tempoAtdOficiais);
			}else if ( dentinhoOutro.cliente.categoria == 2)
			{
				tempoAtdSargentos = (this.dentinhoOutro.cliente.tempoCorte + tempoAtdSargentos);
			}else if(dentinhoOutro.cliente.categoria == 3)
			{
				tempoAtdCabos = (this.dentinhoOutro.cliente.tempoCorte + tempoAtdOficiais);
			}
		}
	}
	
	public void setTempoAtdOtto()
	{
		if(this.ottoOutro.cliente != null)
		{
			if(ottoOutro.cliente.categoria == 1)
			{
				tempoAtdOficiais = (this.ottoOutro.cliente.tempoCorte + tempoAtdOficiais);
			}else if ( ottoOutro.cliente.categoria == 2)
			{
				tempoAtdSargentos = (this.ottoOutro.cliente.tempoCorte + tempoAtdSargentos);
			}else if(ottoOutro.cliente.categoria == 3)
			{
				tempoAtdCabos = (this.ottoOutro.cliente.tempoCorte + tempoAtdOficiais);
			}
		}
	}
	
	public void getTempoEspera()
	{

		
//		System.out.println("antes de começar: "+ this.tempoEspSargentos);
		System.out.println("antes de começar: "+ this.tempoEspSargentos);
		for(int i = 0; i < oficiais.size();i++)
		{

			totalOficiais += this.oficiais.get(i).tempoCorte ;
		}
		
		for(int i = 0; i < sargentos.size();i++)
		{
			if(this.sargentos != null)
			{
//				System.out.println("eu entro auqi?: "+ this.tempoEspSargentos);
				totalSargentos += this.sargentos.get(i).tempoCorte ;
			}
		}
		
		for(int i = 0; i < cabos.size();i++)
		{
			if(this.cabos != null)
			{
				totalCabos += this.cabos.get(i).tempoCorte ;
			}
		}
//		System.out.println("tamanho do sargentos size :" +  sargentos.size());
		if(oficiais.size() == 0)
		{
			totalOficiais = totalOficiais/1;
		}else {
			totalOficiais = totalOficiais/oficiais.size();
		}
		
		if(sargentos.size() == 0)
		{
			totalSargentos = totalSargentos/1;
		}else {
			System.out.println("dividindo: " + totalSargentos + " + " + sargentos.size());
			totalSargentos = totalSargentos/sargentos.size();
		}
		
		if(cabos.size() == 0)
		{
			totalCabos = totalCabos/1;
		}else {
			totalCabos = totalCabos/cabos.size();
		}

		
		this.tempoEspOficiais = this.tempoEspOficiais + totalOficiais;
		this.tempoEspSargentos = this.tempoEspSargentos + totalSargentos;
		this.tempoEspCabos = this.tempoEspCabos + totalCabos;
		
//		this.tempoEspSargentos = tempoEspSargentos/sargentos.size();
		
		System.out.println("----------tempo de espera atual: ---------------");
		System.out.println("espera Oficiais :" + (this.tempoEspOficiais));
		System.out.println("epera Sargentos :" + (this.tempoEspSargentos ));
		System.out.println("espera Cabos :" + (this.tempoEspCabos));
	}
	
	public void run() {
		try {
			
			
			while(BarbeariaMain.isAllAlive()) {
				acordado = 1;
				System.out.println("\t\tHORA DO RELATORIO");
				System.out.println(oficiais.size()*5+"% "+sargentos.size()*5+"% "+cabos.size()*5+"% "+BarbeariaMain.cadeirasLivres()*5+"%");
				System.out.println("comprimento atual das filas: "+oficiais.size()+" "+sargentos.size()+" "+cabos.size());
				getTempoEspera();
				
				//tamanho da fila
				System.out.println();
				tamFilaOficiais = oficiais.size() + tamFilaOficiais;
				tamFilaSargentos = sargentos.size() + tamFilaSargentos;
				tamFilaCabos = cabos.size() + tamFilaCabos;
				
				//tempo de corte por categoria
				
				if(this.dentinhoOutro.cliente == null && this.ottoOutro.cliente == null)
				{
//					System.out.println(" \n\t******************************** to fazendo so o zero **********\n");
					setTempoAtdZero();
					
				}else if(this.dentinhoOutro.cliente != null && this.ottoOutro.cliente == null)
				{
//					System.out.println(" \n\t******************************** to fazendo so o zero e o dentinho **********\n");
					setTempoAtdZero();
					setTempoAtdDentinho();
					
				}else if(this.dentinhoOutro.cliente != null && this.ottoOutro.cliente != null)
				{
//					System.out.println(" \n\t******************************** to fazendo so o zero, dentinho e o otto**********\n");
					setTempoAtdZero();
					setTempoAtdDentinho();
					setTempoAtdOtto();
				}
				
				
				
				setTempo(getTempo() + 1);

				acordado = 0;
				Thread.sleep(3000);
				
			}
			
			
			
			System.out.println(getName() + " foi pra casa\n");
			
			System.out.println("---------------------------------------------------");
			System.out.println("RELATÓRIO FINAL:\n");
			System.out.println("total de relatorios coletados pelo tenente Escovinha: "+ getTempo());
			
			//tamanho da fila
			System.out.println("-------- Tamanho total da fila por categoria: ---------");
			System.out.printf("media fila oficias: %.2f \n", (this.tamFilaOficiais / this.getTempo()));
			System.out.printf("media fila sargentos: %.2f \n", (this.tamFilaSargentos / this.getTempo()));
			System.out.printf("media fila cabos: %.2f \n\n", (this.tamFilaCabos / this.getTempo()));
			System.out.println("-------- Tamanho total da Médio de todas as filas juntas: ---------");
			System.out.println("TAMANHO MEDIO DAS FILAS: " + ((this.tamFilaOficiais + this.tamFilaSargentos + this.tamFilaCabos))/this.getTempo());
			
			System.out.println();
			
			//tempo de corte por categoria
			System.out.println("-------- Tempo total corte por categoria: ---------");
			System.out.println("tempo oficias: " + tempoAtdOficiais + "| tempo sargentos " + tempoAtdSargentos + " |tempo cabos " + tempoAtdCabos);
			System.out.println("\n---------Tempo total Medio de corte por categoria: ---------");
			System.out.println("tempo oficias: " + (double)(tempoAtdOficiais/this.getTempo()) + " | tempo sargentos "
					+ (double) (tempoAtdSargentos/this.getTempo()) + " |tempo cabos " + (double) (tempoAtdCabos/this.getTempo()));
			
			System.out.println();
			
			System.out.println("----------Total de clientes por categoria registrado a cada 3 segundos pelo escovinha ---------------");
			System.out.println("Oficias :" + (this.oficiaisAtuais));
			System.out.println("Sargentos :" + (this.sargentosAtuais  ));
			System.out.println("Cabos :" + (this.cabosAtuais));
			
			System.out.println("----------Total de clientes por categoria TOTAL  ---------------");
			System.out.println("Oficias :" + (this.zeroOutro.contOficiais + this.dentinhoOutro.contOficiais + this.ottoOutro.contOficiais));
			System.out.println("Sargentos :" + (this.zeroOutro.contSargentos + this.dentinhoOutro.contSargentos + this.ottoOutro.contSargentos));
			System.out.println("Cabos :" + (this.zeroOutro.contCabos + this.dentinhoOutro.contCabos + this.ottoOutro.contCabos));
			
			
			System.out.println("----------Total de espera por categoria ---------------");
			System.out.println("espera Oficias :" + (this.tempoEspOficiais));
			System.out.println("epera Sargentos :" + (this.tempoEspSargentos ));
			System.out.println("espera Cabos :" + (this.tempoEspCabos));
			
			System.out.println("----------Total Medio de espera por categoria ---------------");
			System.out.println("media espera Oficias :" + (this.tempoEspOficiais/this.tempo));
			System.out.println("media epera Sargentos :" + (this.tempoEspSargentos/this.tempo));
			System.out.println("mdia espera Cabos :" + (this.tempoEspCabos/this.tempo));
			
//			System.out.println("Total de categorias atendidas: ");
//			System.out.println("Oficias :" + this.zeroOutro.contOficiais );
			 
			//tempo de espera geral: numero de pessoas fora e dentro somadas
			
			
			//posso usar posteriormente, por enquanto o professor não solicitou isso
//			totalTempo =  (tempoAtdCabos + tempoAtdOficiais + tempoAtdSargentos);
//			System.out.println("ESSE É MEU TEMPO CORTE TOTAL: " + totalTempo);
//			System.out.println("ESSE É MEU  TEMPO MEDIO CORTE TOTAL: " + (double)(totalTempo / this.getTempo()) );
			
			
			

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double getTempo() {
		return tempo;
	}

	public static void setTempo(double tempo) {
		Escovinha.tempo = tempo;
	}

	
}