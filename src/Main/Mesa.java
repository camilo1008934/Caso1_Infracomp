package Main;
import java.util.ArrayList;

public class Mesa {
	ArrayList<Cubierto> disponibles;
	
	public Mesa(int tenedores, int cuchillos) {
		disponibles  = new ArrayList<Cubierto>();
		for (int i=0; i<tenedores; i++) {
			disponibles.add(new Cubierto("Tenedor"));
		}
		
		for (int i=0; i<cuchillos; i++) {
			disponibles.add(new Cubierto("Cuchillo"));
		}
	}
	
	public synchronized void addDisponible(Cubierto aCubierto) {
		disponibles.add(aCubierto);
		System.out.println("En la mesa hay "+disponibles.size()+" cubiertos.");
		notifyAll();
	}
	
	public synchronized boolean  estaDisponible (String tipo) {
		for (int i=0; i<disponibles.size();i++) {
			if (disponibles.get(i).getTipo().equals(tipo)) {
				return true;
			}
		}
		return false;
	}
	
	public Cubierto cogerCubierto(String tipo) {
		Cubierto retorno=null;
		for (int i=0; i<disponibles.size(); i++) {
			if (disponibles.get(i).getTipo().equals(tipo)) {
				retorno=disponibles.get(i);
				disponibles.remove(i);
				System.out.println("En la mesa hay "+disponibles.size()+" cubiertos.");
				break;
			}		
		}
		return retorno;
	}
	
	
}
