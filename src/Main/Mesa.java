package Main;
import java.util.ArrayList;

public class Mesa {
	ArrayList<Cubierto> disponibles;
	
	public Mesa() {
		disponibles  = new ArrayList<Cubierto>();
	}
	
	public void addDisponible(Cubierto aCubierto) {
		
	}
	
	public boolean estaDisponible (String tipo) {
		
		return false;
	}
	
	public Cubierto cogerCubierto(String tipo) {
		
		return disponibles.get(0);
	}
	
	
}
