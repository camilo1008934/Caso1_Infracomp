package Main;
import java.util.ArrayList;

public class Mesa {
	ArrayList<Cubierto> disponibles;
	
	public Mesa() {
		disponibles  = new ArrayList<Cubierto>();
	}
	
	public void addDisponible(Cubierto aCubierto) {
		disponibles.add(aCubierto);
	}
	
	public boolean estaDisponible (String tipo) {
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
				break;
			}		
		}
		return retorno;
	}
	
	
}
