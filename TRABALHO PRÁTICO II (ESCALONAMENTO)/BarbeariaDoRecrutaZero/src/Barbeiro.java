import java.util.ArrayList;
import java.util.concurrent.Semaphore;

class Barbeiro extends Thread {
	
	Semaphore semaforo = BarbeariaMain.semaforo;
	ArrayList<Cliente> oficiais = BarbeariaMain.oficiais;
	ArrayList<Cliente> sargentos = BarbeariaMain.sargentos;
	ArrayList<Cliente> cabos = BarbeariaMain.cabos;
	boolean ocupado = false;
	int contOficiais = 0;
	int contSargentos = 0;
	int contCabos = 0;
	int relogioEscovinha = 0;
	double filaSargTotal = 0;
	double filaCabosTotal = 0;
	double filaOficiaisTotal = 0;
	int contadorVezesRelatorio = 0;
	int terminou  = 0;
	Cliente cliente;
	
	Barbeiro()
	{
		this.cliente.categoria = -1;
		this.cliente.tempoCorte = 0;
	}
	
	Barbeiro(String nome) {
		super(nome);

	}
	
	public void coletaInfo(int tipo)
	{
		if(tipo == 1) contOficiais++;
		if(tipo == 2) contSargentos++;
		if(tipo == 3) contCabos++;
		
//		System.out.println("\n\n --------------------------------------------------");
//		System.out.println("Numero de oficiais atendidos: " + oficiais.size());
//		System.out.println("Numero de sargentos atendidos: " + sargentos.size());
//		System.out.println("Numero de cabos atendidos: " + cabos.size());
//		
//		System.out.println("total oficiais teste: " + contOficiais);
//		System.out.println("total sargentos teste: " + contSargentos);
//		System.out.println("total cabos teste: " + contCabos);
//		System.out.println("\n\n --------------------------------------------------");
	}
	
//	public void guardador(Cliente cliente)
//	{
//		if(this.relogioEscovinha + cliente.tempoCorte >= 3)
//		{
//			System.out.println("\n\n\n****ATENÇÃO 3 SEGUNDOS VAO SE PASSAR NO PROXIMO CORTE \n" + this.relogioEscovinha +" "+ cliente.tempoCorte+" -> meu relogio atual: "+this.relogioEscovinha);
////			this.relogioEscovinha = (this.relogioEscovinha + cliente.tempoCorte) - 3;
////			System.out.println("atualizando para "+this.relogioEscovinha);
////			System.out.println("\n\n");				
//			
//			contadorVezesRelatorio++;
//		}else
//		{
////			System.out.println("TO SOMANDO ELE ESCONDIDO");
//			this.relogioEscovinha = this.relogioEscovinha + cliente.tempoCorte;
//		}
//	}
	
	

	
	@Override
	public void run() {
		
		
		try {
			

			
			while(BarbeariaMain.tainha.isAlive() || BarbeariaMain.isNotEmpty()) {
				
				cliente = null;
				
				semaforo.acquire();
				
				if(BarbeariaMain.numBarbeiros == 3) {
					
					if(getName().equals("Zero")) {
						
						if(oficiais.size() > 0) {
							
							ocupado = true;
//							System.out.println("rodei 1");
							coletaInfo(1);
							cliente = oficiais.remove(0);
//							guardador(cliente);
							
							
							
							
						} else if(sargentos.size() > 0 && BarbeariaMain.dentinho.ocupado) {
							
//							System.out.println("rodei 2");
							coletaInfo(2);
							cliente = sargentos.remove(0);
//							guardador(cliente);
							
						} else if(cabos.size() > 0 && BarbeariaMain.otto.ocupado) {
							
//							System.out.println("rodei 3");
							coletaInfo(3);
							cliente = cabos.remove(0);
//							guardador(cliente);
							
						}
					
					}
					
					else if(getName().equals("Dentinho")) {
						
						if(sargentos.size() > 0) {

							ocupado = true;
//							System.out.println("rodei 2");
							coletaInfo(2);
							cliente = sargentos.remove(0);
//							guardador(cliente);
							
						} else if(oficiais.size() > 0 && BarbeariaMain.zero.ocupado) {
							
//							System.out.println("rodei 1");
							coletaInfo(1);
							cliente = oficiais.remove(0);
//							guardador(cliente);
							
						} else if(cabos.size() > 0 && BarbeariaMain.otto.ocupado) {
							
//							System.out.println("rodei 3");
							coletaInfo(3);
							cliente = cabos.remove(0);
//							guardador(cliente);
							
						}
						
					}
					
					else if(getName().equals("Otto")) {
						
						if(cabos.size() > 0) {

							ocupado = true;
//							System.out.println("rodei 3");
							coletaInfo(3);
							cliente = cabos.remove(0);
//							guardador(cliente);
							
						} else if(oficiais.size() > 0 && BarbeariaMain.zero.ocupado) {
							
//							System.out.println("rodei 1");
							coletaInfo(1);
							cliente = oficiais.remove(0);
//							guardador(cliente);
							
						} else if(sargentos.size() > 0 && BarbeariaMain.dentinho.ocupado) {
							
//							System.out.println("rodei 2");
							coletaInfo(2);
							cliente = sargentos.remove(0);
//							guardador(cliente);
							
						}
						
					}
					
				}
				
				else {
						
					if(oficiais.size() > 0) {
						
//						System.out.println("rodei 1");
						coletaInfo(1);
						cliente = oficiais.remove(0);
//						guardador(cliente);
						
					} else if(sargentos.size() > 0) {
//						System.out.println("rodei 2");
						coletaInfo(2);
						cliente = sargentos.remove(0);
//						guardador(cliente);
						
					} else if(cabos.size() > 0) {
						
//						System.out.println("rodei 3");
						coletaInfo(3);
						cliente = cabos.remove(0);
//						guardador(cliente);
						
					}
					
				}
				
				if(cliente == null) {
					
					semaforo.release();
					
				} else {
					
					System.out.println(getName()+" cortando "+cliente);
//					guardador(cliente);
					semaforo.release();
					Thread.sleep(cliente.tempoCorte*1000);
					ocupado = false;
					
				}
				
			}
			
			System.out.println(getName()+" foi pra casa");
			
		} catch (Exception e) {
			System.out.println(getName());
			e.printStackTrace();
		}
	}
}