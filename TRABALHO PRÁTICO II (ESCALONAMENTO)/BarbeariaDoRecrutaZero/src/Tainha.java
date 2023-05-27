import java.util.ArrayList;

class Tainha extends Thread {
	
	int periodo, pausas = 0;
	ArrayList<Cliente> clientes = BarbeariaMain.clientes;
	ArrayList<Cliente> oficiais = BarbeariaMain.oficiais;
	ArrayList<Cliente> sargentos = BarbeariaMain.sargentos;
	ArrayList<Cliente> cabos = BarbeariaMain.cabos;
	
	Tainha() {
		super("Tainha");
	}
	
	void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	@Override
	public void run() {
		try {
			
			while(clientes.size() > 0) {
				
				if(BarbeariaMain.isNotCrowded()) {
					
					if(clientes.get(0).categoria == 0) {
						
						System.out.println(getName()+" identificou ninguem na fila");
						clientes.remove(0);
						pausas++;
						if(pausas == 3) {break;}
						
					} else if(clientes.get(0).categoria == 1) {
						
						System.out.println(getName()+" adicionou "+ clientes.get(0));
						oficiais.add(clientes.remove(0));
						pausas = 0;
						
					} else if(clientes.get(0).categoria == 2) {
						
						System.out.println(getName()+" adicionou "+ clientes.get(0));
						sargentos.add(clientes.remove(0));
						pausas = 0;
						
					} else if(clientes.get(0).categoria == 3) {
						
						System.out.println(getName()+" adicionou "+ clientes.get(0));
						cabos.add(clientes.remove(0));
						pausas = 0;
						
					}
				}
				
				Thread.sleep(periodo*1000);
			}
			
			System.out.println(getName()+" foi pra casa");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}